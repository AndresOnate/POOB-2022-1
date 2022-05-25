package Square;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * Write a description of class Dome here.
 * 
 * @author Nicolas Ariza, Andres Oñate
 * @version 1.0 (07 february 2022)
 */
public class SquareC1Test{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
      */
    @Before
    public void setUp(){
    }

    @Test
    public void shouldAddDomes(){
        Squares square = new Squares(400,400,5);
        square.addDome("red", 250, 250);
        ArrayList<String> domes =  new ArrayList<String>();
        domes.add("red");
        assertEquals(domes, square.domes());
    }
    
    @Test
    public void shouldDeteleDomes(){
        Squares square = new Squares(400,400,5);
        square.addDome("red", 250, 250);
        ArrayList<String> domes =  new ArrayList<String>();
        domes.add("red");
        assertEquals(domes, square.domes());
        square.delDome("red");
        assertEquals(null, square.domes());
    }
    
    @Test
    public void shouldAddTourist(){
        Squares square = new Squares(400,400,5);
        square.touristArrive("red", 250, 250);
        ArrayList<String> tourist =  new ArrayList<String>();
        tourist.add("red");
        assertEquals(tourist, square.tourists());
        square.touristArrive("yellow", 250, 254);
        assertEquals(tourist, square.tourists());
        square.touristArrive("white", 250, 256);
        tourist.add("white");
        assertEquals(tourist, square.tourists());
    }
    
    @Test
    public void shouldTouristMove() throws SquareException{
        Squares square = new Squares(400,400,5);
        square.touristArrive("red", 250, 250);
        square.touristMove("red",100,100, Math.PI/2);
        square.addDome("black",200,200);
        assertEquals(1, square.touristTakePhoto("red").size());
    }
    
    @Test
    public void shouldTouristTakePhoto() throws SquareException{
        Squares square = new Squares(400,400,5);
        square.touristArrive("red", 250, 250);
        square.touristMove("red",100,100, Math.PI/2);
        square.addDome("black",200,200);
        assertEquals(1, square.touristTakePhoto("red").size());
    }
}
