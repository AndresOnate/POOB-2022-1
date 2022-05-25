package domain;
import java.awt.Color;

/**Information about a cell<br>
<b>(automata,row,column,age,state,nextState, color)</b><br>
<br>
 */
public class BombBulb extends Bulb implements Item{
    /**Crpackageeate a new Bulb (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new Bulb is going to be alive in the following state.
     * @param ac 
     * @param row 
     * @param column 
     */
    public BombBulb(CellularAutomata ac,int row, int column){
        super(ac, row, column);    
        color=Color.magenta;
    }
  
    private boolean validar(int row, int column){
        int Length = getAutomata().getLength();
        if (row >= 0 && column >= 0 && row < Length && column < Length){
            return true;
        }
        return false;
    }
    
    private void destruir(int row, int column){      
        Item elemento = getAutomata().getItem(row, column);
        if ( elemento != null && validar(row,column)){
            getAutomata().setItem(row, column, (Item) null);       
        }
        
    }

    /**Decide its next state
     */
    @Override
    public void decide(){
        if (this.isAlive() && getAge()<=2){
            int row = getRow();
            int column = getColumn();
            destruir(row - 1, column);
            destruir(row + 1, column);
            destruir(row, column - 1);
            destruir(row, column + 1);
            destruir(row-1, column - 1);
            destruir(row-1, column + 1);
            destruir(row+1, column + 1);
            destruir(row+1, column - 1);
            nextState = DEAD;
            color=Color.black;
        }
    }
    
    public int shape(){
        return SQUARE;
    }
}
