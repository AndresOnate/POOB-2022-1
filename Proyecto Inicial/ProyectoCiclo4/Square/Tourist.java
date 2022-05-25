package Square;
import Shapes.*;


/**
 * Write a description of class Tourist here.
 * 
 * @author Nicolas Ariza, Andres Oñate
 * @version 1.0 (07 february 2022)
 */
public class Tourist
{
    private String color;
    private int xPosition;
    private int yPosition;
    private double viewAngle;
    private boolean visibility;
    private Triangle tourist;
    private int safeDistance;
    protected String type;
    
    /**
     * Constructor for objects of class Tourist
     */
    public Tourist(String newColor, int positionX, int positionY, int angle, int newSafeDistance)
    {
        tourist = new Triangle(newColor, positionX, positionY, angle, 25,15);
        xPosition = positionX;
        yPosition = positionY;
        viewAngle = angle;
        color = newColor;
        safeDistance = newSafeDistance;
        type = "normal";
    }
    
    public Tourist(String newColor, int positionX, int positionY, int angle, int newSafeDistance, String Newtype)
    {
        tourist = new Triangle(newColor, positionX, positionY, angle, 25,15);
        xPosition = positionX;
        yPosition = positionY;
        viewAngle = angle;
        color = newColor;
        safeDistance = newSafeDistance;
        type = Newtype;
    }
    
    /**
     * Constructor for objects of class Tourist
     */
    public Tourist(String newColor, int positionX, int positionY, double angle, int newHeight, int newWidth)
    {
        tourist = new Triangle(newColor, positionX, positionY, angle, newHeight, newWidth);
        xPosition = positionX;
        yPosition = positionY;
        viewAngle = angle;
        color = newColor;
    }
     
    /**
     * Get position x of the tourist
     */
    public int getxPosition(){
        return xPosition;
    }
    
    /**
     * Get position y of the tourist
     */
    public int getyPosition(){
        return yPosition;
    }
    
    /**
     * Makes the tourist visible
     */
    public void makeVisible(){
        visibility = true;
        tourist.makeVisible();
    }
    
    /**
     * Makes the tourist invisible
     */
    public void makeInvisible(){
        visibility = true;
        tourist.makeInvisible();
    }
    
    /**
     * Set position x,y of the tourist
     */
    public void setPosition(int x, int y){
        xPosition = x;
        yPosition = y;
        tourist.moveTo(x,y);
            
    }
        
    /**
     * Set Angle of the tourist
     */
    public void setAngle(double angle){
        viewAngle = angle;
        tourist.setRotAngle(angle);
    }
    
    /**
     * Get Angle of the tourist
     */
    public double getAngle(){
        return viewAngle;

    }
    
    /**
     * Get Color (ID) of the tourist
     */
    public String getColor(){
        return color;

    }
    
    
    /**
     * Change the color.
     * @ It changes color following the colors of the Rainbow.
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     */
    public void rainbow(){
        makeVisible();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();    
        }
        makeInvisible();
    }
    
    public double getPendiente(){
        return tourist.getPendiente();
    }
    
    public int getSafeDistance(){
        return safeDistance;
    }
    
    public String getType(){
        return type;
    }
}
