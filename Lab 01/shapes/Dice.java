import java.util.Random;
/**
 * Write a description of class Dice here.
 * 
 * @author (Nicolás Ariza, Andres Oñate) 
 * @version 1.0 (01 february 2022)
 */

public class Dice
{
    public boolean visibility;
    private byte digit;
    private String Color;
    private int xPosition;
    private int yPosition;
  
    Circle[] pointsFace = new Circle[7];
    Rectangle body = new Rectangle();
    /**
     * Constructor for objects of class Dice
     */
    public Dice(byte digit0){
        digit = digit0;
        //Dice body
        body.changeSize(110, 110);
        body.changeColor("red");
        body.moveVertical(50);
        //Dice center point
        pointsFace[0] = new Circle();
        pointsFace[0].changeSize(25);
        pointsFace[0].moveHorizontal(95);
        pointsFace[0].moveVertical(95);
        pointsFace[0].changeColor("black");
        //Dice left upper point
        pointsFace[1] = new Circle();
        pointsFace[1].changeSize(25);
        pointsFace[1].moveHorizontal(60);
        pointsFace[1].moveVertical(60);
        pointsFace[1].changeColor("black");
        //Dice right upper point
        pointsFace[2] = new Circle();
        pointsFace[2].changeSize(25);
        pointsFace[2].moveHorizontal(125);
        pointsFace[2].moveVertical(60);
        pointsFace[2].changeColor("black");
        //Dice left point
        pointsFace[3] = new Circle();
        pointsFace[3].changeSize(25);
        pointsFace[3].moveHorizontal(60);
        pointsFace[3].moveVertical(95);
        pointsFace[3].changeColor("black");
        //Dice right point
        pointsFace[4] = new Circle();
        pointsFace[4].changeSize(25);
        pointsFace[4].moveHorizontal(125);
        pointsFace[4].moveVertical(95);
        pointsFace[4].changeColor("black");
        //Dice left down point
        pointsFace[5] = new Circle();
        pointsFace[5].changeSize(25);
        pointsFace[5].moveHorizontal(60);
        pointsFace[5].moveVertical(125);
        pointsFace[5].changeColor("black");
        //Dice right down point
        pointsFace[6] = new Circle();
        pointsFace[6].changeSize(25);
        pointsFace[6].moveHorizontal(125);
        pointsFace[6].moveVertical(125);
        pointsFace[6].changeColor("black");
        makeVisible();
    }
    
    /**
     * Cambia aleatoriamente el color del cuerpo del dado dando el efecto de
     * arcoirirs
     */
    public void rainbowWin(){
        body.rainbow();
    }
    
    /**
     * Consulta si el estado del dado es "visible", es decir, que si el dado
     * se encuentra o no en el canvas
     */
    public boolean isVisible(){
        return visibility;
    } 
    
    /**
     * Cambia el estado de "visibilidad" del dado en el canvas
     * @param newVisibility nuevo estado de visibilidad del dado
     */
    public void setVisible(boolean newVisibility){
        visibility = newVisibility;
    }
    
    /**
     * Cambia la cara del dado visible para el usuario segun el numero
     * @param newDigit nuevo valor que tendra la cara del dado
     */
    public void setDigit(byte newDigit){
        digit = newDigit;
    }
    
    /**
     * Retorna el color que posee el dado en el isntante en el que se consulta
     */
    public String getColor(){
        return Color;
    }
    
    /**
     * diceMaker creates the face of the dice that the user wants to see in the canvas
     */
    public void makeVisible(){
        if (0 < digit && digit < 7){
            makeInvisible();
            body.makeVisible();
            visibility = true;
            switch(digit){
                case 1:
                    pointsFace[0].makeVisible();
                    break;
                case 2:
                    pointsFace[2].makeVisible();
                    pointsFace[5].makeVisible();
                    break;
                case 3:
                    pointsFace[2].makeVisible();
                    pointsFace[0].makeVisible();
                    pointsFace[5].makeVisible();
                    break;
                case 4:
                    pointsFace[1].makeVisible();
                    pointsFace[2].makeVisible();
                    pointsFace[5].makeVisible();
                    pointsFace[6].makeVisible();
                    break;
                case 5:
                    pointsFace[1].makeVisible();
                    pointsFace[2].makeVisible();
                    pointsFace[5].makeVisible();
                    pointsFace[6].makeVisible();
                    pointsFace[0].makeVisible();
                    break;
                case 6:
                    pointsFace[1].makeVisible();
                    pointsFace[2].makeVisible();
                    pointsFace[3].makeVisible();
                    pointsFace[4].makeVisible();
                    pointsFace[5].makeVisible();
                    pointsFace[6].makeVisible();
                    break;
            }
        } else{
            System.out.println("Error, no valid!!");
        }
    }
    
    /**
     * Retorna el valor de la cara del dado que es visible en el canvas
     */
    public byte getDigit(){
        return digit;
    }
    
    /**
     * Cambia la cara del dado un digito a la vez
     */
    public void next(){
        if (digit == 6){
            digit = 1;
        } else{
            digit ++;
        }
        makeVisible();
    }
    
    /**
     * Cambia la cara del dado por un nuevo valor dado por el usuario
     * @param newDigit nuevo digito por el que se reemplazara el dado
     */
    public void change(byte newDigit){
        digit = newDigit;
        makeVisible();
    }
    
    /**
     * Cambia la posicion espacial del dado dandole dos valores (x,y)
     */
    public void moveTo(int xPosition, int yPosition){
        body.moveHorizontal(xPosition);
        body.moveVertical(yPosition);
        for (int j = 0; j < 7; j++){
            pointsFace[j].moveHorizontal(xPosition);
            pointsFace[j].moveVertical(yPosition);
        }
    }
    
    /**
     * Cambia el "color" que posee el dado en el canvas
     * @param newColor nombre del nuevo color
     */
    public void changeColor(String newColor){
        Color = newColor;
        body.changeColor(newColor);
    }
    
    /**
     * Cambia el estado de "visibilidad" del dado en el canvas
     * @param newVisibility nuevo estado de visibilidad del dado
     */
    public void makeInvisible(){
        body.makeInvisible();
        visibility = false;
        for (int i = 0; i < 7; i++){
                pointsFace[i].makeInvisible();
            }
    }
    
    /**
     * Cambia aleatoriamente el valor del dado, como si se "lanzara" el dado
     */
    public void change(){
        for (int i = 0; i < 7; i++){
            pointsFace[i].makeInvisible();
        }
        Random random = new Random();
        byte newDigit = (byte) (Math.random()*6+1);
        digit = newDigit;
        makeVisible();
    }
}