import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LCG_App implements ActionListener{

    Font font = new Font("SansSerif", Font.BOLD, 15);

    private JFrame frame;
    private JTextField at, ct, mt, lt, st, output;
    JButton submitButton;


    String message =    " In mathematics, the \"linear congruential generator\" \n"+
                        "(LCG) is an old and well known algorithm for the \n"+
                        "pseudo-random numbers generators. \n\n "+
                        "\tThe Algorithm is:   x(n+1) = [a * x(n) + c] % m \n\n" +
                        " The period of an LCG is at most \"m\", and for some \n"+
                        "choices of \"a\" it can be much smaller.\n"+
                        "The LCG has a full period if and only if: \n" +
                        "\t> c and m are coprime \n"+
                        "\t> a-1 is divisible by all prime factors of m, \n"+
                        "\t> a-1 is a multiple of 4 if m is a multiple of 4.\n\n";

    public int a = 5, c = 1, m = 16, seed = 0, length = 10;

    public LCG_App(){

        frame = new JFrame("Linear Congruential Generator (LCG)");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());

        //title
        JLabel title = new JLabel("Linear Congruential Generator (LCG)", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 19));
        JPanel t = new JPanel();
        t.setLayout(new BorderLayout());
        t.add(title, BorderLayout.NORTH);
        t.setBorder(new EmptyBorder(5, 5, 10, 5));
        finalPanel.add(t, BorderLayout.NORTH);

        //description
        JPanel description = new JPanel();
        description.setLayout(new BorderLayout());
        JLabel ldes = new JLabel();
        ldes.setText(TxtToHTML.escape(message));
        ldes.setFont(font);
        description.add(ldes, BorderLayout.CENTER);
        description.setBorder(new EmptyBorder(5, 25, 5, 5));
        //description.add(new JLabel(" "), BorderLayout.CENTER);
        finalPanel.add(description, BorderLayout.CENTER);

        
        //body
            
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());

        at = new JTextField(10);
        ct = new JTextField(10);
        mt = new JTextField(10);
        lt = new JTextField(10);
        st = new JTextField(10);
        output = new JTextField(40);
        output.setEditable(false);
        submitButton = new JButton("Submit");

            // Add labelled input fields to display
            JPanel inFieldPane = new JPanel();
            inFieldPane.setLayout(new GridLayout(3,4, 5, 5));

            inFieldPane.add(new JLabel("Constant a: "));
            inFieldPane.add(at);
            at.setText(String.valueOf(a));
            at.addActionListener(this);

            inFieldPane.add(new JLabel("Constant c: "));
            inFieldPane.add(ct);
            ct.setText(String.valueOf(c));
            ct.addActionListener(this);

            inFieldPane.add(new JLabel("Constant m: "));
            inFieldPane.add(mt);
            mt.setText(String.valueOf(m));
            mt.addActionListener(this);

            inFieldPane.add(new JLabel("Constant l: "));
            inFieldPane.add(lt);
            lt.setText(String.valueOf(length));
            lt.addActionListener(this);

            inFieldPane.add(new JLabel("Insert seed: "));
            inFieldPane.add(st);
            st.setText(String.valueOf(seed));
            st.addActionListener(this);



            body.add(inFieldPane, BorderLayout.NORTH);

            // Add submission button
            JPanel submitPane = new JPanel();
            submitPane.setLayout(new FlowLayout());
            submitPane.add(new JLabel("Press Button to Create Sequence"));
            submitButton.addActionListener(this);
            submitPane.add(submitButton);
            body.add(submitPane, BorderLayout.CENTER);
            
            // Add Output fields
            JPanel outFieldPane = new JPanel();
            outFieldPane.setLayout(new GridLayout(2,1));
            outFieldPane.add(new JLabel("Sequence Created:"));
            outFieldPane.add(output);
            body.add(outFieldPane, BorderLayout.SOUTH);

        finalPanel.add(body, BorderLayout.SOUTH);

        // Display the final product
        finalPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        frame.add(finalPanel);
        frame.pack();
        frame.setVisible(true);

    }

    public int[] generator(){
        int sequence[] = new int[length];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = (a * seed + c) % m;
            seed = sequence[i];
        }
        return sequence;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton)
        {
            a = Integer.parseInt(at.getText().trim());
            c = Integer.parseInt(ct.getText().trim());
            m = Integer.parseInt(mt.getText().trim());
            length = Integer.parseInt(lt.getText().trim());
            seed = Integer.parseInt(st.getText().trim());

            int seq[] = generator();
            
            String out = "";
            for (int i = 0; i < seq.length; i++) {
                out += seq[i];

                if(i != seq.length - 1){
                    out += " - ";
                }
            }

            output.setText(out);
        }
    }
}