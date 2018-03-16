package main.java.World;

public class Supplies extends Actor
{
    private int row;
    private int col;
    private String type = "S";

    /**
     * Actor Default constructors and Specific constructor
     * Actor getters and setters
     */
    public Supplies(){
    }
    public Supplies(int startrow, int startcol){
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


}
