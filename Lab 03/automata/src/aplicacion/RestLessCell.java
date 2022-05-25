package domain;
import java.awt.Color;

/**
 * Write a description of class RestLessCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RestLessCell extends Cell implements Item
{
    /**
     * Constructor for objects of class RestLessCell
     */
    public RestLessCell(CellularAutomata ac,int row, int column)
    {
        super(ac, row, column);
        color = Color.orange;
    }
    
    /**Decide its next state
     */
    public void decide(){
        int cont = 0;
        int fila = getRow();
        int columna = getColumn();
        int minRowRange = fila - 1;
        int maxRowRange = fila + 1;
        int minColumnRange = columna - 1;
        int maxColumnRange = columna + 1;
        byte flag = 0;
        if (fila == 0){
            minRowRange = fila;
            maxRowRange = fila + 1;
        }else if (fila == getAutomata().getLength() - 1){
            minRowRange = fila - 1;
            maxRowRange = fila;
        }
        
        if(columna == 0){
            minColumnRange = columna;
            maxColumnRange = columna + 1;
        } else if (columna == getAutomata().getLength() - 1){
            minColumnRange = columna - 1;
            maxColumnRange = columna;
        }
        
        for (int i = minRowRange; i <= maxRowRange; i++){
            for (int j = minColumnRange; j <= maxColumnRange; j++){
                if (!(i == fila && j == columna)){
                    if (getAutomata().getTable()[i][j] == null || !(getAutomata().getTable()[i][j].isAlive())){
                        nextState=Agent.DEAD;
                        cont ++;
                    }
                }
            }
        }
        
        if ((0 < fila) && (fila < getAutomata().getLength() - 1)){
            if ((getAutomata().getTable()[fila-1][columna] == null) && (getAutomata().getTable()[fila+1][columna] == null)){
                RestLessCell inquieta = new RestLessCell(getAutomata(), fila - 1, columna);
                Cell normal = new Cell(getAutomata(), fila + 1, columna);
            } else if (getAutomata().getTable()[fila-1][columna] == null){
                RestLessCell inquieta = new RestLessCell(getAutomata(), fila - 1, columna);
            } else if (getAutomata().getTable()[fila-1][columna] == null){
                Cell normal = new Cell(getAutomata(), fila + 1, columna);
            }
        } else if (fila == 0){
            if (getAutomata().getTable()[fila+1][columna] == null){
                Cell normal = new Cell(getAutomata(), fila + 1, columna);
            }
        } else {
            if (getAutomata().getTable()[fila-1][columna] == null){
                RestLessCell inquieta = new RestLessCell(getAutomata(), fila - 1, columna);
            }
        }
    }
}