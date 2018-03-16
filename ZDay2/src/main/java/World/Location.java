package main.java.World;

public class Location {
    private int row;
    private int column;



    public Location(int r, int c){
        row = r;
        column = c;
    }

    public int getRow() { return row;}

    public int getCol() { return column;}

    /**
     * The turn angle for turning 90 degrees to the left.
     */
    public static final int LEFT = -90;
    /**
     * The turn angle for turning 90 degrees to the right.
     */
    public static final int RIGHT = 90;
    /**
     * The turn angle for turning 45 degrees to the left.
     */
    public static final int HALF_LEFT = -45;
    /**
     * The turn angle for turning 45 degrees to the right.
     */
    public static final int HALF_RIGHT = 45;
    /**
     * The turn angle for turning a full circle.
     */
    public static final int FULL_CIRCLE = 360;
    /**
     * The turn angle for turning a half circle.
     */
    public static final int HALF_CIRCLE = 180;
    /**
     * The turn angle for making no turn.
     */
    public static final int AHEAD = 0;

    /**
     * The compass direction for north.
     */
    public static final int NORTH = 0;
    /**
     * The compass direction for northeast.
     */
    public static final int NORTHEAST = 45;
    /**
     * The compass direction for east.
     */
    public static final int EAST = 90;
    /**
     * The compass direction for southeast.
     */
    public static final int SOUTHEAST = 135;
    /**
     * The compass direction for south.
     */
    public static final int SOUTH = 180;
    /**
     * The compass direction for southwest.
     */
    public static final int SOUTHWEST = 225;
    /**
     * The compass direction for west.
     */
    public static final int WEST = 270;
    /**
     * The compass direction for northwest.
     */
    public static final int NORTHWEST = 315;

    /**
     *
     * @param direction (Directions range from 0 - 360, rounding to a factor of 45 each time, giving 8 compass directions)
     * @return The Location adjacent to the given direction.
     */
    public Location getAdjacentLocation(int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
    }

    public String toString()
    {
        return "(" + getRow() + ", " + getCol() + ")";
    }

    public static Location directionToLocation(Actor a, String compassDirection){
        int changeRow = 0;
        int changeCol = 0;
        switch(compassDirection){
            case "STAY": changeRow = 0;
                         changeCol = 0;
                break;
            case "N": changeRow = -1 ;
                break;
            case "E": changeCol = 1;
                break;
            case "S": changeRow = 1;
                break;
            case "W": changeCol = -1;
                break;
            case "NE": changeRow = -1;
                       changeCol = 1;
                break;
            case "NW": changeRow = -1;
                       changeCol = -1;
                break;
            case "SE": changeRow = 1;
                       changeCol = 1;
                break;
            case "SW": changeRow = 1;
                       changeCol = -1;
                break;
            default:
                System.out.println("Not a valid Direction");
                return new Location(a.getRow(), a.getColumn());
        }
        return new Location(a.getRow() + changeRow, a.getColumn() + changeCol);
    }
}
