import java.awt.*;
import java.util.Random;


public class Ant implements Runnable {

    int x, y, forward;
    private Grids grid = null;
    private boolean alive;
    ControlPanel con_pl = null;
    private Color color, ant_color;
    Random r;

    public Ant(int x, int y, Grids grid, ControlPanel con_pl) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.forward = Ant_Util.RIGHT;
        this.con_pl = con_pl;
        this.alive = true;
        this.color = Ant_Util.white;
        if(!con_pl.isDrunk()){
            ant_color = Ant_Util.red;
        } else {
            ant_color = Ant_Util.blue;
        } 
        grid.setColor(x, y, ant_color);
        r = new Random();
    }

    public void run() {
        
        while (this.alive) {
            //check if the ant is "drunk"
            if(!con_pl.isDrunk()){
                ant_color = Ant_Util.red;
            } else{
                ant_color = Ant_Util.blue;
                
            }
            // check if the app is paused
            
            if(!(this.con_pl.isStopped())){
                if(!con_pl.isDrunk()){
                    
                    move();
                } 
                else {
                    
                    randomMove();
                } 
                color = grid.getColor(x, y);
                
            }
            grid.setColor(x, y, ant_color);
            int s = con_pl.slider.getValue();
            stay_idle(s);
        }
    }

    public void move(){
        // black : turn left
        if (color == Ant_Util.black || color == Ant_Util.purple) {
            grid.setColor(x, y, Ant_Util.white);
            this.turn_left();
            
        } else if(color == Ant_Util.white){ // white : turn right (the if statement is used to exclude a possible red/blue cell)
            grid.setColor(x, y, Ant_Util.black);
            this.turn_right();                        
        }
    }

    public void randomMove(){
        boolean choice = r.nextBoolean();
        if(color != Ant_Util.white){
            grid.setColor(x, y, Ant_Util.white);
        } else{
            grid.setColor(x, y, Ant_Util.purple);
        }
        
        if(choice){
            this.turn_left();
        }
        else{
            this.turn_right();
        }

        
    }

    // stay idle for next step; and this function controls the speed
    private void stay_idle(int s) {
        try {
            Thread.sleep(Ant_Util.ANT_SPEED - s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void terminal() {
        this.alive = false;
    }

    public boolean isAlive(){
        return this.alive;
    }

    private void turn_left() {
        switch (this.forward) {
            case Ant_Util.UP:
                -- y;
                this.forward = Ant_Util.LEFT;
                break;
            case Ant_Util.DOWN:
                ++ y;
                this.forward = Ant_Util.RIGHT;
                break;
            case Ant_Util.LEFT:
                ++ x;
                this.forward = Ant_Util.DOWN;
                break;
            case Ant_Util.RIGHT:
                -- x;
                this.forward = Ant_Util.UP;
                break;
        }
        check();
    }

    private void turn_right() {
        switch (this.forward) {
            case Ant_Util.UP:
                ++ y;
                this.forward = Ant_Util.RIGHT;
                break;
            case Ant_Util.DOWN:
                -- y;
                this.forward = Ant_Util.LEFT;
                break;
            case Ant_Util.LEFT:
                -- x;
                this.forward = Ant_Util.UP;
                break;
            case Ant_Util.RIGHT:
                ++ x;
                this.forward = Ant_Util.DOWN;
                break;
        }
        check();
    }

    private void check() {
        if (x == Ant_Util.Grid_Height) {
            x = 0;
        } else if (x < 0) {
            x = Ant_Util.Grid_Height - 1;
        }
        if (y == Ant_Util.Grid_Width) {
            y = 0;
        } else if (y < 0) {
            y = Ant_Util.Grid_Width - 1;
        }
    }
}
