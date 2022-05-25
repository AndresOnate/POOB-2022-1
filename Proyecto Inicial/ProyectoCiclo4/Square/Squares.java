package Square;
import Shapes.*;

import java.util.Random;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.geom.*;
/**
 * Write a description of class Square here.
 * 
 * @author Nicolas Ariza, Andres Oñate
 * @version 1.0 (07 february 2022)
 */
public class Squares
{
    private int dimensionX;
    private int dimensionY;
    private int safetyDistance;
    private boolean state;
    private Rectangle square;
    private ArrayList<String> colorDomes = new ArrayList<String>();
    private ArrayList<Dome> totalDomes = new ArrayList<Dome>();
    private ArrayList<String> colorTourists = new ArrayList<String>();
    private ArrayList<Tourist> totalTourists = new ArrayList<Tourist>();
    private HashMap<String,Integer> idDomes = new HashMap<String,Integer>();
    private ArrayList<Integer> desiredViewPhoto = new ArrayList<Integer>();
    private HashMap <Tourist, Tourist> angleShot = new HashMap <Tourist, Tourist>();
    private boolean visibility;
    
    /**
     * Constructor for objects of class Square
     */
    public Squares(int width, int height, int safeDistance)
    {
        dimensionX = width;
        dimensionY = height;
        safetyDistance = safeDistance;
        square = new Rectangle(dimensionX, dimensionY);
        visibility = false;
        state = false;
    }
    
  
    /**
     * Constructor for objects of class Square C2
     */
    public Squares(int [] dimensions, int[][] dome, int [] desiredView)
    {
        dimensionX = dimensions[0];
        dimensionY = dimensions[1];
        square = new Rectangle(dimensionX, dimensionY);
        visibility = false;
        state = false;
        Random rand = new Random();
        ArrayList<String> colors = new ArrayList<String>();
        colors.addAll(Arrays.asList(new String[] {"white", "red", "green", "black", "magenta"}));
            
        for (int i = 0; i < dimensions[2]; i++){
            int nAleatorio = rand.nextInt(colors.size());
            String color = colors.get(nAleatorio);
            this.desiredViewPhoto.add(desiredView[i]); // Revsar para futuras pruebas
            this.addDome(color,dome[i][0],dome[i][1]);
            this.idDomes.put(color, i+1);
            colors.remove(color);
        }
    }
    
    
    /**
     * Define la foto requerida
     * @param domes Array con los colores de los domos
     */public void  defineRequestedPhoto(String [] domes){
        this.desiredViewPhoto.clear();
        for (int i= 0; i < domes.length;i++){
            this.desiredViewPhoto.add(this.idDomes.get(domes[i]));
            //System.out.println(this.idDomes.get(domes[i]) + "");
        }
    }
    
    
    /**
     * Obtener la desiredViewPhoto
     */
    public ArrayList<Integer>  getDesiredViewPhoto (){
        return  this.desiredViewPhoto;
    }
    
    /**
     * Ordena Los domos por su posición en Y
     */
    private ArrayList<String> sortByYposition(ArrayList<String> d, boolean isAsc){
        ArrayList<String> domesSort = new ArrayList<String>();
        ArrayList<Dome> domes  = this.totalDomes;
        Collections.sort(domes,Comparator.comparing(Dome::getyPosition));   
        Collections.reverse(domes);
        if (isAsc){
            Collections.reverse(domes);
        }
        for (Dome dome: domes){
            String color = dome.getColor();
            if (d.contains(color)){
                domesSort.add(dome.getColor());      
            }  
        }
        return domesSort;
    }
     
    /**
     * Ordena Los domos por su posición en X
     */
    private ArrayList<String> sortByXposition(ArrayList<String> d, boolean isDesc){
        ArrayList<String> domesSort = new ArrayList<String>();
        ArrayList<Dome> domes  = this.totalDomes;
        Collections.sort(domes,Comparator.comparing(Dome::getxPosition));   
        if (!isDesc){
            Collections.reverse(domes);
        }
        for (Dome dome: domes){
            String color = dome.getColor();
            if (d.contains(color)){
                domesSort.add(dome.getColor());      
            }    
        }
        return domesSort;
    }
    
