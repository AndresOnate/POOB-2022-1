package presentation;
import domain.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class AutomataGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=31;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoAutomata photo;
    private CellularAutomata automata;

    private JMenuBar menu;
    private JMenu opciones;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardarComo;
    private JMenuItem importar;
    private JMenuItem exportarComo;
    private JMenuItem salir;


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
        prepareElementsMenu();
        photo.repaint();
    }

    private void prepareElementsMenu(){
        // Implementacion de un menu
        menu = new JMenuBar();
        // Extensiones del menu
        opciones = new JMenu("Opciones");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardarComo = new JMenuItem("Guardar Como...");
        importar = new JMenuItem("Importar");
        exportarComo = new JMenuItem("Exportar Como...");
        salir = new JMenuItem("Salir");
        opciones.add(nuevo);
        opciones.addSeparator();
        opciones.add(abrir);
        opciones.add(guardarComo);
        opciones.add(importar);
        opciones.add(exportarComo);
        opciones.addSeparator();;
        opciones.add(salir);
        opciones.addSeparator();;
        menu.add(opciones);
        setJMenuBar(menu);
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        buttonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonTicTacAction();
                }
            });
        prepareActionsMenu();
    }

    private void prepareActionsMenu(){
        nuevo.addActionListener(e -> actionNew());
        abrir.addActionListener(e -> actionOpen());
        salir.addActionListener(e -> actionExit());
        exportarComo.addActionListener(e -> actionExport());
        importar.addActionListener(e -> actionImport());
        guardarComo.addActionListener(e -> actionSave());
    }

    private void buttonTicTacAction() {
        automata.ticTac();
        photo.repaint();
    }

    public CellularAutomata getAutomata(){
        return automata;
    }

    private void actionNew(){
         automata = new CellularAutomata();
         this.revalidate();
         this.repaint();
    }

    private void updateAutomata(CellularAutomata newAutomata){
        this.automata = newAutomata;
        photo.setGUI(this);
        photo.repaint();
    }

    private void actionOpen(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter( ".dat","dat");
            fileChooser.setDialogTitle("Abrir");
            fileChooser.setFileFilter(filter);
            int selection = fileChooser.showSaveDialog(this);
            if (selection == JFileChooser.APPROVE_OPTION) {
                CellularAutomata newAutomata = automata.open(fileChooser.getSelectedFile());
                this.automata = newAutomata;
                photo.repaint();
            }
        }catch (AutomataException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void actionSave(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter( ".dat","dat");
            fileChooser.setDialogTitle("Guardar");
            fileChooser.setFileFilter(filter);
            int selection = fileChooser.showSaveDialog(this);
            if (selection == JFileChooser.APPROVE_OPTION) {
                automata.save(fileChooser.getSelectedFile());
            }
        }catch (AutomataException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void actionImport(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
            fileChooser.setDialogTitle("Importar");
            fileChooser.setFileFilter(filter);
            int selection = fileChooser.showOpenDialog(this);
            if (selection == JFileChooser.APPROVE_OPTION) {
                CellularAutomata newAutomata = CellularAutomata.importFile(fileChooser.getSelectedFile());
                this.automata = newAutomata;
                photo.repaint();
            }
        } catch (AutomataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actionExport() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
            fileChooser.setDialogTitle("Exportar");
            fileChooser.setFileFilter(filter);
            int selection = fileChooser.showSaveDialog(this);
            if (selection == JFileChooser.APPROVE_OPTION) {
                automata.export(fileChooser.getSelectedFile());
            }
        } catch (AutomataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actionExit() {
        System.exit(0);
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

    public void setGUI(AutomataGUI gui){
        this.gui = gui;
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