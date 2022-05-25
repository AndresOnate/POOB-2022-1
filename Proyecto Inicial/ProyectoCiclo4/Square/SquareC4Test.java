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

public class SquareC4Test
{
    /**
     * Default constructor for test class SquareC4Test
     */
    public SquareC4Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        ArrayList<String> solve = new ArrayList<String>();
    }
 
    @Test
    public void shouldAddTouristPrudent() throws SquareException {
        Squares square = new Squares(400,400,5);
        square.touristArrive("red", 250, 250);
        try{
            square.touristArrive("prudent","yellow", 250, 259);
        } catch (SquareException e) {
            assertEquals(SquareException.SAFETY_DISTANCE,e.getMessage());
        }   
    }
    
    @Test
    public void shouldAddTouristPerfectionist() throws SquareException {
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{30,60},{50,40}, {30,30}, {20,50}};
        int [] desiredView = {4,3,5,2,1}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("perfectionist","yellow",350,350);
       square.touristMove("yellow",350,350, Math.PI);
       assertEquals(5,square.touristTakePhoto("yellow").size());
    }
    
    @Test
    public void shouldAddTouristRebel() throws SquareException {
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{30,60},{50,40}, {30,30}, {20,50}};
        int [] desiredView = {4,3,5,2,1}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("rebel","yellow",350,350);
       square.touristMove("yellow",350,350, Math.PI);
       assertEquals(null,square.touristTakePhoto("yellow"));
    }
    
    @Test
    public void shouldNotDelFixedDome() throws SquareException {
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,4}; 
        int [][] domes = {{30,70},{30,60},{50,40}, {30,30}};
        int [] desiredView = {4,3,5,2}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.addDome("fixed","pink", 20, 50);
       assertEquals(5,square.domes().size());
       square.delDome("pink");
       assertEquals(5,square.domes().size());
    }
    
    @Test
    public void shouldCreateNormalDomes(){
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,60},{50,40},{30,30}, {20,50}};
        int [] desiredView = {4, 3, 5, 2, 1};
        Squares square = new Squares(dimensions,domes,desiredView);
        assertEquals(5, square.domes().size());
    }
    
    @Test
    public void shouldCreateShyDomes()throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,0}; 
        int [][] domes = {};
        int [] desiredView = {};
        Squares square = new Squares(dimensions,domes,desiredView);
        square.addDome("shy","black",110, 70);
        square.addDome("shy","white",140, 70);
        assertEquals(2, square.domes().size());
    }
       
    @Test
    public void shouldNotTakePhotoOfShyDomes() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{20,70},{50,70},{80,70},{110,70}, {140,70}};
        int [] desiredView = {4, 3, 5, 2, 1};
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, Math.PI);
        square.addDome("shy","orange",170, 70);
        square.addDome("shy","pink",200, 70);
        assertEquals(5, square.touristTakePhoto("yellow").size());   
    }
    
    @Test
    public void shouldNotTakeRequestedPhotoOfShyDomes() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{20,70},{50,70},{80,70},{110,70}, {140,70}};
        int [] desiredView = {4, 3, 5, 2, 1};
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, 0);
        square.addDome("shy","orange",170, 70);
        square.addDome("shy","pink",200, 70);
        assertEquals(5, square.touristInstructionsRequestedPhoto("yellow").size());     
    }
}
