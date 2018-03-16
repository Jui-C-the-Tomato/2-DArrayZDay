package main.java.World;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private int row;
    private int col;
    private String type = "A";

    /**
     * Actor Default constructors and Specific constructor
     * Actor getters and setters
     */
    public Actor(){
    }
    public Actor(int startrow, int startcol){
        row = startrow;
        col = startcol;
    }
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

    /**
     *
     * @param board (The current board)
     * @param row (the row the Actor wants to move to)
     * @param col (the column the Actor wants to move to)
     * Checks for possible move locations
     * Gets the valid locations the actor can move in
     * Checks if the desired moveTo location is in the array of valid locations
     * If desired location is valid, moveTo location.
     */
    public void moveTo(String[][] board, int row, int col){
        int previousRow = this.getRow();
        int previousCol = this.getColumn();
        List<Location> validlocations = this.getValidLocations(this);
        Location desiredlocation = new Location(row, col);
        //System.out.println(row +" "+ col);
        boolean actorValidMove = checkValidLocation(board,this, validlocations, desiredlocation);

        if(actorValidMove) {
            this.setRow(row);
            this.setColumn(col);
            //System.out.println(previousRow + " " + previousCol);
            ConsoleRunner.add(board, this);

            if(previousRow != row || previousCol != col) {
                ConsoleRunner.remove(board, this, previousRow, previousCol);
            }
        }else{
            System.out.println("Cannot move there");
        }
    }

}
