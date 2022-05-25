import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SetTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    
    @Test
    public void shouldCreateEmptySets(){
        String [] ev ={};        
        assertEquals(0, new Set(ev).size());
    }    
   
    
    @Test
    public void shouldCreateSets(){
        String [] e ={"APPLE", "ORANGE"};        
        assertEquals(2, new Set(e).size());
    }    
    
    
    
    @Test
    public void shouldCompareSets(){
        String [] ev ={};  
        String [] e ={"APPLE", "ORANGE"}; 
        assertTrue(new Set(ev).equals(new Set(ev)));
        assertTrue(new Set(e).equals(new Set(e)));
        assertFalse(new Set(ev).equals(new Set(e)));
    }  
    
    @Test
    public void shouldNotHaveDuplicateElements(){
        String [] eu ={"APPLE", "ORANGE"};        
        String [] er ={"ORANGE","ORANGE","APPLE"};
        assertEquals(2, (new Set(er)).size());
        assertEquals(new Set(eu), new Set(er));
    }    

    @Test
    public void shouldNotBeCaseSensitive(){     
        String [] eu ={"ORANGE","APPLE"};
        String [] em = {"oRange","apple", "APPLE"};
        assertEquals(2, (new Set(em)).size());
        assertEquals((new Set(eu)), (new Set(em)));
    }
    
    @Test
    public void shouldConvertToString(){
        String [] ev ={};
        assertEquals("{}", new Set(ev).toString());
        String [] es = {"1","2","3"};
        assertEquals("{1,2,3}", new Set(es).toString());
        String [] ec = {"one","two","three"};
        assertEquals("{ONE,THREE,TWO}", new Set(ec).toString());
    }
    
    @Test
    public void shouldUniteSet(){
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","6"};
        String [] ec = {"1","2","3","4","5","6"};
        assertEquals(new Set(ec), new Set(ev).union(new Set(ec)));
    }
    
    @Test
    public void shouldIntersectionSet(){
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        String [] ec = {"3"};
        assertEquals(new Set(ec), new Set(ev).intersection(new Set(ec)));
    }
    
     @Test
    public void shouldDifferenceSet(){
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        String [] ec = {"1","2"};
        assertEquals(new Set(ec), new Set(ev).difference(new Set(es)));
    }
    
     @Test
    public void shouldSymmetricDifferenceSet(){
        String [] ev ={"1","2","3"};
        String [] es = {"4","5","3"};
        String [] ec = {"1","2","4","5"};
        assertEquals(new Set(ec), new Set(ev).symmetricDifference(new Set(es)));
    }

    @Test
    public void shouldCartesianProductSet(){
        String [] ev = {"1","3","6"};
        String [] es = {"2","4","7"};
        String [] ec = {"(1,2)","(1,4)","(1,7)","(3,2)","(3,4)","(3,7)","(6,2)","(6,4)","(6,7)"};
        assertEquals(new Set(ec), new Set(ev).cartesianProduct(new Set (es)));
    }
    
    @Test
    public void shouldSubSetSets(){
        String [] es = {"1","2","3"};
        String [] ev = {"{1,2,3}","5","(1,6)"};
        assertTrue(new Set(es).subset(new Set (ev)));
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