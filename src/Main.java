import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
    /*
    MENU:
    -titolo
    -sottotitolo, istruzioni
    -bottoni di selezione:
        -calcola un numero casuale in un range
        -sequenza LCG
        -formica di langton
    */

    public static void main(String[] args) {
        
        JFrame frame = new JFrame();

        frame.setTitle("Pseudo Random Number Generator Project");
        frame.setSize(new Dimension(550, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new MenuPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
