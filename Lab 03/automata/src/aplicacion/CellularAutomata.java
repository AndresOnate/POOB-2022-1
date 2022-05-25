package domain;
import java.util.*;

public class CellularAutomata{
    static private int LENGTH=30;
    private Item[][] automata;
    
    /**
     * CellularAutomata Constructor
     */
    public CellularAutomata() {
        automata=new Item[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                automata[r][c]=null;
            }
        }
        someItems();
    }

    /**
     * Método Para obtener el tamaño del tablero de juego
     * @return LENGTH tamaño 
     */
    public int  getLength(){
        return LENGTH;
    }

    /**
     * Método Que retorna un elemento en cierta posición en el tablero
     * @param r row
     * @param c column
     * @return Item asociado a esos valores o Null
     */
    public Item getItem(int r,int c){
        return automata[r][c];
    }
       
    /**
     * Método Que asigna un elemento en una posición específica 
     *
     * @param r row
     * @param c column
     * @param e Nuevo Item
     */
    public void setItem(int r, int c, Item e){
        automata[r][c]=e;
    }
    
    public Item[][] getTable(){
        return automata;
    }

    /**
     * Agregar Items al tablero
     */
    public void someItems(){
        //Cell indiana = new Cell(this, 1, 1);
        //Cell celula007 = new Cell(this, 2, 2);
        Bulb suroeste = new Bulb(this, 0, 0);
        Bulb noreste = new Bulb(this, 0, 29);
        //Sanadora camilo = new Sanadora(this, 2, 1);
        //Sanadora nicolas = new Sanadora(this, 2, 3);
        //Cell p1 = new Cell(this, 14, 15);
        //Cell p2 = new Cell(this, 15, 14);
        //Cell p3 = new Cell(this, 16, 15);
        //Cell p4 = new Cell(this, 15, 16);  
        
        //Cell p1 = new Cell(this, 3, 3);
        //Cell p2 = new Cell(this, 3, 4);
        //Bulb p3 = new Bulb(this, 3, 5);
        //Bulb p4 = new Bulb(this, 4, 3);
        //Bulb p6 = new Bulb(this, 4, 5);
        //Cell p7 = new Cell(this, 5, 3);
        //Cell p8 = new Cell(this, 5, 4);
        //Cell p9 = new Cell(this, 5, 5);
        //BombBulb bomba = new BombBulb(this, 4, 4);
        //BombBulb  bombaX = new BombBulb(this, 1, 2);
        Conway john = new Conway(this, 5, 3);
        Conway horton = new Conway(this, 5, 4);
        
        Conway b1= new Conway(this, 28, 0);
        Conway b2 = new Conway(this, 28, 1);
        Conway b3 = new Conway(this, 29, 1);
        Conway b4 = new Conway(this, 29, 0);
        
        Conway pa1= new Conway(this, 15, 14);
        Conway pa2 = new Conway(this, 15, 15);
        Conway pa3 = new Conway(this, 15, 16);

    }
    
    /**
     * Método que realiza una actualización del estado de los elementos según sus propiedades y su entorno (f)
     */
    public void ticTac(){
        for(int i = 0; i< LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                if(automata[i][j] != null){
                    automata[i][j].decide();             
                }else{
                    cellBorn(i, j);
                }
            }
        }
        for(int i = 0; i < LENGTH; i++){
            for(int j=0; j< LENGTH; j++){
                if(automata[i][j] != null){
                    automata[i][j].change();
                }
            }
        }
    }
    
    private int revisarVecinos(int i, int j){
        int Y = 1;
        int X = 0;
        int dirs[][] = {{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},};
        int count = 0;
        for(int k = 0 ; k < 8; k++){
            if((((i + dirs[k][Y]) >= 0) && ((i + dirs[k][Y]) < LENGTH)) && (((j + dirs[k][X]) >= 0) && ((j + dirs[k][X]) <LENGTH))){
                if((automata[i + dirs[k][Y]][j + dirs[k][X]]) != null && (automata[i + dirs[k][Y]][j + dirs[k][X]]).isAlive()){
                    count++;
                }
                
            }
        }
        return count;
    }
    
    public void cellBorn(int row, int column){
        int cellsAlive = revisarVecinos(row,column);
        if(cellsAlive  == 3){
            Conway newItem = new Conway(this, row, column);
        }
    }
    
}
