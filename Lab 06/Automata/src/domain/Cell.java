package domain;

import java.awt.Color;

/**Information about a cell<br>
<b>(automata,row,column,age,state,nextState, color)</b><br>
<br>
 */
public class Cell extends Agent implements Item{
    protected char nextState;
    protected Color color;
    private CellularAutomata automata;
    private int row,column;


    /**Create a new cell (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new cell is going to be alive in the following state.
     * @param ac 
     * @param row 
     * @param column 
     */
    public Cell(CellularAutomata ac,int row, int column){
        automata=ac;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        automata.setItem(row,column,(Item)this);    
        color=Color.red;
    }

    /**Returns the row
    @return row
     */
    public final int getRow(){
        return row;
    }

    /**Returns tha column
    @return column
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
        if (getAge()>=3){
            nextState=Agent.DEAD;
        }   
    }
    
    public CellularAutomata getAutomata(){
        return this.automata;
    }
    
    public int getLength(){
        return automata.getLength();
    }

    /**Change its actual state
     */
    public final void change(){
        turn();
        state=nextState;
    }
}
