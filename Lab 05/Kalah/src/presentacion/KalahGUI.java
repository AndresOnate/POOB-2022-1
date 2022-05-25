package presentacion;
import dominio.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

public class KalahGUI extends JFrame {
	private int numHouses = 6;
	private int numSeeds =  3;
	
	/*Game logic*/
	private KalahGame game;
    /*Menu Elements*/
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;
	private JMenuItem chgColor;
	private JMenuItem chgNumSeeds;
	private JMenuItem chgNumHouses;
	/*Design Elements*/
	private JPanel panelTablero;
	private JPanel panelInfoJugadorUno;
	private JPanel panelInfoJugadorDos;
	private JPanel panelOpcionesJuego;
	private JLabel infoPlayer1;
	private JLabel infoPlayer2;
	private JLabel turnPlayer1;
	private JLabel turnPlayer2;
	private String turnOn = "Your turn";
	private String turnOff = "Wait until your rival play";
	/*panelOpcionesJuego*/
	private JButton endGame;
	private JButton reStart;
	/*panelTablero*/
	private JPanel casas;
	private House[][] housesList;
	private House[] almacenes = new House[2];
	private JButton[][] botonesTablero;
    //Propiedades tablero
    private Color backgroundColor = Color.WHITE;
    private Color borderColor = Color.BLACK;
	
	
	


