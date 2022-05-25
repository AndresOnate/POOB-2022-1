package domain;
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CellularAutomataTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CellularAutomataTest
{

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void ShouldTheBuldDie(){
        CellularAutomata automata = new CellularAutomata();
        Bulb bombillo = new Bulb(automata,15,15);
        for (int i = 0; i < 102; i++){
            automata.ticTac(); 
        }
        assertEquals(false,automata.getItem(15,15).isAlive());
        assertEquals(Color.gray,automata.getItem(15,15).getColor());
        
    }
    
    @Test
    public void ShouldSanadoraHelp(){
        CellularAutomata automata = new CellularAutomata();
        Cell p1 = new Cell(automata, 14, 15);
        Cell p2 = new Cell(automata, 15, 14);
        Cell p3 = new Cell(automata, 16, 15);
        Cell p4 = new Cell(automata, 15, 16);   
        for (int i = 0; i < 4; i++){
            automata.ticTac(); 
        }
        Sanadora sanadora = new Sanadora(automata,15,15);
        for (int i = 0; i < 6; i++){
            automata.ticTac(); 
        }
        assertEquals(false,automata.getItem(15,15).isAlive());
        assertEquals(true,automata.getItem(14,15).isAlive());
        assertEquals(true,automata.getItem(15,14).isAlive());
        assertEquals(true,automata.getItem(16,15).isAlive());
        assertEquals(true,automata.getItem(15,16).isAlive());
    }
    
    @Test
    public void ShouldBombDestroy(){
        CellularAutomata automata = new CellularAutomata();
        Cell p1 = new Cell(automata, 14, 15);
        Cell p2 = new Cell(automata, 15, 14);
        Cell p3 = new Cell(automata, 16, 15);
        Cell p4 = new Cell(automata, 15, 16);   
        automata.ticTac(); 
        BombBulb bombillo = new BombBulb (automata,15,15);
        automata.ticTac();
        automata.ticTac();
        assertEquals(false,automata.getItem(15,15).isAlive());
        assertEquals(Color.black,automata.getItem(15,15).getColor());
        assertEquals(null,automata.getItem(14,15));
        assertEquals(null,automata.getItem(15,14));
        assertEquals(null,automata.getItem(16,15));
        assertEquals(null,automata.getItem(15,16));
    }
    
    @Test
    public void ShouldConway(){
        CellularAutomata automata = new CellularAutomata();
        Cell c1 = new Conway(automata, 10, 10);
        Cell c2 = new Conway(automata, 10, 11);
        automata.ticTac();
        assertEquals(true,automata.getItem(10,10).isAlive());
        assertEquals(true,automata.getItem(10,11).isAlive());
        automata.ticTac();
        assertEquals(false,automata.getItem(10,10).isAlive());
        assertEquals(false,automata.getItem(10,11).isAlive());
    }    
    
    @Test
    public void ShouldConwayBloque(){
        CellularAutomata automata = new CellularAutomata();
        Conway b1= new Conway(automata, 28, 0);
        Conway b2 = new Conway(automata, 28, 1);
        Conway b3 = new Conway(automata, 29, 1);
        Conway b4 = new Conway(automata, 29, 0);
        automata.ticTac();
        assertEquals(true,automata.getItem(28, 0).isAlive());
        assertEquals(true,automata.getItem(28, 1).isAlive());
        assertEquals(true,automata.getItem(29, 1).isAlive());
        assertEquals(true,automata.getItem(29, 0).isAlive());
        for (int i = 0; i < 102; i++){
            automata.ticTac(); 
        }
        assertEquals(true,automata.getItem(28, 0).isAlive());
        assertEquals(true,automata.getItem(28, 1).isAlive());
        assertEquals(true,automata.getItem(29, 1).isAlive());
        assertEquals(true,automata.getItem(29, 0).isAlive());
    }  
    
    @Test
    public void ShouldConwayParpadeador(){
        CellularAutomata automata = new CellularAutomata();
        Conway pa1= new Conway(automata, 20, 20);
        Conway pa2 = new Conway(automata, 20, 21);
        Conway pa3 = new Conway(automata, 20, 22);
        automata.ticTac();
        assertEquals(true,automata.getItem(20, 20).isAlive());
        assertEquals(true,automata.getItem(20, 21).isAlive());
        assertEquals(true,automata.getItem(20, 22).isAlive());        
        automata.ticTac(); 
        assertEquals(false,automata.getItem(20, 20).isAlive());
        assertEquals(true,automata.getItem(20, 21).isAlive());
        assertEquals(true,automata.getItem(19, 21).isAlive());
        assertEquals(true,automata.getItem(21, 21).isAlive());
        assertEquals(false,automata.getItem(20, 22).isAlive());
    } 
    
    @Test
    public void shouldRestLessCellBeDead(){
        CellularAutomata automata = new CellularAutomata();
        RestLessCell cell = new RestLessCell(automata, 6, 6);
        automata.ticTac();
        assertEquals(false, automata.getItem(6,6).isAlive());
    }
}
