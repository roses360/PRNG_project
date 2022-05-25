import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel implements ActionListener {
    private Grids grid = null;
    public JSlider slider;
    private JLabel label;
    private JPanel panelB, panelS;
    private CusButton draw_btn = null,
                    put_ant_btn = null,
                    stop_btn = null,
                    clear_btn = null,
                    delete_ant_btn = null,
                    drunk_mode_btn = null;
    private boolean disabling;

    public ControlPanel(Grids g) {
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.grid = g;
        this.label = new JLabel();
        this.panelB = new JPanel(new GridLayout(0, 3, 5, 5));
        this.panelS = new JPanel();
        


        this.slider = new JSlider(0, 100, 10);
        this.slider.setPaintTrack(true);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.setMajorTickSpacing(20);
        this.slider.setMinorTickSpacing(5);
        this.slider.setPaintLabels(true);
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label.setText("speed = " + slider.getValue()) ;
            }
        });
        label.setText("speed = " + slider.getValue()) ;

        
        this.draw_btn = new CusButton("Draw square");
        this.draw_btn.addActionListener(this);
        this.panelB.add(draw_btn);

        this.put_ant_btn = new CusButton("Put an ant");
        this.put_ant_btn.addActionListener(this);
        this.panelB.add(put_ant_btn);

        this.delete_ant_btn = new CusButton("Delete an ant");
        this.delete_ant_btn.addActionListener(this);
        this.panelB.add(delete_ant_btn);

        this.clear_btn = new CusButton("Clear All");
        this.clear_btn.addActionListener(this);
        this.panelB.add(clear_btn);

        this.stop_btn = new CusButton("Start");
        this.stop_btn.addActionListener(this);
        this.panelB.add(stop_btn);
        this.stop_btn.changePressed();
        
        this.drunk_mode_btn = new CusButton("Drunk Mode");
        this.drunk_mode_btn.addActionListener(this);
        this.panelB.add(drunk_mode_btn);
        
        this.panelS.add(slider);
        this.panelS.add(label);
        
        this.add(panelB);
        this.add(panelS);
        
    }

    public boolean isPuttingAnt() {
        return this.put_ant_btn.isPressed();
    }

    public boolean isDeleting() {
        return this.delete_ant_btn.isPressed();
    }

    public boolean isStopped() {
        return this.stop_btn.isPressed();
    }

    public boolean isDrawing() {
        return this.draw_btn.isPressed();
    }

    public boolean isDrunk() {
        return this.drunk_mode_btn.isPressed();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.clear_btn) {
            for (int i = 0; i < Ant_Util.Grid_Height; ++ i) {
                for (int j = 0; j < Ant_Util.Grid_Width; ++ j) {
                    grid.setColor(i, j, Ant_Util.white);
                }
            }
        } else if (e.getSource() == this.put_ant_btn) {
            if (isPuttingAnt()) {
                this.grid.setCursor(Cursor.getDefaultCursor());
                this.put_ant_btn.setText("Put an ant");
                this.disabling = true;
            } else {
                this.grid.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                this.put_ant_btn.setText("end putting");
                this.disabling = false;
            }
            this.put_ant_btn.changePressed();
            //disabling the draw and delete-ant buttons when put-ant is activated
            this.draw_btn.setEnabled(disabling);
            this.delete_ant_btn.setEnabled(disabling);

        } else if (e.getSource() == this.delete_ant_btn) {
            if (isDeleting()) {
                this.grid.setCursor(Cursor.getDefaultCursor());
                this.delete_ant_btn.setText("Delete an ant");
                this.disabling = true;
            } else {
                this.grid.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                this.delete_ant_btn.setText("end deleting");
                this.disabling = false;
            }
            this.delete_ant_btn.changePressed();
            //disabling the draw and put-ant button when delete-ant is activated
            this.draw_btn.setEnabled(disabling);
            this.put_ant_btn.setEnabled(disabling);

        } else if (e.getSource() == this.stop_btn) {
            if (isStopped()) {
                //this.grid.setCursor(Cursor.getDefaultCursor());
                this.stop_btn.setText("Stop");
            } else {
                //this.grid.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                this.stop_btn.setText("Resume");
            }
            this.stop_btn.changePressed();
            //System.out.println(this.isStopped());
        } else if (e.getSource() == this.draw_btn) {
            if (isDrawing()) {
                this.grid.setCursor(Cursor.getDefaultCursor());
                this.draw_btn.setText("Draw square");
                this.disabling = true;
            } else {
                this.grid.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                this.draw_btn.setText("end drawing");
                this.disabling = false;
            }
            this.draw_btn.changePressed();
            this.put_ant_btn.setEnabled(disabling);
            this.delete_ant_btn.setEnabled(disabling);
        }else if(e.getSource() == this.drunk_mode_btn){
            if (isDrunk()) {
                //this.grid.setCursor(Cursor.getDefaultCursor());
                this.drunk_mode_btn.setText("Drunk Mode");
            } else {
                //this.grid.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                this.drunk_mode_btn.setText("Normal mode");
            }
            this.drunk_mode_btn.changePressed();
        }
    }
}
