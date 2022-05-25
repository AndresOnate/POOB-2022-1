package presentacion;

import javax.swing.*;
import java.awt.*;

public class House extends JPanel {

    private int semillas;
	private char miTipo;

    public House(int Semillas, char type){
        this.setLayout(new GridLayout(Semillas/3,3));
        this.semillas = Semillas;
		this.miTipo = type;
        for (int i = 0; i < semillas; i++ ){
            Seed seed = new Seed(this, type);
            add(seed);
        }
    }
	public void refresh (int newSeedStock){
		this.removeAll();
		this.setLayout(new GridLayout((int) newSeedStock/3,3));
        this.semillas = newSeedStock;
        for (int i = 0; i < newSeedStock; i++ ){
            Seed seed1 = new Seed(this, this.miTipo);
            add(seed1);
		}
		this.repaint();
	}
	
	public int getSeedsSaved(){
		return this.semillas;
	}

}