    /**
     * Devuelve un ArrayList de Enteros con los Id de los domos, toma como parametro un ArrayList<String> con los domos
     */
    public ArrayList<Integer> colorToIdDomes(ArrayList<String> colorDomes){
        ArrayList<Integer> idDomes = new ArrayList<Integer>();
        for (String dome: colorDomes){
            idDomes.add(this.idDomes.get(dome));
        }
        return idDomes;
    }
    
    /**
     * Ciclo 2: Devuelve los turistas qye pueden tomar la foto requerida.
     */
    public ArrayList<String> whoTakePhoto() throws SquareException{
        ArrayList<String> touristCantPhoto = new  ArrayList<String>();
        for (Tourist tourist: this.totalTourists){
            ArrayList<String> photo = touristTakePhoto(tourist.getColor());
            ArrayList<Integer> idPhoto = new ArrayList<Integer>();
            idPhoto.addAll(colorToIdDomes(photo));
            if (idPhoto.equals(this.desiredViewPhoto)){
                touristCantPhoto.add(tourist.getColor());
            }              
        }
        return touristCantPhoto;
    }
    
    /**
     * Este método toma la foto requerida si es posible
     * Ciclo 2
     */
    public void  takeRequestedPhoto() throws SquareException {
        ArrayList<String> touristCantPhoto = whoTakePhoto();
        if (!touristCantPhoto.isEmpty()){
            touristTakePhoto(touristCantPhoto.get(0)); 
        }
    }
    
    /**
     * Este método le da instrucciones a un determinado turista para que pueda tomar la foto.
     * @param 
     * Ciclo 2
     */
    public ArrayList<String> touristInstructionsRequestedPhoto(String tourist) throws SquareException{
        ArrayList<String> Photo = new ArrayList<String>();
        if (colorTourists.contains(tourist) ){
            byte index = (byte) colorTourists.indexOf(tourist);
            double angle =  totalTourists.get(index).getAngle();
            int xPosition = totalTourists.get(index).getxPosition();
            int yPosition = dimensionY - totalTourists.get(index).getyPosition();
            double iAngle = Math.PI/4;
            while (iAngle < 2*Math.PI){
                Photo.clear();
                touristMove(tourist, xPosition, yPosition, angle);
                Photo =  touristTakePhoto(tourist);
                ArrayList<Integer> idPhoto = colorToIdDomes(Photo);
                if (!idPhoto.isEmpty() && idPhoto.equals(this.desiredViewPhoto)){
                        return Photo;
                }   
                angle += Math.PI/4;
                iAngle += Math.PI/4;
            }                        
        }
        return  Photo;
    }
    
    /**
     * Allow you to add a new dome to the square
     * @param color add a color for the dome created
     * @param x describes the x position of the dome
     * @param y describes the y position of the dome
     */
    public void addDome(String color, int x, int y){
        if ((x <= dimensionX && y <= dimensionY) && !(colorDomes.contains(color)) && (validPosition(color, x, dimensionY-y, safetyDistance))){
            Dome dome = new Dome(color, x, dimensionY - y);
            colorDomes.add(color);
            totalDomes.add(dome);
            makeVisible();
        }
    }
    
    /**
     * Allow you to add a new dome to the square
     * @param color add a color for the dome created
     * @param x describes the x position of the dome
     * @param y describes the y position of the dome
     */
    public void addDome(String type, String color, int x, int y) throws SquareException{
        if (!validPosition(color,x, dimensionY-y, safetyDistance)) throw new SquareException(SquareException.DOMES_OVERLAP);
        if ((x <= dimensionX && y <= dimensionY) && !(colorDomes.contains(color))){
             if (type == "normal"){
                Dome dome = new Dome(color, x, dimensionY - y);
                colorDomes.add(color);
                totalDomes.add(dome);
                makeVisible();
            } else if (type == "shy"){
                ShyDome dome = new ShyDome(color, x, dimensionY - y);
                colorDomes.add(color);
                totalDomes.add(dome);
                makeVisible();
            }else if (type == "fixed"){
                fixedDome dome = new fixedDome(color, x, dimensionY - y);
                colorDomes.add(color);
                totalDomes.add(dome);
                makeVisible();
            }
        }
    }
   
