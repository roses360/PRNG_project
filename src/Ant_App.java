import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ant_App implements ActionListener {

    JFrame frame = null;
    Grids grid = null;

    Ant ant = null;
    ArrayList<Ant> ant_ths = null;
    ControlPanel con_pl = null;

    boolean paused;

    public Ant_App() {
        paused = false;

        grid = Grids.getInstance();
        grid.setActionListener(this);
                
        con_pl = new ControlPanel(grid);

        frame = new JFrame();
        frame.add(grid, BorderLayout.CENTER);
        frame.add(con_pl, BorderLayout.SOUTH);

        frame.setTitle("Langtan's Ant");
        frame.setSize(new Dimension(Ant_Util.Win_Width, Ant_Util.Win_Height + 50));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        
        frame.setVisible(true);
        
        ant = new Ant(Ant_Util.ANT_START_X, Ant_Util.ANT_START_Y, grid, con_pl);
        ant_ths = new ArrayList<Ant>();
        ant_ths.add(ant);
        new Thread(ant).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.con_pl.isStopped()) {
            this.paused = true;
        }
        if (this.con_pl.isPuttingAnt()) {
            String str[] = e.getActionCommand().split(" ");
            int x = Integer.valueOf(str[0]);
            int y = Integer.valueOf(str[1]);
            ant = new Ant(x, y, grid, con_pl);
            ant_ths.add(ant);
            new Thread(ant).start();
        } else if (this.con_pl.isDeleting()){
            //System.out.println("hi");
            String str[] = e.getActionCommand().split(" ");
            int x = Integer.valueOf(str[0]);
            int y = Integer.valueOf(str[1]);            
            a: for (Ant a : ant_ths){
                if(a.x == x && a.y == y){
                    a.terminal();
                    grid.setColor(x, y, Ant_Util.white);
                    break a;
                }
            }
        } else if (this.con_pl.isDrawing()){
            String str[] = e.getActionCommand().split(" ");
            int x = Integer.valueOf(str[0]);
            int y = Integer.valueOf(str[1]);            
            if(grid.getColor(x, y) == Ant_Util.white)
                grid.setColor(x, y, Ant_Util.black);
            else if(grid.getColor(x, y) == Ant_Util.black)
                grid.setColor(x, y, Ant_Util.white);
        }
        
    }
}