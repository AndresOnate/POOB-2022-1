package domain;

import java.awt.Color;

/**
 * Write a description of class Conway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conway extends Cell{
    
    /**Create a new cell (<b>row,column</b>) in the automata <b>ac</b>.
     * Every new cell is going to be alive in the following state.
     * @param ac El automata celular al que pertenece este elemento
     * @param fila La fila en la que se encuentra ubicado el elemento
     * @param columna La columna en la que se encuentra ubicado el elemento
     * @param nombre El nombre del elemento
     */
     public Conway(CellularAutomata ac,int row, int column){
        super(ac, row, column);
        color = Color.blue;
    }
    
    private int revisarVecinos(int i, int j){
        int Y = 1;
        int X = 0;
        int dirs[][] = {{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},};
        int count = 0;
        for(int k = 0 ; k < 8; k++){
            if((((i + dirs[k][Y]) >= 0) && ((i + dirs[k][Y]) < getLength())) && (((j + dirs[k][X]) >= 0) && ((j + dirs[k][X]) <getLength()))){
                if((getAutomata().getItem(i + dirs[k][Y],j + dirs[k][X])) != null && getAutomata().getItem(i + dirs[k][Y],j + dirs[k][X] ).isAlive()){
                    count++;
                }           
            }         
        }
        return count;
    }
    
    /**
     * Método Que decide que va a hacer la célula en el siguiente periodo de tiempo
     */
    @Override
    public void decide(){
        int vivas = revisarVecinos(getRow(),getColumn());
        if(!isAlive()){
            if(vivas == 3){
                nextState = ALIVE;
            }
        }else{
            if(vivas < 2 || vivas > 3){
                nextState = DEAD;
            }
        }
    }
}