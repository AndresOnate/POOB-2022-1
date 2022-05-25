package dominio;

public class KalahGameException extends Exception{
    public static final String RANGE_HOUSES = "Error, house out of range";
    public static final String RANGE_SEEDS = "Error, seeds out of range";
    public static final String PLAYER_TURN = "Error, it is not the player's turn yet";

    public KalahGameException(String message){
        super(message);
    }

}
