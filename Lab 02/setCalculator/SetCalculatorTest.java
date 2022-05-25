import static org.junit.Assert.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SetCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SetCalculatorTest

{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    @Test
    public void shouldCreateVariables(){
        String [] ac ={};
        SetCalculator ev = new  SetCalculator();   
        ev.create("prueba");
        assertEquals(new Set(ac),ev.getVariable().get("prueba"));
    }  
    
    @Test
    public void shouldAssing(){
        String [] ac ={"4","5","3"};
        SetCalculator ev = new  SetCalculator(); 
        ev.create("prueba");
        ev.assign("prueba",ac);
        assertEquals(new Set(ac),ev.getVariable().get("prueba"));
    } 
    
    @Test
    public void shouldAssingOperationsUnion(){
        char op1;
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        String [] ec = {};
        SetCalculator calt = new  SetCalculator();
        calt.create("pUnion");
        calt.create("b");
        calt.create("c");
        calt.assign("b", ev);
        calt.assign("c", es);
        op1 = 'u';
        calt.assign("pUnion", "b", op1, "c");
        assertEquals("{1,2,3,4,5}",calt.toString("pUnion"));  
    }  
    
    @Test
    public void shouldAssingOperationsIntersection(){
        char op2;
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        SetCalculator calt = new  SetCalculator();
        calt.create("pIntersection");
        calt.create("b");
        calt.create("c");
        calt.assign("b", ev);
        calt.assign("c", es);
        op2 = 'i';
        calt.assign("pIntersection", "b", op2, "c");
        assertEquals("{3}",calt.toString("pIntersection"));
    
    } 
    
    
    @Test    
    public void shouldAssingOperationsDifference(){
        char op3;
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        SetCalculator calt = new  SetCalculator();
        calt.create("pDifference");
        calt.create("b");
        calt.create("c");
        calt.assign("b", ev);
        calt.assign("c", es);
        op3 = '-';
        calt.assign("pDifference", "b", op3, "c");
        assertEquals("{1,2}",calt.toString("pDifference"));
    
    } 
    
    
    @Test    
    public void shouldAssingOperationsSymmetricDifference(){
        char op4;
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        SetCalculator calt = new  SetCalculator();
        calt.create("pSymmetric");
        calt.create("b");
        calt.create("c");
        calt.assign("b", ev);
        calt.assign("c", es);
        op4 = '_';
        calt.assign("pSymmetric", "b", op4, "c");
        assertEquals("{1,2,4,5}",calt.toString("pSymmetric"));
    
    } 
    
    @Test    
    public void shouldAssingOperationsProduct(){
        char op4;
        String [] ev ={"1","2"};
        String [] es = {"4","5"};
        SetCalculator calt = new  SetCalculator();
        calt.create("pProduct");
        calt.create("b");
        calt.create("c");
        calt.assign("b", ev);
        calt.assign("c", es);
        op4 = 'x';
        calt.assign("pProduct", "b", op4, "c");
        assertEquals("{(1,4),(1,5),(2,4),(2,5)}",calt.toString("pProduct"));
    
    } 
    
    
    @Test    
    public void shouldToString(){
  
        String [] ev ={"1","2"};
        SetCalculator calt = new  SetCalculator();
        calt.create("b");
        calt.assign("b", ev);
        assertEquals("{1,2}",calt.toString("b"));
    
    } 
    
    
    @Test    
    public void shouldToOk(){
        SetCalculator calt = new  SetCalculator();
        calt.create("b");
        assertTrue("{1,2}",calt.ok());
    
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