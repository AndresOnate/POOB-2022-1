package Shapes;


import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Figure{
    public static final double PI=3.1416;
    private int diameter;
   
    /**
     * Constructor for objects of class Circle
     */
    public Circle(int positionX, int positionY, String newColor){
        super(positionX, positionY, newColor);
        diameter = 25;
    }
       
    public void draw(){
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(), 
                new Ellipse2D.Double(getXPosition(), getYPosition(), 
                diameter, diameter));
            canvas.wait(10);
        }
    }
}
