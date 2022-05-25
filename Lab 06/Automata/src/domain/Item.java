package domain;
import java.awt.Color;
import java.io.Serializable;

/*No olviden adicionar la documentacion*/
public interface Item extends Serializable {
  int ROUND = 1;
  int SQUARE = 2;


  /**
   * Desición a tomar por un Item
   */
  void decide();
   
  /**
   * Método que cambia el estado
   */
  default void change(){
  };
  
  /**
   * Método para obtener la forma del elemento
   * @return int Shape
   */
  default int shape(){
      return ROUND;
  }
  
    /**
   * Método para obtener el color del elemento
   * @return Color
   */
  default Color getColor(){
      return Color.black;
  };
  
    /**
   * Método para conocer si un elemento está vivo en el tablero
   * @return boolean El elemento esta vivo.
   */
  default boolean isAlive(){
      return false;
  }
  
}
