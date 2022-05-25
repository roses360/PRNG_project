import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener{

    private JPanel panel;
    private CusButton random_btn = null,
                    LCG_btn = null,
                    ant_btn = null;
    private String message = "\t\t\t\t\t\t\tHi there!\n"+
                            "This program shows how pseudorandom numbers \n"+
                            " and sequences can be created by particular \n"+
                            "algorithms. "+
                            "Furthermore, it allows you to analyze \n"+
                            "an example of chaos theory with a simulation \n"+
                            "of the famous Langton's ant. \n"+
                            "\n"+
                            " You can choose what to do by clicking with the \n"+
                            "mouse on the buttons below. \n\n";
                            
    Font font = new Font("SansSerif", Font.BOLD, 17);

    public MenuPanel(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //title
        JLabel title = new JLabel("PSEUDO RANDOM NUMBER GENERATOR", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 19));
        JPanel t = new JPanel();
        t.setLayout(new BorderLayout());
        t.add(title, BorderLayout.NORTH);
        t.add(new JLabel(" "), BorderLayout.CENTER);    //a little space between title and text
        panel.add(t, BorderLayout.NORTH);

        //message
        JPanel mes = new JPanel();
        mes.setLayout(new BorderLayout());
        JLabel lmes = new JLabel();
        lmes.setText(TxtToHTML.escape(message));
        lmes.setFont(font);
        mes.add(lmes, BorderLayout.CENTER);
        mes.setBorder(new EmptyBorder(5, 10, 5, 10));
        panel.add(mes, BorderLayout.CENTER);

        //buttons
        JPanel btns = new JPanel();
        btns.setLayout(new GridLayout(0,1, 20, 10));

        random_btn = new CusButton("Random number generator");
        random_btn.setFont(font);
        random_btn.addActionListener(this);
        btns.add(random_btn);

        LCG_btn = new CusButton("Linear Congruential Generator (LCG)");
        LCG_btn.setFont(font);
        LCG_btn.addActionListener(this);
        btns.add(LCG_btn);

        ant_btn = new CusButton("Langton's Ant");
        ant_btn.setFont(font);
        ant_btn.addActionListener(this);
        btns.add(ant_btn);

        panel.add(btns, BorderLayout.SOUTH);

        //add components
        this.add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == random_btn){

            new Random_app();

        } else if(e.getSource() == LCG_btn){

            new LCG_App();

        } else if(e.getSource() == ant_btn){

            new Ant_App();

        }     
        
    }
    
}