    private boolean validPosition(String Color, int x, int y, int safeDistance){
        for (Dome dome: this.totalDomes ){

            if ((dome.getyPosition() == y  && dome.getxPosition() == x) && Color != dome.getColor()){
                return false;
            }
        }
        for (Tourist tourist: this.totalTourists){
            int xTourist = tourist.getxPosition();
            int yTourist = tourist.getyPosition();         
            double cateto1 = x - xTourist;
            double cateto2 = y - yTourist;
            double hipotenusa = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
            if ((xTourist == x && yTourist == y || hipotenusa < safeDistance) && Color != tourist.getColor() ){
                return false;
            }
        }
        return true;
    }
       
    /**
     * Allow you to delete a dome previusly added to the square
     * @param dome associate to the color of the dome
     */
    public void delDome(String dome){
        if (colorDomes.contains(dome)){
            byte index = (byte) colorDomes.indexOf(dome);
            String type = totalDomes.get(index).type();
            if (type != "fixed"){
                colorDomes.remove(dome);
                totalDomes.remove(index);
                makeVisible();
            }
        }
        
    }
    
    /**
     * This method is used to add a new visitor to the red square
     * @param color associate to the color of the tourist
     * @param x describe the x coordinate of the tourist in the square
     * @param y describe the y coordinate of the tourist in the square
     * @param type describe the type of tourist ("normal,"prudent,"")
     */
    public void touristArrive(String Type , String color, int x, int y) throws SquareException{
        int varSafeDsitance = safetyDistance;
        if (Type == "prudent"){
             varSafeDsitance = 2*varSafeDsitance;        
        }
        if (!validPosition(color,x, dimensionY-y, varSafeDsitance)) throw new SquareException(SquareException.SAFETY_DISTANCE);
        if (x <= dimensionX && y <= dimensionY && !(colorTourists.contains(color)) ){
            Tourist tourist0 = new Tourist(color, x, dimensionY - y, 0, safetyDistance);
            if (Type == "prudent"){
                 tourist0 = new prudent(color, x, dimensionY - y, 0, safetyDistance);
            } else if (Type == "perfectionist"){
                 tourist0 = new  perfectionist(color, x, dimensionY - y, 0, safetyDistance);
            } else if (Type == "rebel"){
                 tourist0 = new  rebel(color, x, dimensionY - y, 0, safetyDistance);
            }     
            Tourist tourist1 = new Tourist("green", x, dimensionY - y, Math.PI, 2000, 18000);
            angleShot.put(tourist0, tourist1);
            colorTourists.add(color);
            totalTourists.add(tourist0);
            makeVisible();
        } 
    }
    
        /**
     * This method is used to add a new visitor to the red square
     * @param color associate to the color of the tourist
     * @param x describe the x coordinate of the tourist in the square
     * @param y describe the y coordinate of the tourist in the square
     */
    public void touristArrive(String color, int x, int y){
        int varSafeDsitance = safetyDistance;
        if (x <= dimensionX && y <= dimensionY && !(colorTourists.contains(color)) && (validPosition(color,x, dimensionY-y, varSafeDsitance))){
            Tourist tourist0 = new Tourist(color, x, dimensionY - y, 0, safetyDistance);    
            Tourist tourist1 = new Tourist("green", x, dimensionY - y, Math.PI, 2000, 18000);
            angleShot.put(tourist0, tourist1);
            colorTourists.add(color);
            totalTourists.add(tourist0);
            makeVisible();
        } 
    }
           
