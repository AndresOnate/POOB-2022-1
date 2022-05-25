package domain;
import java.awt.Color;

/**Information about a cell<br>
<b>(automata,row,column,age,state,nextState, color)</b><br>
<br>
 */
public class Bulb extends Agent implements Item{
    protected char nextState;
    protected Color color;
    private CellularAutomata automata;
    private int row,column;


    /**Crpackageeate a new Bulb (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new Bulb is going to be alive in the following state.
     * @param ac 
     * @param row 
     * @param column 
     */
    public Bulb(CellularAutomata ac,int row, int column){
        automata=ac;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        automata.setItem(row,column,(Item)this);    
        color=Color.yellow;
    }

    /**Returns the row
    @return 
     */
    public final int getRow(){
        return row;
    }

    /**Returns tha column
    @return 
     */
    public final int getColumn(){
        return column;
    }

    
    /**Returns the color
    @return 
     */
    public final Color getColor(){
        return color;
    }


    /**Decide its next state
     */
    public void decide(){
        if (getAge()>=100){
            nextState = Agent.DEAD;
            color = Color.gray;
        }   
    }

    /**Change its actual state
     */
    public final void change(){
        turn();
        state=nextState;
    }
    
    
    public int shape(){
        if (getAge() % 2 == 0){
            return SQUARE;     
        }
        return ROUND;
    }
    
    public CellularAutomata getAutomata(){
        return this.automata;
    }
}
