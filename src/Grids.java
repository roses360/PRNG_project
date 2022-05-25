import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Grids extends Container {
    // every button is a grid
    private JButton[][] grid = new JButton[Ant_Util.Grid_Height][Ant_Util.Grid_Width];

    private static Grids Instance = new Grids();

    public static Grids getInstance() {
        return Instance;
    }

    private Grids() {
        // this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setLayout(new GridLayout(Ant_Util.Grid_Height, Ant_Util.Grid_Width));
        for (int i = 0; i < Ant_Util.Grid_Height; ++ i) {
            for (int j  = 0; j < Ant_Util.Grid_Width; ++ j) {
                grid[i][j] = new JButton();
                grid[i][j].setBackground(Ant_Util.white);
                this.add(grid[i][j]);
            }
        }
    }

    public void setActionListener(ActionListener actionListener) {
        for (int i = 0; i < Ant_Util.Grid_Height; ++ i) {
            for (int j = 0; j < Ant_Util.Grid_Width; ++ j) {
                grid[i][j].addActionListener(actionListener);
                grid[i][j].setActionCommand(i + " " + j);
            }
        }
    }

    public Color getColor(int x, int y) {
        return grid[x][y].getBackground();
    }

    public void setColor(int x, int y, Color c) {
        grid[x][y].setBackground(c);
    }
}