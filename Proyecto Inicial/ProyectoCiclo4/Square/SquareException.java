package Square;

/**
 * Square exception allow us to verify some posible exceptions during  
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareException extends Exception{
    public static final String SAFETY_DISTANCE = "The current tourists violate the safety distance";
    public static final String DOMES_OVERLAP = "Domes can not overlap";  
    public static final String ENTITY_OUT_OF_TABLE = "Error, the actual entity drops out of the square";
    public SquareException(String message){
        super(message);
    }
}

