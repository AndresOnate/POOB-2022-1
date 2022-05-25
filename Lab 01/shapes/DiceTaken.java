import java.util.Random;
import javax.swing.JOptionPane;
/**
 * Write a description of class Dice here.
 * 
 * @author (Nicolás Ariza, Andres Oñate) 
 * @version 1.0 (01 february 2022)
 */
public class DiceTaken
{
    private Dice [][] dados;
    private byte tableSize;
    private boolean isCorrect;
    /**
     * Constructor for objects of class DiceTaken
     */
    public DiceTaken(byte n)
    {
        dados = new Dice[n][n];
        tableSize = n;
        byte indexI = (byte) (Math.random()*tableSize);
        byte indexJ = (byte) (Math.random()*tableSize);
        
        for (int i = 0; i < tableSize ; i++) {
            for (int j = 0; j < tableSize ; j++) {
                byte newDigit = (byte) (Math.random()*6+1);
                dados[i][j] = new Dice(newDigit);
                dados[i][j].moveTo(130*j,i*130);
            }
        }
        dados[indexI][indexJ].makeInvisible();
        updateTable();
        isWinner();
    }

    /**
     * Actualiza el tablero deacuerdo al valor de los dados.
     */
    private void updateTable(){
        for (Dice [] dadoBeta: dados){
            for (Dice dadoAlpha:dadoBeta){
                dadoAlpha.changeColor("red");
                if (dadoAlpha.isVisible()){
                    dadoAlpha.makeVisible();
                }
            }
        }
        
        for (int i = 0; i < tableSize; i++){
            for (int j = 0; j < tableSize; j++) {
                if ((0 < i  && i < tableSize - 1) && ( 0 < j  && j < tableSize - 1) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i][j - 1].getDigit()) && dados[i][j - 1].isVisible()){
                        dados[i][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j - 1].getDigit()) && dados[i - 1][j - 1].isVisible()){
                        dados[i - 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j].getDigit()) && dados[i - 1][j].isVisible()){
                        dados[i - 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j + 1].getDigit()) && dados[i - 1][j + 1].isVisible()){
                        dados[i - 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j + 1].getDigit()) && dados[i][j + 1].isVisible()){
                        dados[i][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j + 1].getDigit()) && dados[i + 1][j + 1].isVisible()){
                        dados[i + 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j].getDigit()) && dados[i + 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j - 1].getDigit()) && dados[i + 1][j - 1].isVisible()){
                        dados[i + 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                }else if((i == 0) && ( 0 < j  && j < tableSize - 1) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i][j - 1].getDigit()) && dados[i][j - 1].isVisible()){
                        dados[i][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j - 1].getDigit()) && dados[i + 1][j - 1].isVisible()){
                        dados[i + 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j].getDigit()) && dados[i + 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j + 1].getDigit()) && dados[i + 1][j + 1].isVisible()){
                        dados[i + 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j + 1].getDigit()) && dados[i][j + 1].isVisible()){
                        dados[i][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                }else if (( 0 < i  && i < tableSize - 1) && (0 == j) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i - 1][j].getDigit()) && dados[i - 1][j].isVisible()){
                        dados[i - 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j + 1].getDigit()) && dados[i - 1][j + 1].isVisible()){
                        dados[i - 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j + 1].getDigit()) && dados[i][j + 1].isVisible()){
                        dados[i][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j + 1].getDigit()) && dados[i + 1][j + 1].isVisible()){
                        dados[i + 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j].getDigit()) && dados[i + 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                }else if ((i == tableSize - 1) && ( 0 < j  && j < tableSize - 1) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i][j - 1].getDigit()) && dados[i][j - 1].isVisible()){
                        dados[i][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j - 1].getDigit()) && dados[i - 1][j - 1].isVisible()){
                        dados[i - 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j].getDigit()) && dados[i - 1][j].isVisible()){
                        dados[i - 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j + 1].getDigit()) && dados[i - 1][j + 1].isVisible()){
                        dados[i - 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j + 1].getDigit()) && dados[i][j + 1].isVisible()){
                        dados[i][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                }else if ((i == tableSize - 1) && (0 == j) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i - 1][j].getDigit()) && dados[i - 1][j].isVisible()){
                        dados[i - 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j + 1].getDigit()) && dados[i - 1][j + 1].isVisible()){
                        dados[i - 1][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j + 1].getDigit()) && dados[i][j + 1].isVisible()){
                        dados[i][j + 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j + 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                }else if (( 0 < i  && i < tableSize - 1) && (j == tableSize - 1) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i - 1][j].getDigit()) && dados[i - 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i - 1][j - 1].getDigit()) && dados[i - 1][j - 1].isVisible()){
                        dados[i - 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i - 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i][j - 1].getDigit()) && dados[i][j - 1].isVisible()){
                        dados[i][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j - 1].getDigit()) && dados[i + 1][j - 1].isVisible()){
                        dados[i + 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j].getDigit()) && dados[i + 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }
                } else if ((0 == i) && (j == tableSize - 1) && dados[i][j].isVisible()){
                    if((dados[i][j].getDigit() == dados[i][j - 1].getDigit()) && dados[i][j - 1].isVisible()){
                        dados[i][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j - 1].getDigit()) && dados[i + 1][j - 1].isVisible()){
                        dados[i + 1][j - 1].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j - 1].makeVisible();
                        dados[i][j].makeVisible();
                    }
                    if((dados[i][j].getDigit() == dados[i + 1][j].getDigit()) && dados[i + 1][j].isVisible()){
                        dados[i + 1][j].changeColor("green");
                        dados[i][j].changeColor("green");
                        dados[i + 1][j].makeVisible();
                        dados[i][j].makeVisible();
                    }   
                }
            }
        }
    }
         
    /**
     * Lanza de nuevo un dado espefico para obtener un nuevo valor o el mismo.
     * @i Posición en x
     * @j Posicion en y
     */
    public void lanzar(byte i, byte j){
        if (i > 0 && i < tableSize + 1 && j > 0 && j < tableSize + 1 && dados[i-1][j-1].isVisible() == true){
            dados[i-1][j-1].change();   
        } else {
            JOptionPane.showMessageDialog(null, "Incorrecto, ingrese una nueva posición.");
        }
        updateTable();
        isWinner();
    }
    
     /**
     * Mueve un dado una cantidad especifica vertical y horizontalmente.
     * @i Posición en x
     * @j Posicion en y
     */
    public void move(byte i, byte j)
    {
        i = (byte) (i - 1);
        j = (byte) (j - 1);   
        Dice AUX;
        if (i >= 0 && i < tableSize  && j >= 0 && j < tableSize ){
            if ( i-1 >= 0 && dados[i-1][j].isVisible() == false ){
                
                dados[i-1][j].setDigit((dados[i][j].getDigit()));
                dados[i][j].setVisible(false);
                dados[i][j].makeInvisible();
                dados[i-1][j].makeVisible();

             
            } else if ( i + 1 < tableSize && dados[i+1][j].isVisible() == false){
                dados[i+1][j].setDigit((dados[i][j].getDigit()));
                dados[i][j].setVisible(false);
                dados[i][j].makeInvisible();
                dados[i+1][j].makeVisible();
                
            } else if ( j - 1 >= 0 && dados[i][j-1].isVisible() == false){
                dados[i][j-1].setDigit((dados[i][j].getDigit()));
                dados[i][j].setVisible(false);
                dados[i][j].makeInvisible();
                dados[i][j-1].makeVisible();
                
            } else if ( j + 1 < tableSize && dados[i][j+1].isVisible() == false){
                dados[i][j+1].setDigit((dados[i][j].getDigit()));
                dados[i][j].setVisible(false);
                dados[i][j].makeInvisible();
                dados[i][j+1].makeVisible();
                
            }else{
                JOptionPane.showMessageDialog(null, "Incorrecto, ingrese una nueva posición.");
            }
                
        } else{
            JOptionPane.showMessageDialog(null, "Incorrecto, ingrese una nueva posición.");
        }
        updateTable();
        isWinner();
    }
    
    /**
     * Notofica al jugador que cumplio el objeivo.
     */
    private void isWinner(){
        byte cont = 0;
        for (Dice [] dadoAlpha:dados){
            for (Dice dadoBeta:dadoAlpha){
                if (dadoBeta.isVisible()){
                    if (dadoBeta.getColor().equals("green")){
                        cont ++;
                    }
                }
            }
        }
        if (cont == (tableSize * tableSize) - 1){
            for (Dice [] dadoAlpha:dados){
                for (Dice dadoBeta:dadoAlpha){
                    dadoBeta.rainbowWin();
                }
            }
            JOptionPane.showMessageDialog(null, "Ganador, ganador !!");
        }
    }
}



