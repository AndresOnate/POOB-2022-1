package Square;


/**
 * Write a description of class prudent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class prudent extends Tourist{    
    /**
     * Constructor for objects of class prudent
     */
    public prudent(String newColor, int positionX, int positionY, int angle, int safeDistance)
    {
        super(newColor, positionX, positionY, angle, 2*safeDistance, "prudent");
    }
}
