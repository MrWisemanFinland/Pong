
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javafx.application.Application;
import javax.swing.border.EmptyBorder;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mikko
 */
public class Valinta extends JFrame implements ActionListener {

    public static int valinta;
    public JRadioButton one, two, three;
    public JButton go;
    
    public Valinta() {
        
        this.setTitle("Pong!");
        JLabel teksti = new JLabel("Please choose game option:");
        
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel valintaruutu = new JPanel(new FlowLayout());
        this.add(valintaruutu);
        valintaruutu.setBorder(new EmptyBorder(20, 20, 20, 20));
        valintaruutu.setLayout(new BoxLayout(valintaruutu, BoxLayout.PAGE_AXIS));
        one = new JRadioButton("Human vs. human");
        two = new JRadioButton("Human vs. computer");
        three = new JRadioButton("Computer vs. computer");
        
        ButtonGroup pelivalinta = new ButtonGroup();
        
        pelivalinta.add(one);
        pelivalinta.add(two);
        pelivalinta.add(three);
        
        valintaruutu.add(teksti);
        valintaruutu.add(one);

        valintaruutu.add(two);

        valintaruutu.add(three);

        one.setSelected(true);
        JButton go = new JButton("OK");
        go.addActionListener(this);
        
        valintaruutu.add(go);

        this.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (one.isSelected()) {
            valinta = 1;
            this.setVisible(false);
            Application.launch(Pong.class);
        }
        if (two.isSelected()) {
            valinta = 2;
            this.setVisible(false);
            Application.launch(Pong.class);
        }
        if (three.isSelected()) {
            valinta = 3;
            this.setVisible(false);
            Application.launch(Pong.class);
        }
        
    }





public static void main(String[] args) {
        new Valinta();
        
    }

}
