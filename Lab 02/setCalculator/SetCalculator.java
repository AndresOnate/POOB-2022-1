import java.util.HashMap;
import java.util.HashMap;

/** SetCalculator.java
 * 
 * @author ESCUELA 2022-01
 */
    
public class SetCalculator{
    private boolean state;
    private HashMap<String,Set> variables;
    
    /**
     * Constructor de la clase SetCalculator
    */
   public SetCalculator(){
        variables = new HashMap<String,Set>();
        state = false;
    }

    //Create a new variable
    /** 
     * Create a new variable
     * @param nombre Nombre de la variable
    */
    public void create(String nombre){
        String [] sl = {};
        Set inicial = new Set(sl);
        variables.put(nombre,inicial);
        state = true;
        ok();
    }
     
    //Create and assign a set to an existing variable
    //a := set 
    /** 
     * Create and assign a set to an existing variable
     * @param set Nombre del conjunto 
     * @param elements Elementos del conjunto
    */
   public void assign(String set, String elements[] ){
       if (variables.containsKey(set)){
           Set newSet = new Set(elements);
           variables.put(set,newSet);
           state = true;
           ok();
       }
       state = false;
       ok();
    }    
    
    //Assign the result of an operation to an existing variable
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, '-' difference, '_' symmetric difference, 'x' cartesian product
    /** 
     * Assign the result of an operation to an existing variable
     * a = b op c
     * The operator characters are:  'u' union, 'i' intersection, '-' difference, '_' symmetric difference, 'x' cartesian product
     * @param a existing variable
     * @param b Conjunto 1
     * @param op Operación a realizar
     * @param c  conjunto 2
     * @param elements Elementos del conjunto
    */
   public void assign(String a, String b, char op, String c){
        if (variables.containsKey(a) && variables.containsKey(b) && variables.containsKey(c)  ){
            switch (op){
                case 'u':
                    variables.put(a,variables.get(b).union(variables.get(c)));
                    state = true;
                    ok();
                    break;
                case 'i':
                    variables.put(a,variables.get(b).intersection(variables.get(c)));
                    state = true;
                    ok();
                    break;
                case '-':
                    variables.put(a,variables.get(b).difference(variables.get(c)));
                    state = true;
                    ok();
                    break;
                case '_':
                    variables.put(a,variables.get(b).symmetricDifference(variables.get(c)));
                    state = true;
                    ok();
                    break;
                case 'x':
                    variables.put(a,variables.get(b).cartesianProduct(variables.get(c)));
                    state = true;
                    ok();
                    break;
                
            }
        }
    }
  
    
    //Returns the value of a set
     /** 
     * Returns the value of a set
     * @param variable
    */
   public String toString(String variable){
        return this.variables.get(variable).toString();
    }
    
    
    //If the last operation was done
    /** 
     * If the last operation was done
    */
   public boolean ok(){
        return state;
    }
    
   public HashMap getVariable(){
        return this.variables;
    }
}



