package Square;


/**
 * ShyDome its a class of dome wich is sneaky when a tourist will take a photo
 * of her. When a tourist take a photo and a ShyDome is in the vision angle
 * she will hide behind of him if she can
 * 
 * @authors Nicolás Ariza Barbosa and Andres Camilo Oñate Quimbayo 
 * @version 1.0
 */
public class ShyDome extends Dome{
    public ShyDome(String newColor, int positionX, int positionY){
        super(newColor, positionX, positionY, "shy");
    }
}    
