package Square;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class SquareC2Test{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
      */
    @Before
    public void setUp(){
        ArrayList<String> solve = new ArrayList<String>();
    }
       
    @Test
    public void shouldCreateDomes(){
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,60},{50,40},{30,30}, {20,50}};;
        int [] desiredView = {4, 3, 5, 2, 1}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        assertEquals(5, square.domes().size());
    }
            
    @ Test
    public void shouldTouristMove() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,1}; 
        int [][] domes = {{300,70}};
        int [] desiredView = {4, 3, 5, 2, 1}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",150,150);
        square.touristMove("yellow",150,150, 7*Math.PI/4);        //      
    }
    
    @Test
    public void shouldDefinePhoto(){
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,100},{50,200},{300,300}, {200,150}};;
        int [] desiredView = {4, 3, 5, 2, 1}; 
        String [] Sdomes = {"white", "red", "green", "black", "magenta"};
        Squares square = new Squares(dimensions,domes,desiredView);
        square.defineRequestedPhoto(Sdomes);
        assertEquals(5,square.getDesiredViewPhoto().size());
    }
    
    @Test
    public void shouldTouristTakePhoto0G() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{300,20},{50,60},{50,40},{30,30}, {20,50}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",10,20);
       assertEquals(4,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomes90G() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{150,20},{150,10},{150,170}, {170,200}, {150,300}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",150,50);
       square.touristMove("yellow",150,50, Math.PI/2);
       assertEquals(2,square.touristTakePhoto("yellow").size());
    }
    
    @Test
    public void shouldNotTakeSuperposedDomes180G() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{370,350},{30,350},{150,350}, {170,350}, {150,350}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",350,350);
       square.touristMove("yellow",350,350, Math.PI);
       assertEquals(1,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomes270G() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{350,100},{350,120},{350,150}, {350,80}, {350,370}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",350,350);
       square.touristMove("yellow",350,350, 3*Math.PI/2);
       assertEquals(1,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomesIIC() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{200,200},{150,250},{100,150}, {150,80}, {200,280}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",350,350);
       square.touristMove("yellow",250,150, 3*Math.PI/4);
       assertEquals(4,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomesIC() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{200,200},{300,300},{80,40}, {180,70}, {350,280}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",100,100);
       square.touristMove("yellow",100,100, Math.PI/4);
       assertEquals(3,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomesIIIC() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{200,200},{250,250},{150,40}, {180,70}, {40,50}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",100,100);
       square.touristMove("yellow",340,340, 5*Math.PI/4);
       assertEquals(4,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldNotTakeSuperposedDomesIIIIC() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{200,200},{100,100},{150,40}, {180,70}, {40,350}};
        int [] desiredView = {1,2,5,4,3}; 
       Squares square = new Squares(dimensions,domes,desiredView);
       square.touristArrive("yellow",50,50);
       square.touristMove("yellow",100,150, 7*Math.PI/4);
       assertEquals(4,square.touristTakePhoto("yellow").size());

    }
    
    @Test
    public void shouldDeterminateCantTakePhoto() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,60},{50,40},{30,30}, {20,50}};
        int [] desiredView = {4, 3, 5, 2, 1}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, Math.PI);
        assertEquals("yellow", square.whoTakePhoto().get(0));    
    }
 
    
    @Test
    public void shouldDeterminateCantPhotoNotPossible() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,60},{50,40},{30,30}, {20,50}};
        int [] desiredView = {1,2,3,4,5}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, 0);
        assertEquals(0, square.whoTakePhoto().size());
        //      
    }
      
    @Test
    public void shouldTakeRequestedPhoto() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,60},{60,70},{90,80},{120,90}, {150,100}};
        int [] desiredView = {1,2,3,4,5}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, 3*Math.PI/2);
        square.takeRequestedPhoto();        
    }
      
    @Test
    public void shouldInstructionsRequestedPhoto() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{50,60},{50,40},{30,30}, {20,50}};
        int [] desiredView = {4, 3, 5, 2, 1}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",250,250);
        square.touristMove("yellow",250,250, Math.PI/4);
        assertEquals(5, square.touristInstructionsRequestedPhoto("yellow").size());   
    }
       
    @Test
    public void shouldInstructionsRequestedPhotoP2() throws SquareException{
        ArrayList<String> solve = new ArrayList<String>();
        int [] dimensions = {400,400,5}; 
        int [][] domes = {{30,70},{100,60},{200,40},{220,130}, {300,50}};
        int [] desiredView = {5,4,3,2,1}; 
        Squares square = new Squares(dimensions,domes,desiredView);
        square.touristArrive("yellow",300,350);
        square.touristMove("yellow",300,350, Math.PI/2);
        assertEquals(5, square.touristInstructionsRequestedPhoto("yellow").size());
        
    }
          
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}