import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final float PI=3.1416f;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private float area;
    

    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
       
    /**
     * Determine the area of the circle.
     */
    public float areaCircle(){
        

        area = (diameter / 2)*(diameter / 2) * PI;
        return area;
    }
        
    /**
     * Double the area of the circle
     */
    public float doubleArea(){
        area = 2 * areaCircle();
        diameter = 2 * (int)Math.sqrt(area / PI);
        return area;
    }
    
    /**
     * Find the volume of a cylinder with a circular base and height h.
     * @h cylinder height
     */
    public float Volumen(float h){
        float volumen = 0;
        volumen = h*areaCircle();
        return volumen;
    }
        
    /**
     * Makes the circle visible.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
      
    /**
     * Makes the circle invisible.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Change the color.
     * It changes color following the colors of the Rainbow.
     * color the new color. Valid colors are "red", "yellow", "blue", "green",
     */
    public void rainbow(){
        for (int i = 0; i<= 100; i++) {
            color = "yellow";
            draw();
            color = "red";
            draw();
            color = "blue";
            draw();
            color = "green";
            draw();
            color =  "magenta";
            draw();
        }
        }
    }