    /**
     * Allow you to move a specific tourist around the red square
     * @param tourist associate with the color of a tourist
     * @param x describes the new coordinate x of this person
     * @param y describes the new coordinate y of this person
     * @param angle describes the view angle of the camera
     */
    public void touristMove(String tourist, int x, int y, double angle) throws SquareException{
        for (Tourist t: totalTourists){
            double temp = Math.sqrt(Math.pow(t.getxPosition() - x, 2) + Math.pow(t.getyPosition() - y, 2));
            if (tourist != t.getColor() && temp < this.safetyDistance){
                throw new SquareException(SquareException.SAFETY_DISTANCE);
            }
        }
        int varSafeDsitance = safetyDistance;
        String type = "";
        boolean mState = false;
        if (colorTourists.contains(tourist)){
            byte index = (byte) colorTourists.indexOf(tourist);
            type = totalTourists.get(index).getType();
        }    
        if ( type != "prudent"  && (validPosition(tourist, x, dimensionY - y, safetyDistance))){
            mState = true;
        }else if (type == "prudent"){
            mState = validPosition(tourist, x, dimensionY - y, 2*varSafeDsitance);
        }
        if (mState){  
            byte index = (byte) colorTourists.indexOf(tourist);   
            Tourist tourist0 = totalTourists.get(index);
            Tourist tourist1 = angleShot.get(tourist0);
            
            totalTourists.get(index).setAngle(angle);
            totalTourists.get(index).setPosition(x,dimensionY - y);
            
            tourist1.setAngle(angle + Math.PI);
            tourist1.setPosition(x,dimensionY - y);
            angleShot.remove(tourist0);
            angleShot.put(tourist0, tourist1);
            makeVisible();
        }
    }   
    
    private boolean validarDomos(Dome domo, double x , double y){
        double XDome = domo.getxPosition();
        double yDome = dimensionY - domo.getyPosition();
        double cateto1 = x - XDome;
        double cateto2 = y - yDome;
        double distancia1 = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
        double m = 0;
        if (cateto1 != 0){
            m = ((y - yDome)/(x - XDome));
        }        
        boolean flag = true;
        for (Dome dome: this.totalDomes ){  
            int XDomoI = dome.getxPosition();
            int yDomoI = dimensionY - dome.getyPosition();
            double catetoI1 = x - XDomoI;
            double catetoI2 = y - yDomoI;
            double distancia2 = Math.sqrt(cateto1*catetoI1 + cateto2*catetoI2);
            if (XDome ==  XDomoI && distancia2 < distancia1 && m == 0.0){
                return false;
            }
            if ((yDomoI == m*(XDomoI-x) + y ) && distancia2 < distancia1 ){
                return false;   
            }   
        }
        return true;
    }
       
