import java.awt.*;


public class Ant_Util {

    // define the color with RGB
    public static final Color white = new Color(255, 255, 255);
    public static final Color black = new Color(0, 0, 0);
    public static final Color red = new Color(255, 0, 0);   
    public static final Color blue = new Color(0, 0, 255);  //drunk mode
    public static final Color purple = new Color(102, 0, 204);
    // the number of grids on the x-axis
    public static int Grid_Height = 80;
    // the number of grids on the y-axis
    public static int Grid_Width = 80;

    // the width of the window
    public static int Win_Width = 700;
    // the height of the window
    public static int Win_Height = 700;

    // do not modify these four values
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    // the starting point of the first ant
    public static final int ANT_START_X = Grid_Height / 2;
    public static final int ANT_START_Y = Grid_Width / 2;

    // the speed of the ant : how much time (ms) the ant spends
    public static int ANT_SPEED = 101;
    
}