    public KalahGUI() throws KalahGameException{
		game = new KalahGame(this.numHouses,this.numSeeds);
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        prepareElementsMenu();
		prepareElementsBoard();
        //Pantalla principal
        setTitle("Kalah");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width/2, height/2);
        setLocationRelativeTo(null);
    }

    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		salvar = new JMenuItem("Salvar");
		salir = new JMenuItem("Salir");

		chgColor = new JMenuItem("Cambiar de color");
		JMenu configuracion = new JMenu("Configuracion");
		chgNumHouses =  new JMenuItem("Cambiar numero de casas");
		chgNumSeeds =  new JMenuItem("Cambiar numero de semillas");

        menu.add(archivo);
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(salvar);
        archivo.add(salir);

		menu.add(configuracion);
		configuracion.add(chgNumSeeds);
		configuracion.add(chgNumHouses);
		configuracion.add(chgColor);
        setJMenuBar(menu);
    }
	
	private void prepareElementsBoard(){
        this.setLayout(new GridLayout(2,1));
        JPanel mainPanel = new JPanel(new GridLayout(1,3));
        panelTablero = new JPanel(new GridLayout(1,3,15,10));
		panelInfoJugadorUno = new JPanel(new GridLayout(2,1));
		panelInfoJugadorDos = new JPanel(new GridLayout(2,1));
		panelOpcionesJuego = new JPanel();
        add(elementsPanelTablero());
		mainPanel.add(elementsPanelInfoJugadorDos());
		mainPanel.add(elementsPanelOpcionesJuego());
		mainPanel.add(elementsPanelInfoJugadorUno());
        add(mainPanel);
	}
	
	
	private JPanel elementsPanelOpcionesJuego(){
		endGame = new JButton("Finish");
		reStart = new JButton("Restart");
		GridBagConstraints buttonDesign1 = new GridBagConstraints();
		buttonDesign1.fill = GridBagConstraints.HORIZONTAL;
		buttonDesign1.ipady = 25;
		buttonDesign1.ipadx = 100;
		buttonDesign1.weightx = 0.0;
		buttonDesign1.gridwidth = 3;
		buttonDesign1.gridx = 0;
		buttonDesign1.gridy = 3;
		GridBagConstraints buttonDesign2 = new GridBagConstraints();
		buttonDesign2.fill = GridBagConstraints.HORIZONTAL;
		buttonDesign2.ipady = 25;
		buttonDesign2.ipadx = 100;
		buttonDesign2.weightx = 0.0;
		buttonDesign2.gridwidth = 3;
		buttonDesign2.gridx = 0;
		buttonDesign2.gridy = 0;
		panelOpcionesJuego.setLayout(new GridBagLayout());
		panelOpcionesJuego.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5)
												,new TitledBorder("Game Options")));
		panelOpcionesJuego.add(reStart, buttonDesign1);
		panelOpcionesJuego.add(endGame, buttonDesign2);
		return panelOpcionesJuego;
	}
	
	
	private JPanel elementsPanelTablero(){
		panelTablero.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),
													new TitledBorder("Kalah")));
        /*Espacio de las casas en el tablero*/
		int housesValue = this.numHouses;
		int seedsValue = this.numSeeds;
		housesList = new House[2][housesValue];
        casas = new JPanel(new GridLayout(4,housesValue, 10,10));
        House almanen1 = new House(0, 'A');
        House almanen2 = new House(0, 'A');
		botonesTablero = new JButton[2][housesValue];
		
		for (int j = 0; j < housesValue; j++){
			JButton current = new JButton("2 - " + (j + 1));
			botonesTablero[0][j] = current;
			casas.add(current);
		}
        for (int i = 0; i < housesValue*2; i++){
            House house = new House(seedsValue, 'C');
            house.setBorder(BorderFactory.createLineBorder(borderColor,1));
            casas.add(house);
			if (i < housesValue){
				housesList[0][i] = house;
			}else{
				housesList[1][i - housesValue] = house;
			}
        }
		for (int j = 0; j < housesValue; j++){
			JButton current = new JButton("1 - " + (j + 1));
			botonesTablero[1][j] = current;
			casas.add(current);
		}
		almanen1.setBorder(BorderFactory.createLineBorder(borderColor,1));
		almanen2.setBorder(BorderFactory.createLineBorder(borderColor,1));
        panelTablero.add(almanen1);
        panelTablero.add(casas);
        panelTablero.add(almanen2);
		almacenes[0] = almanen1;
		almacenes[1] = almanen2;
        return panelTablero;
	}
	
	
	private JPanel elementsPanelInfoJugadorUno(){
		infoPlayer1 = new JLabel("Number Of Seeds: " + game.getSeedsInStore(1));
		turnPlayer1 = new JLabel(turnOn);
		infoPlayer1.setFont(new Font("Verdana",1,13));
		turnPlayer1.setFont(new Font("Verdana",1,13));
		panelInfoJugadorUno.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5)
												,new TitledBorder("Player 1")));
		panelInfoJugadorUno.add(infoPlayer1);
		panelInfoJugadorUno.add(turnPlayer1);
        return panelInfoJugadorUno;
	}
	
	
	private JPanel elementsPanelInfoJugadorDos(){
		infoPlayer2 = new JLabel("Number Of Seeds: " + game.getSeedsInStore(0));
		turnPlayer2 = new JLabel(turnOff);
		infoPlayer2.setFont(new Font("Verdana",1,13));
		turnPlayer2.setFont(new Font("Verdana",1,13));
		panelInfoJugadorDos.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5)
												,new TitledBorder("Player 2")));
		panelInfoJugadorDos.add(infoPlayer2);
		panelInfoJugadorDos.add(turnPlayer2);
        return panelInfoJugadorDos;
	}


	private void prepareActions() throws KalahGameException{
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
        /* Preparar menu */
        prepareActionsMenu();
		prepareBoardActions();
		prepareGameOptionsActions();
    }
	
	
	private void prepareBoardActions(){
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < game.getNumberOfHouses(); j++){
				int id = j;
				int actualPlayer = i;
				botonesTablero[i][j].addActionListener(e -> actionPlayThisHouse(actualPlayer, id));
			}
		}
	}
	
	
    private void prepareActionsMenu(){
		nuevo.addActionListener(e -> actionEndThisGame());
        salir.addActionListener(e -> actionExit());
        salvar.addActionListener(e -> actionSave());
        abrir.addActionListener(e -> actionOpen());
		chgColor.addActionListener(e -> actionChgColor());
		chgNumHouses.addActionListener(e -> actionChangeNumHouses());
		chgNumSeeds.addActionListener(e -> actionChangeNumSeeds());
		
    }

	
	private void prepareGameOptionsActions(){
		reStart.addActionListener(e -> actionRestartThisGame());
		endGame.addActionListener(e -> actionEndThisGame());
	}


	private void actionExit(){
        int valor = JOptionPane.showConfirmDialog(this, "Exit game?", "Warning", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

	private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this, "Esta funcionalidad esta en construccion," +
                    " usted esta intentando guardar el archivo " + name);
        }
    }

	private void actionOpen() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this, "Esta funcionalidad esta en construccion," +
                    " usted intento abrir el archivo " + name);
        }
    }

	private void actionChgColor (){
		Color newColor = JColorChooser.showDialog(this, "Choose a color", this.getBackground());
		if (newColor != null){
			// Tablero de juego
			panelTablero.setBackground(newColor);
			panelTablero.repaint();
			// Informacion jugadores
			panelInfoJugadorUno.setBackground(newColor);
			panelInfoJugadorUno.repaint();
			panelInfoJugadorDos.setBackground(newColor);
			panelInfoJugadorDos.repaint();
			// Opciones del juego
			panelOpcionesJuego.setBackground(newColor);
			panelOpcionesJuego.repaint();
		}
	}


	private void actionPlayThisHouse(int actualPlayer,int houseId){
		try{
			game.play(houseId, actualPlayer);
			refresh();
		} catch (KalahGameException e){
			JOptionPane.showMessageDialog(null, KalahGameException.PLAYER_TURN);
		}
	}

	private  void actionChangeNumHouses() {
		String nHouses= JOptionPane.showInputDialog(this, "Digite el nuevo numero de casas");
		if (nHouses != null) {
			try {
				int Houses = Integer.parseInt(nHouses);
				game = new KalahGame(Houses,this.numSeeds);
				this.numHouses = Houses;
				panelTablero.removeAll();
				panelTablero.revalidate();
				panelTablero.repaint();
				panelTablero = elementsPanelTablero();
				prepareBoardActions();
				refresh();
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "El número de casas debe ser un numero");
			}catch (KalahGameException e) {
				JOptionPane.showMessageDialog(null, KalahGameException.RANGE_HOUSES);
			}
		}
	}

	private  void actionChangeNumSeeds() {
		String nSeed = JOptionPane.showInputDialog(this, "Digite el nuevo numero de casas");
		if (nSeed != null) {
			try {
				int seeds = Integer.parseInt(nSeed);
				game.setNumSeeds(seeds);
				this.numSeeds = seeds;
				panelTablero.revalidate();
				panelTablero.repaint();
				refresh();
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "El número de semillas debe ser un numero");
			}catch (KalahGameException e) {
				JOptionPane.showMessageDialog(null, KalahGameException.RANGE_SEEDS);
			}
		}
	}

	private  void refresh(){
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < game.getNumberOfHouses(); j++){
				housesList[i][j].refresh(game.getSeedsPlayer(j,i));
				if (housesList[i][j].getSeedsSaved() == 0){
					botonesTablero[i][j].setVisible(false);
				}else{
					botonesTablero[i][j].setVisible(true);
				}
			}
		}
		
		for (int k = 0; k < 2; k++){
			almacenes[k].refresh(game.getSeedsInStore(k));
		}
		
		if (game.player() == 1){
			turnPlayer1.setText(turnOn);
			turnPlayer2.setText(turnOff);
		}else{
			turnPlayer1.setText(turnOff);
			turnPlayer2.setText(turnOn);
		}
		
		infoPlayer1.setText("Number Of Seeds: " + game.getSeedsInStore(1));
		infoPlayer2.setText("Number Of Seeds: " + game.getSeedsInStore(0));
		
		if (game.isOverGame()){
			messageEndGame();
		}
	}


	private void actionEndThisGame(){
		int confirmation = JOptionPane.showConfirmDialog(this, "End the current macth?", "Warning", JOptionPane.YES_NO_OPTION);
		if (confirmation == JOptionPane.YES_OPTION){
			messageEndGame();
		}
	}


	private void actionNewGame(){
		int confirmation = JOptionPane.showConfirmDialog(this, "Create a new game?", "Warning", JOptionPane.YES_NO_OPTION);
		if (confirmation == JOptionPane.YES_OPTION){
			actionRestartThisGame();
		}
	}
	
	
	private void messageEndGame() {
		if (game.getSeedsInStore(1) > game.getSeedsInStore(0)) {
			JOptionPane.showMessageDialog(null, "The player 1 have won with " + game.getSeedsInStore(1) + " seeds!");
		} else if(game.getSeedsInStore(1) == game.getSeedsInStore(0)){
			JOptionPane.showMessageDialog(null, "Both players tied");
		}else{
			JOptionPane.showMessageDialog(null, "The player 2 have won with " + game.getSeedsInStore(0) + " seeds!");
		}
		int confirmation = JOptionPane.showConfirmDialog(this, "Want to start other match?", "", JOptionPane.YES_NO_OPTION);
		if(confirmation == JOptionPane.YES_OPTION){
			actionRestartThisGame();
		}else{
			System.exit(0);
		}
	}


	private void actionRestartThisGame(){
		try{
			game = new KalahGame(this.numHouses,this.numSeeds);
			this.revalidate();
			this.repaint();
			refresh();
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Something went wrong, please close the window and try open it again");
		}
	}


    public static void main(String args[]) throws KalahGameException{
        KalahGUI gui = new KalahGUI();
        gui.setVisible(true);
    }
}
