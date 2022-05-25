package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=31;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoAutomata photo;
    private CellularAutomata automata;

    private AutomataGUI() {
        automata=new CellularAutomata();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Automata celular");
        photo=new PhotoAutomata(this);
        buttonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(buttonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE,SIDE*SIZE+50)); 
        setResizable(false);
        photo.repaint();
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        buttonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonTicTacAction();
                }
            });

    }

    private void buttonTicTacAction() {
        automata.ticTac();
        photo.repaint();
    }

    public CellularAutomata getAutomata(){
        return automata;
    }
    
    public static void main(String[] args) {
        AutomataGUI ca=new AutomataGUI();
        ca.setVisible(true);
    }  
}

class PhotoAutomata extends JPanel{
    private AutomataGUI gui;

    public PhotoAutomata(AutomataGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE, gui.SIDE*gui.SIZE));         
    }


    public void paintComponent(Graphics g){
        CellularAutomata automata=gui.getAutomata();
        super.paintComponent(g);
         
        for (int f=0;f<=automata.getLength();f++){
            g.drawLine(f*gui.SIDE,0,f*gui.SIDE,automata.getLength()*gui.SIDE);
        }
        for (int c=0;c<=automata.getLength();c++){
            g.drawLine(0,c*gui.SIDE,automata.getLength()*gui.SIDE,c*gui.SIDE);
        }       
        for (int f=0;f<automata.getLength();f++){
            for(int c=0;c<automata.getLength();c++){
                if (automata.getItem(f,c)!=null){
                    g.setColor(automata.getItem(f,c).getColor());
                    if (automata.getItem(f,c).shape()==Item.SQUARE){                  
                        if (automata.getItem(f,c).isAlive()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }else{
                            g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);    
                        }
                    }else {
                        if (automata.getItem(f,c).isAlive()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } else {
                            g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        }
                    }
                }
            }
        }
    }
}