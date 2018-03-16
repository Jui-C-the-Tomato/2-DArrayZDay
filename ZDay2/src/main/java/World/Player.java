package main.java.World;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private int row;
    private int col;
    private String type = "@";

    public Player(int startrow, int startcol){
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

    public void processActors(){}

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
            //System.out.println(validlocations.get(i).toString());
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
    public boolean checkValidLocation(List<Location> vl, Location l){
        //Checks if parameter coordinates are within validlocations array
        for(Location validlocations : vl){
            if (l.toString().equals(validlocations.toString())){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param board (The current board)
     * @param compassDirection (The compassDirection the player wants to move to)
     * Checks for possible move locations
     * Gets the valid locations the actor can move in
     * Checks if the desired moveTo location is in the array of valid locations
     * If desired location is valid, moveTo location.
     */
    public void moveTo(String[][] board, String compassDirection){ //removed Actor a
        int previousRow = this.getRow();
        int previousCol = this.getColumn();
        List<Location> validlocations = this.getValidLocations(this);
        Location desiredlocation = new Location(row, col);
        boolean validMove = checkValidLocation(validlocations, desiredlocation);

        if(validMove) {
            this.setRow(row);
            this.setColumn(col);
            ConsoleRunner.add(board, this);
            ConsoleRunner.remove(board, this, previousRow, previousCol);
        }else{
            //System.out.println("Cannot move there");
        }
    }
}