package main.java.World;

import java.util.ArrayList;
import java.util.List;

public class Zombie extends Actor {
    private int row;
    private int col;
    private String type = "Z";

    public Zombie(int startrow, int startcol){
        row = startrow;
        col = startcol;
    }
    //Accessors and Mutators
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return col;
    }
    public void setRow(int newrow){
        row = newrow;
    }
    public void setColumn(int newcol){
        col = newcol;
    }
    public String getType(){
        return type;
    }

    public void processActors(){

    }

    /**
     *
     * @param a (the Actor object)
     * @return (Location list of valid move locations in all 9 locations
     * which includes the 8 compass directions and the Actor current position)
     */
    public List<Location> getValidLocations(Actor a){

        List<Location> validlocations = new ArrayList<Location>();
        Location currentActorPos = new Location(a.getRow(), a.getColumn());
        for(int i = 0; i < 8; i ++){
            Location adjloc = currentActorPos.getAdjacentLocation(i * 45);
            validlocations.add(i, adjloc);
            System.out.println(validlocations.get(i).toString());
        }
        validlocations.add(currentActorPos);
        return validlocations;
    }

    /**
     *
     * @param vl (List of valid Locations the Actor can move based on its current position)
     * @param l (The Location the Actor wants to move to)
     * @return True or false based on whether the Location is within valid Locations
     */
    public boolean checkValidLocation(String[][] board, Actor a, List<Location> vl, Location l){
        //Checks if parameter coordinates are within validlocations array and the board array
        boolean actorValidMove = false;
        boolean boardValidMove = true;
        for(Location validlocations : vl){
            if (l.toString().equals(validlocations.toString())){
                actorValidMove = true;
            }
        }
        if(a.getRow() == 0 ||
                a.getRow() == board.length-1 || a.getColumn() == 0 ||
                a.getColumn() == board[0].length-1){ //equal 8
            if(l.getRow() < 0 || l.getRow() > board.length-1 ||
                    l.getCol() < 0 || l.getCol() > board.length-1){
                boardValidMove = false;
            }
        }
        return actorValidMove && boardValidMove;
    }

    public Location zombieWander(){
        String[] compassDirections = {"STAY", "N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        String randDirection = compassDirections[(int)(Math.random() * 8) +1];
        //System.out.print(randDirection);
        return Location.directionToLocation(this, randDirection);
    }

    public boolean playerInSight(Player p, List<Location> validLocations){
        List<Location> sightLocations = new ArrayList<Location>(); //Stores all the valid locations and locations in its field of view

        for(Location vl: validLocations){
            sightLocations.add(vl);
            sightLocations.add(new Location(vl.getRow()+1, vl.getCol()+1));
            sightLocations.add(new Location(vl.getRow()-1, vl.getCol()+1));
            sightLocations.add(new Location(vl.getRow()+1, vl.getCol()-1));
            sightLocations.add(new Location(vl.getRow()-1, vl.getCol()-1));
        }
        for(Location sl: sightLocations){
            if(p.getRow() == sl.getRow() && p.getColumn() == sl.getCol()){ //Checks if the player's location matches the zombie's sight locations
                return true;
            }
        }
        return false;
    }

    public Location getDirectionTowardsPlayer(Player p){ //Returns the location that is in the player's direction
        int currentZRow = this.getRow();
        int currentZCol = this.getColumn();
        int currentPRow = p.getRow();
        int currentPCol = p.getColumn();

        int a = currentPRow - currentZRow; // +: player is lower than zombie / -: player is higher than zombie
        int b = currentPCol - currentZCol; // +: player is to the right of zombie / -: player is to the left of zombie

        Location locationTowardsPlayer = new Location((int)(this.getRow()+Math.signum(a)), (int)(this.getColumn()+Math.signum(b)));

        System.out.println(locationTowardsPlayer);
        return locationTowardsPlayer;
    }
    /**
     *
     * @param board (The current board)
     * Checks for possible move locations
     * Gets the valid locations the actor can move in
     * Checks if the desired moveTo location is in the array of valid locations
     * If desired location is valid, moveTo location.
     */
    public void moveTo(String[][] board, Player p){
        int previousRow = this.getRow();
        int previousCol = this.getColumn();

        Location l = zombieWander(); //Generates random zombie desired location
        int desiredRow = l.getRow();
        int desiredCol = l.getCol();

        List<Location> validlocations = this.getValidLocations(this); //Gets the 8 Locations surrounding the zombie
        Location desiredlocation = new Location(desiredRow, desiredCol); //Desired Location which was randomly generated
        System.out.println(desiredRow +" "+ desiredCol);
        boolean actorValidMove = checkValidLocation(board,this, validlocations, desiredlocation); //Checks if the zombie's move doesn't go out of bounds
        System.out.println(actorValidMove);

        //HERE
        boolean playerInView = playerInSight(p, validlocations);
        Location locationTowardsPlayer = getDirectionTowardsPlayer(p);

        if(actorValidMove) {
            if(playerInView){ //If the player is in view
                this.setRow(locationTowardsPlayer.getRow()); //Set location towards the player
                this.setColumn(locationTowardsPlayer.getCol());
            }else {
                this.setRow(l.getRow()); //Else wander randomly
                this.setColumn(l.getCol());
            }
            System.out.println(previousRow + " " + previousCol);
            ConsoleRunner.add(board, this);

            if(previousRow != row || previousCol != col) { //issue
                ConsoleRunner.remove(board, this, previousRow, previousCol);
            }
        }else{
            System.out.println("Cannot move there");
        }
    }
}
