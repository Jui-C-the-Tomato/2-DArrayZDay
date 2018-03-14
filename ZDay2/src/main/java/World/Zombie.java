package main.java.World;

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

    public void processActors(){}

}
