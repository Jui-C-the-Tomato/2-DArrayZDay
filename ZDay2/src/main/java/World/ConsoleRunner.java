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
        if(validLocationsOnBoard(board, row, col)){
            board[row][col] = aType;
        }else{
            System.out.println("Not valid locations");
        }
    }
    public static void remove(String[][] board, Actor a, int rowRemove, int colRemove){
        //String aType = a.getType();
        if(validLocationsOnBoard(board, rowRemove, colRemove)){
                board[rowRemove][colRemove] = "~";

        }else{
            System.out.println("Not valid locations");
        }
    }

    public static boolean validLocationsOnBoard(String[][] board, int row, int col){ //Checks if the location given is valid on the board.
        // implements location
        if(row > board.length || col > board[0].length){
            return false;
        }
        return true;
    }

    public static Location inputLocation(String[][] board, Actor a, Scanner UI){
        String[] compassDirections = {"STAY", "N", "S", "E", "W", "NE", "NW", "SE", "SW"};
        System.out.println("Which direction do you want to move?\n(N,E,S,W,NE,NW,SE,SW,STAY)");
        String dirLocation = UI.next();
        for(String i : compassDirections) {
            if(dirLocation.equals(i)){
                return Location.directionToLocation(a, dirLocation);
            }
        }
        return Location.directionToLocation(a, "Invalid");
    }


    public static void main(String[] args){
        boolean gameEnd = false;
        /**
         * First create board, then create world from board, then create actors, then add actors onto board, then showboard.
         */
        //Set size of board
       String[][] board = new String[8][8];
       createWorld(board);
       //Instantiate and Add actors
       Actor p = new Player(1,2);
       Zombie z1 = new Zombie(6,6);
       add(board, p);
       add(board, z1);
       //Turns
       int turns = 2;

       //Randomly spawn Supplies

       //Randomly spawn obstacles


       while(!gameEnd){
           try {
               showBoard(board);
               //Player Turns
               Scanner UI = new Scanner(System.in);
               Location directionRowsCols = inputLocation(board, p, UI);
               p.moveTo(board, directionRowsCols.getRow(), directionRowsCols.getCol());

               //Zombie Turns
               //Location zl = z1.zombieWander();
               if(turns % 2 == 0) {
                   z1.moveTo(board,(Player)p);
               }


               //Spawn a random supply on the board
               turns++;

           }catch(Exception e){
               System.out.println("Not valid input");
           }

           // Check if player collides with supply
           // Move zombie
           // Ask player where toMove player (Check if valid location)
        }

    }
}
