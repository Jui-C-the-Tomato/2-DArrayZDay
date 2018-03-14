package main.java.World;

public class Actor {
    private int row;
    private int col;
    private String type = "A";
    private int speed =

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
    public String[][] getValidLocations(){

    }

    public void moveTo(String[][] board, int row, int col){ //removed Actor a
        //check for possible move locations
        int previousRow = this.getRow();
        int previousCol = this.getColumn();
        this.setRow(row);
        this.setColumn(col);
        ConsoleRunner.add(board, this);
        ConsoleRunner.remove(board, this, previousRow, previousCol);
    }

}
