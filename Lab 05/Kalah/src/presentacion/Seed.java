package presentacion;

import javax.swing.*;
import java.awt.*;

public class Seed extends JPanel {

    private House housePropia;
	private char miTipoCasa;

    public Seed(House house, char type){
        this.housePropia = house;
		this.miTipoCasa = type;
    }

    public void paintComponent (Graphics g) {
		if (miTipoCasa == 'C'){
			super.paintComponent(g);
			int x = this.getWidth() / 2;
			int y = this.getHeight() / 2;
			int dx = x / 2;
			int dy = y / 2;
			g.drawOval(dx, dy, x, x);
		} else{
			super.paintComponent(g);
			int x = this.getWidth() / 4;
			int y = this.getHeight() / 4;
			int dx = x / 2;
			int dy = y / 2;
			g.drawOval(dx, dy, x, x);
		}
    }
}
