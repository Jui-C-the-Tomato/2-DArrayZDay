package main.java.World;
import java.util.*;
import java.util.Scanner;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import main.java.grid.BoundedGrid;

public class ConsoleRunner {
    public static void header(){
        System.out.println("-------------------------");
    }

    public static void createWorld(String[][] board){  //Creates the String[][] and fills it with "~" to set the board up.
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c <  board[0].length; c++){
                board[r][c] = "~";
            }
        }
    }
    public static void showBoard(String[][] board){ //Shows the board in the terminal, including added actors.
        header();
        for(int r = 0; r <  board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                System.out.print(" "+board[r][c]);
            }
            System.out.println("");
        }
    }

    public static void add(String[][] board, Actor a){ //Add actors to the board. Actors must have a location before using add.
        String aType = a.getType();
        int row = a.getRow();
        int col = a.getColumn();
        if(validLocations(board, row, col)){
            board[row][col] = aType;
        }else{
            System.out.println("Not valid locations");
        }
    }
    public static void remove(String[][] board, Actor a, int rowRemove, int colRemove){
        //String aType = a.getType();
        if(validLocations(board, rowRemove, colRemove)){
            board[rowRemove][colRemove] = "~";
        }else{
            System.out.println("Not valid locations");
        }
    }

    public static boolean validLocations(String[][] board, int row, int col){ //Checks if the location given is valid on the board.
        // implements location
        if(row > board.length || col > board[0].length){
            return false;
        }
        return true;
    }

    public static int[] location(Scanner UI){
        int[] location = new int[2];
        System.out.println("Move to Row 0 - 7");
        int moveRow = UI.nextInt();
        System.out.println("Move to Column 0 -7");
        int moveColumn = UI.nextInt();
        location[0] = moveRow;
        location[1] = moveColumn;
        return location;
    }
    public static void main(String[] args){
        boolean gameEnd = false;
        /**
         * First create board, then create world from board, then create actors, then add actors onto board, then showboard.
         */
        //set size of board
       String[][] board = new String[8][8];
       Scanner UI = new Scanner(System.in);
       createWorld(board);
       Actor z = new Zombie(1,2);
       add(board, z);
       showBoard(board);

       while(!gameEnd){
           int[] rowscols = location(UI);
           z.moveTo(board, rowscols[0], rowscols[1]);
           showBoard(board);

           // Check if player collides with supply
           // Move zombie
           // Ask player where toMove player (Check if valid location)
        }

    }
}
