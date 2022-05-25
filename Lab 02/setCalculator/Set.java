import java.util.*;
/**
 * 
 * @author Nicolas Ariza, Andres Oñate
 * @version 1.0 (15 february 2022)
 */
public class Set {
    private ArrayList<String> valores = new ArrayList<String>();
    
    /**
    * Set class constructor
    * @param elements Son los elementos que forman parte del conjunto.
    */
    public Set(String[] elements){
       for(String elemento: elements){
           String x = elemento.toUpperCase();
           if (!valores.contains(x)){
               this.valores.add(x);
           }
       }
       Collections.sort(valores);
    }
    
    //Return (this union a)
    /**
      * Operación Unir entre conjutos
      * @param Set a Conjunto a Unir
      * @return newSet Retorna la union del conjunto de llamado y el conjunto a
     */
    public Set union (Set a){
        String[] s1 = new String[this.valores.size()];
        s1 = this.valores.toArray(s1);
        Set solveSet = new Set(s1);
        if (!(a.valores.isEmpty())){
            for(String element: a.valores){
                if (!(this.valores.contains(element))){
                    solveSet.valores.add(element);
                }
            }
            Collections.sort(solveSet.valores);
        }
        return solveSet;
    }
    
    /**
     * Intersection operation between sets
     * @param Set a the set with which we're going to do the intersection
     * @return newSet if both sets have common elements it will return the new intersection set, otherwise the void set
     */
    public Set intersection (Set a){
        String[] s1 = {};
        Set solveSet = new Set(s1);
        if (!(a.valores.isEmpty())){
            for(String element: a.valores){
                if (this.valores.contains(element)){
                    solveSet.valores.add(element);
                }
            }
            Collections.sort(solveSet.valores);
        } 
        return solveSet;
    }
    
    /**
     * Difference operation between sets
     * @param Set a the set with which we're going to do the difference
     * @return solveSet being the set who contains the result of the difference operation between this set and set a, void set if both sets are the same set
     */
    public Set difference (Set a){
        String [] sl = {};
        Set solveSet = new Set(sl);
        for(String elemento: this.valores){
            if (!a.valores.contains(elemento)){
                solveSet.valores.add(elemento);
            }
        }
        Collections.sort(solveSet.valores);
        return solveSet;
    }
    
    /**
     * Symmetric difference operation between sets
     * @param Set a the set with which we're going to do the symmetric difference
     * @return solveSet being the set who contains the result of the symmetric difference operation between this set and set a, void set if both sets are the same set
     */
    public Set symmetricDifference (Set a){
        String [] sl = {};
        Set solveSet = new Set(sl);
        for(String elemento: this.valores){
            if (!(a.valores.contains(elemento))){
                solveSet.valores.add(elemento);
            }
        }
        
        for(String elemento: a.valores){
            if (!this.valores.contains(elemento)){
                solveSet.valores.add(elemento);
            }
        }
        Collections.sort(solveSet.valores);
        return solveSet;
    }
    
    
    /**
     * Cartesian product between sets
     * @param Set a the set with which we're going to do the cartesian product
     * @return valuesOfvalues if both sets have at least one element, otherwise it will return void set
     */
    public Set cartesianProduct(Set a){
        String [] sl = {};
        Set solveSet = new Set(sl);
        for (String element: this.valores){
            for (String element2: a.valores){
                solveSet.valores.add("("+ element + "," + element2 +")");
            }
        }
        return solveSet;
    }
    
    /**
     * Do the operation of "is subset of" between two sets
     * @param Set a being the set who is going to be compared against the set who called this method
     * @return boolean true if this set is subset of the set a, otherwise it will return false
     */
    public boolean subset(Set a){
        boolean flag = false;
        String temp = toString();
        for(String element : a.valores){
            if (element.equals(temp)){
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * @return size Devuelve el cardinal del conjunto.
     */
    public int size(){
        return this.valores.size();
    }
    
    /**
     * Determina si un elemento pertenece a un conjunto
     * @return boolean element E this
    */
    public boolean contains(String element){
        return this.valores.contains(element);
    }
    
 
    /**
    * @return equals Determina si dos conjuntos son iguales.
    */
    public boolean equals(Set a){
        return this.valores.equals(a.valores );
    }
    
    /**
    * @return equals Determina si un conjunto y un Objeto son iguales.
    */
    public boolean equals(Object a){
        return equals((Set)a);
    }
    
    
    /**
     * Devuelve el conjunto en tipo String.
     * @elementsString Elementos del conjunto a String.
     */
    public String toString() {
        String elementsString = "";
        if (!this.valores.isEmpty()){
            elementsString = "{";
            for (int i = 0; i < this.valores.size() -1 ;i++  ){
                String element = this.valores.get(i);
                elementsString += element + ",";
            }
            elementsString += this.valores.get(this.valores.size()-1) + "}";
        }else{
            return "{}";
        }
        return elementsString ;
    }
}
