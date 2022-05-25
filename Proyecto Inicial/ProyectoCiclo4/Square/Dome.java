package Square;
import Shapes.*;
import java.util.ArrayList;
/**
 * Write a description of class Dome here.
 * 
 * @author Nicolas Ariza, Andres Oñate
 * @version 1.0 (07 february 2022)
 */
public class Dome
{
    private String color;
    private int xPosition;
    private int yPosition;
    private Circle dome;
    private int idNum;
    private boolean visibility;
    protected String type;
    /**
     * Constructor for objects of class Dome
     */
    public Dome(String newColor, int positionX, int positionY)
    {
        color = newColor;
        xPosition = positionX;
        yPosition = positionY;
        dome = new Circle(xPosition, yPosition, color);
        type = "normal";
    }
    
    public Dome(String newColor, int positionX, int positionY, String NewType)
    {
        color = newColor;
        xPosition = positionX;
        yPosition = positionY;
        dome = new Circle(xPosition, yPosition, color);
        type = NewType;
    }
    
     /**
     * Constructor for objects of class Dome
     */
    public Dome(String newColor, int positionX, int positionY, int newIdNum)
    {
        color = newColor;
        xPosition = positionX;
        yPosition = positionY;
        idNum = newIdNum;
        dome = new Circle(xPosition, yPosition, color);
    }
    
    /**
     * Get Color (ID) of the dome
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Get position x of the dome
     */
    public int getxPosition(){
        return xPosition;
    }
    
    /**
     * Get position y of the dome
     */
    public int getyPosition(){
        return yPosition;
    }
    
    /**
     * Makes the dome visible
     */
    public void makeVisible(){
        visibility = true;
        dome.makeVisible();
    }
    
    /**
     * Makes the dome invisible
     */
    public void makeInvisible(){
        visibility = true;
        dome.makeInvisible();
    }
    
    /**
     * Return the type of the Dome
    **/
    public String type(){
        return this.type;

    }
    
   /**
     * Given a specific type of dome this method will start the behaviour of every dome. This one is for the ShyDomes.
     * @param touristPositionX is the x position of the tourist wich is going to take a photo of the ShyDome.
     * @param touristPositionY is the y position of the tourist wich is going to take a photo of the ShyDome.
     * @param touristVisionAngle is the vision angle of the current tourist, its expresed on radians.
     */
    public void letMeBeMe(int touristPositionX, int touristPositionY, double touristVisionAngle, int repeats, int squareX, int squareY) throws SquareException{
        boolean keys[] = new boolean[8];
        keys[0] = 0 < touristPositionX - 30 - 5*repeats;
        keys[1] = 0 < touristPositionY - 30 - 5*repeats;
        keys[2] = touristPositionX + 30 + 5*repeats < squareX;
        keys[3] = touristPositionY + 30 + 5*repeats < squareY;
        
        if (touristVisionAngle == 0){
            if (keys[0]){
                this.dome.moveTo(touristPositionX - 30 - 5*repeats, touristPositionY);
            } else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if ( 0 < touristVisionAngle && touristVisionAngle < Math.PI / 2){
            if(keys[0] && keys[3]){
                this.dome.moveTo(touristPositionX - 30 - 5*repeats, touristPositionY + 30 + 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (touristVisionAngle == Math.PI / 2){
            if(keys[3]){
                this.dome.moveTo(touristPositionX, touristPositionY + 30 + 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (Math.PI / 2 < touristVisionAngle && touristVisionAngle < Math.PI){
            if(keys[2] && keys[3]){
                this.dome.moveTo(touristPositionX + 30 + 5*repeats, touristPositionY + 30 + 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (touristVisionAngle == Math.PI){
            if(keys[2]){
                this.dome.moveTo(touristPositionX + 30 + 5*repeats, touristPositionY);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (Math.PI < touristVisionAngle && touristVisionAngle < 3*Math.PI/2){
            if(keys[2] && keys[1]){
                this.dome.moveTo(touristPositionX + 30 + 5*repeats, touristPositionY - 30 - 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (touristVisionAngle == 3*Math.PI/2){
            if(keys[1]){
                this.dome.moveTo(touristPositionX, touristPositionY - 30 - 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        } else if (3*Math.PI/2 < touristVisionAngle && touristVisionAngle < 2*Math.PI){
            if(keys[0] && keys[1]){
                this.dome.moveTo(touristPositionX - 30 - 5*repeats, touristPositionY - 30 - 5*repeats);
            }else {
                throw new SquareException(SquareException.ENTITY_OUT_OF_TABLE);
            }
        }
    }
    
}
