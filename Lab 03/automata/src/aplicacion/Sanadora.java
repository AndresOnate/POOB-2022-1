package domain;
import java.awt.Color;

/**Information about a cell Sanadora<br>
<b>(automata,row,column,age,state,nextState, color)</b><br>
<br>
 */
public class Sanadora extends Cell implements Item{
    /**Crpackageeate a new Bulb (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new Bulb is going to be alive in the following state.
     * @param ac 
     * @param row 
     * @param column 
     */
    public Sanadora (CellularAutomata ac,int row, int column){
        super(ac,row, column);
        color  = Color.green;
        nextState=Agent.DEAD;
    }
   
   /*
    * Validar posición del Item
    */
   private boolean validar(int row, int column){
        int Length = getAutomata().getLength();
        if (row >= 0 && column >= 0 && row < Length && column < Length){
            return true;
        }
        return false;
    }
    
    private void sanar(int row, int column){      
        Item elemento = getAutomata().getItem(row, column);
        if ( elemento != null && !elemento.isAlive() && validar(row,column)){
            getAutomata().setItem(row, column, (Item) (new Cell(getAutomata(),row, column)));        
        }
        
    }
       
    /**Decide its next state
     */
    @Override
    public void decide(){
        if (getAge() == 4){
            nextState= ALIVE;
        }
        if (this.isAlive()){
            int row = getRow();
            int column = getColumn();
            sanar(row - 1, column);
            sanar(row + 1, column);
            sanar(row, column - 1);
            sanar(row, column + 1);
            nextState = DEAD;
        }
    }
}