    /**
     * Take a photo showing the domes at a 180-degree camera viewing angle, 
     * as well as taking into account the tourist's angle. 
     * @param actualTourist associate with the color of a tourist
     */
    public ArrayList touristTakePhoto(String actualTourist) throws SquareException{
        if (colorTourists.contains(actualTourist)){
            ArrayList<String> Photo = new ArrayList<String>();
            byte index = (byte) colorTourists.indexOf(actualTourist);
            String type = totalTourists.get(index).getType(); 
            int posicionx = totalTourists.get(index).getxPosition();
            double m = -totalTourists.get(index).getPendiente();
            int posiciony = dimensionY - totalTourists.get(index).getyPosition();
            double caseAngle = totalTourists.get(index).getAngle();
            Tourist view = angleShot.get(totalTourists.get(index));
            int repeats = 0;
            if (caseAngle  == 0 || caseAngle ==  2*Math.PI){           
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (domo.getxPosition() >= posicionx){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }                        
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, false);                         
            } else if (caseAngle  == Math.PI/2 ){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo = totalDomes.get(i);
                    if (dimensionY - totalDomes.get(i).getyPosition() >=  posiciony){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }   
                    }
                }
                view.rainbow();
                Photo = sortByXposition(Photo, true);  
            } else if (caseAngle  == Math.PI){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (domo.getxPosition() <= posicionx){
                         if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }        
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, false);
            } else if (caseAngle  == 3*Math.PI/2 ){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (dimensionY - domo.getyPosition() <= posiciony){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }      
                    }
                }
                view.rainbow();
                Photo = sortByXposition(Photo, false);
                 // Analisis como un plano cartesiano normal
            } else if (caseAngle > 0 && caseAngle < Math.PI/2  ){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (dimensionY - domo.getyPosition() >= (m*(domo.getxPosition()  - posicionx) + posiciony)){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }    
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, true);
            }else if ( caseAngle > Math.PI/2  && caseAngle < Math.PI){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (dimensionY - totalDomes.get(i).getyPosition() >= (m*(totalDomes.get(i).getxPosition()  - posicionx) + posiciony)){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }    
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, false);
            }else if (caseAngle > Math.PI  && caseAngle< 3*Math.PI/2 ){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (dimensionY - domo.getyPosition() <= (m*(domo.getxPosition()  - posicionx) + posiciony)){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }    
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, false);
            }else if (caseAngle > 3*Math.PI/2 && caseAngle < 2*Math.PI){
                for (int i = 0; i < totalDomes.size(); i++){
                    Dome domo =  totalDomes.get(i);
                    if (dimensionY - domo.getyPosition() <= (m*(domo.getxPosition()  - posicionx) + posiciony)){
                        if (totalDomes.get(i).type() == "shy"){
                            totalDomes.get(i).letMeBeMe(posicionx,dimensionY - posiciony,caseAngle,repeats,dimensionX,dimensionY);
                            repeats += 1;
                        }else if (validarDomos(domo,posicionx, posiciony)){
                            Photo.add(totalDomes.get(i).getColor());
                        }   
                    }
                }
                view.rainbow();
                Photo = sortByYposition(Photo, true);
            }
            if (type != "perfectionist" && type != "rebel"){
                return Photo;
            }
            ArrayList<Integer> idPhoto = colorToIdDomes(Photo);
            if (!idPhoto.isEmpty() && type == "perfectionist"  && idPhoto.equals(this.desiredViewPhoto)){
                return Photo;
            }
            if (!idPhoto.isEmpty() && type == "rebel"  && idPhoto.equals(this.desiredViewPhoto)){
                touristMove(actualTourist, posicionx, dimensionY-totalTourists.get(index).getyPosition(), caseAngle + Math.PI);
                return null;
            }
        }
        return null;
    }  
    
    private ArrayList touristTakeFakePhoto(String actualTourist, boolean domie) {
        if (colorTourists.contains(actualTourist)){
            ArrayList<String> Photo = new ArrayList<String>();
            byte index = (byte) colorTourists.indexOf(actualTourist);
            int posicionx = totalTourists.get(index).getxPosition();
            int posiciony = dimensionY - totalTourists.get(index).getyPosition();
            double caseAngle = totalTourists.get(index).getAngle();
            if (caseAngle  == 0){
                for (int i = 0; i < totalDomes.size(); i++){
                    if (totalDomes.get(i).getxPosition() >= posicionx){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                
                return Photo;                          
            } else if (caseAngle  == Math.PI/2 ){
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() >= posiciony){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            } else if (caseAngle  == Math.PI){
                for (int i = 0; i < totalDomes.size(); i++){
                    if (totalDomes.get(i).getxPosition() <= posicionx){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            } else if (caseAngle  == 3*Math.PI/2 ){
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() <= posiciony){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            } else if (caseAngle > 0 && caseAngle < Math.PI/2  ){
                double m = -(25) / (7/2);
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() >= (m*(totalDomes.get(i).getxPosition()  - posicionx) + posiciony)){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            }else if ( caseAngle > Math.PI/2  && caseAngle < Math.PI){
                double m = (7/2) / 25 ;
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() >= (m*(totalDomes.get(i).getxPosition()  - posicionx) + posiciony)){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            }else if (caseAngle > Math.PI  && caseAngle< 3*Math.PI/2 ){
                double m = -(25) / (7/2);
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() <= (m*(totalDomes.get(i).getxPosition()  - posicionx) + posiciony)){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            }else if (caseAngle > 3*Math.PI/2 && caseAngle < 2*Math.PI){
                double m = (7/2) / 25;
                for (int i = 0; i < totalDomes.size(); i++){
                    if (dimensionY - totalDomes.get(i).getyPosition() <= (m*(totalDomes.get(i).getxPosition()  - posicionx) + posiciony)){
                        Photo.add(totalDomes.get(i).getColor());
                    }
                }
                return Photo;
            }
        }
        return null;
    }
        
    /**
     * returns the domes (colors) that are in the square. 
     */
    public ArrayList<String> domes(){
        if (colorDomes.isEmpty()){
            return null;
        }
        return colorDomes;
        
    }
        
    /**
     * returns the tourists (colors) that are in the square. 
     * @return ArrayList tourists
     */
    public ArrayList<String> tourists(){
        if (colorTourists.isEmpty()){
            return null;
        }
        return colorTourists;
    }

    /**
     * Returns the x and y position of a specified dome.
     * @param actualDome associate with the color of a dome
     * @return Array int
     */
    public int [] dome(String actualDome){
        if (colorDomes.contains(actualDome)){
            byte index = (byte) colorDomes.indexOf(actualDome);
            Dome temporal = totalDomes.get(index); 
            int [] list = new int[3];
            list[0] = temporal.getxPosition();
            list[1] = dimensionY-temporal.getyPosition();
            list[2] =  this.idDomes.get(actualDome);
            return list;
        } else {
            return null;
        }
    }
    
    /**
     * Returns the x and y position of a specified tourist.
     * @param actualDome associate with the color of a tourist.
     * @return Array int
     */
    public int [] tourist(String actualTourist){
        if (colorTourists.contains(actualTourist)){
            byte index = (byte) colorTourists.indexOf(actualTourist);
            Tourist temporal = totalTourists.get(index); 
            int [] list = new int[2];
            list[0] = temporal.getxPosition();
            list[1] = dimensionY-temporal.getyPosition();
            return list;
        } else {
            return null;
        }
    }
    
    /**
     * Makes the square visible
     */
    public void makeVisible(){
        visibility = true;
        square.makeVisible();
        if (!(totalDomes.isEmpty())){
            for (int i = 0; i < totalDomes.size(); i++){
                Dome temporal = totalDomes.get(i);
                temporal.makeVisible();
                totalDomes.add(i, temporal);
                totalDomes.remove(i+1);
            }
        }
        if (!(totalTourists.isEmpty())){
            for (int i = 0; i < totalTourists.size(); i++){
                Tourist temporal = totalTourists.get(i);
                temporal.makeVisible();
                totalTourists.add(i, temporal);
                totalTourists.remove(i+1);
            }
        }
    }
    
    /**
     * Makes the square Invisible
     */
    public void makeInvisible(){
        visibility = false;
        square.makeInvisible();
        if (!(totalDomes.isEmpty())){
            for (int i = 0; i < totalDomes.size(); i++){
                Dome temporal = totalDomes.get(i);
                temporal.makeInvisible();
                totalDomes.add(i, temporal);
                totalDomes.remove(i+1);
            }
        }
        if (!(totalTourists.isEmpty())){
            for (int i = 0; i < totalTourists.size(); i++){
                Tourist temporal = totalTourists.get(i);
                temporal.makeInvisible();
                totalTourists.add(i, temporal);
                totalTourists.remove(i+1);
            }
        }
    }
    
    
    /**
     * Returns whether the last action could be performed or not.
     */
    public void ok(){
        if (state){
            JOptionPane.showMessageDialog(null, "Tarea realizada");
        }else{
            JOptionPane.showMessageDialog(null, "Error, value not found");
        }
        
    }
    
    /**
     * Finish the simulator
     */
    public void finish(){
        makeInvisible();
        dimensionX = 0;
        dimensionY = 0;
        safetyDistance = 0;
        square = null;
        colorDomes.clear();
        totalDomes.clear();
        colorTourists.clear();
        totalTourists.clear();
        angleShot.clear();
        System.exit(-1);
    }
}