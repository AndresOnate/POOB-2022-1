package Shapes;

import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends Figure{   
    public static int VERTICES=3;  
    private int height;
    private int width;
    private double rotAngle;
    private double pendiente = 1;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(String newColor, int x, int y, double angle,int newHeight, int newWidth ){
        super(x,y,newColor);
        height = newHeight;
        width = newWidth;
        rotAngle = angle;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /*
     * Draw the triangle with current specifications on screen.
     */
    public  void draw(){
        if(isVisible()) {
            int xPosition = getXPosition();
            int yPosition = getYPosition();
            String color = getColor();
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            int x, y;
            for (int i = 0; i < 3; i++) {
                x = xpoints[i] - xPosition ;
                y = ypoints[i] - yPosition;
                xpoints[i] = yPosition + (int) Math.round(((x * Math.cos(Math.PI - rotAngle)) - (y * Math.sin(Math.PI - rotAngle))));
                ypoints[i] = xPosition + (int) Math.round(((x * Math.sin(Math.PI - rotAngle)) + (y * Math.cos(Math.PI - rotAngle)))); 
            if ((xpoints[2] -xpoints[1]) != 0){
                pendiente = (ypoints[2]-ypoints[1]) /(xpoints[2] -xpoints[1]);
            }else{
                pendiente = 1;
            }        
            canvas.draw(this, color, new Polygon(ypoints,  xpoints,3));
            canvas.wait(10);
        }
      
        }
    }
    
    /**
     * Set RotAngle of the tourist
     */
    public void setRotAngle(double angle){
        rotAngle = -angle;
        
    }
    
    public double getPendiente(){
        return pendiente;
        
    }     
}