package Shapes;

import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */

public class Rectangle extends Figure {
    public static int EDGES = 4;    
    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(int dimensionX, int dimensionY, String color){
        super(0,0,color);
        height = dimensionY;
        width = dimensionX;
    }
    
    public Rectangle(int dimensionX, int dimensionY){
        super(0,0,"blue");
        height = dimensionY;
        width = dimensionX;
    }
    
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    public Shape Form(){
        return new java.awt.Rectangle(getXPosition(), getYPosition(), width + 25, height + 25);                                   
    }
    
    public void draw() {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas(this.height + 25, this.width + 25);
            canvas.draw(this, getColor(),
                new java.awt.Rectangle(getXPosition(), getYPosition(), 
                                       width + 25, height + 25));
            canvas.wait(10);
        }
    }


}

