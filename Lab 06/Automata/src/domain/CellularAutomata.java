package domain;

import java.io.*;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

public class CellularAutomata implements Serializable{
    static private int LENGTH=30;
    private Item[][] items;
    
    /**
     * CellularAutomata Constructor
     */
    public CellularAutomata() {
        items =new Item[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                items[r][c]=null;
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
        return items[r][c];
    }
       
    /**
     * Método Que asigna un elemento en una posición específica 
     *
     * @param r row
     * @param c column
     * @param e Nuevo Item
     */
    public void setItem(int r, int c, Item e){
        items[r][c]=e;
    }
    
    public Item[][] getTable(){
        return items;
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
                if(items[i][j] != null){
                    items[i][j].decide();
                }else{
                    cellBorn(i, j);
                }
            }
        }
        for(int i = 0; i < LENGTH; i++){
            for(int j=0; j< LENGTH; j++){
                if(items[i][j] != null){
                    items[i][j].change();
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
                if((items[i + dirs[k][Y]][j + dirs[k][X]]) != null && (items[i + dirs[k][Y]][j + dirs[k][X]]).isAlive()){
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

    /**
     * Abre un archivo de tipo .dat que contiene un CellularAutomata
     * @param file El archivo con la información del automata
     * @return El CellularAutomata contenido en el archivo
     * @throws AutomataException Cuando ocurre un error al abrir
     */
    public static CellularAutomata open(File file) throws AutomataException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            CellularAutomata automata = (CellularAutomata) objectIn.readObject();
            objectIn.close();
            return automata;
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.FILE_NOT_FOUND);
        } catch (StreamCorruptedException e) {
            throw new AutomataException(AutomataException.CORRUPT_FILE);
        } catch (InvalidClassException e) {
            throw new AutomataException(AutomataException.INVALID_CLASS);
        } catch (OptionalDataException e) {
            throw new AutomataException(AutomataException.PRIMITIVE_DATA_ERROR);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.OPEN_ERROR);
        }
    }

    /**
     * Guarda la clase CellularAutomata en un archivo .dat
     * @param file El archivo/directorio donde se quiere guardar el automata
     * @throws AutomataException Cuando ocurre un error al guardar
     */
    public void save(File file) throws AutomataException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.FILE_NOT_FOUND);
        } catch (InvalidClassException e) {
            throw new AutomataException(AutomataException.INVALID_CLASS);
        } catch (NotSerializableException e) {
            throw new AutomataException(AutomataException.NOT_SERIALIZABLE);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.SAVE_ERROR);
        }
    }

    /**
     * Importa todos los Items de un archivo txt
     * @param file El archivo txt  que contiene la información de los Items
     * @throws AutomataException Cuando ocurre algún problema al importar
     */
    public static CellularAutomata importFile (File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            findErrors(lines);
            CellularAutomata automata = new CellularAutomata();
            for (String rLine : lines) {
                String[] splittedLine;
                splittedLine = rLine.trim().split(" ");
                int row = Integer.parseInt(splittedLine[1]);
                int column = Integer.parseInt(splittedLine[2]);
                Class cls = Class.forName("domain." + splittedLine[0]);
                Class[] parameterType = {CellularAutomata.class, int.class, int.class};
                automata.setItem(row,column,(Item)cls.getDeclaredConstructor(parameterType).newInstance(automata, row, column));
            }
            return automata;
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASS_NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ILLEGAL_ACCESS);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.INSTANTIATION_ERROR);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NOT_METHOD_FOUND);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.INVOCATION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.IMPORT_ERROR);
        }
    }


    /**
     * Exporta todos los los Items en un archivo txt con el formato adecuado
     * @param file El archivo/directorio donde se va a exportar
     * @throws AutomataException Error al exportar
     */
    public void export(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(writeCellularAutomata());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.EXPORT_ERROR);
        }
    }

    /**
     * Método que escribe el automata en un formato adecuado
     */
    private String writeCellularAutomata() {
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (items[i][j] != null) {
                        data.append(items[i][j].getClass().getSimpleName()).append(" ").append(i).append(" ").
                                append(j).append("\n");
                    }
                }
            }
            return data.toString();
        }


    /**
     * Abre un archivo de tipo .dat que contiene un CellularAutomata
     * @param file El archivo con la información del automata
     * @return El CellularAutomata contenido en el archivo
     * @throws AutomataException Cuando ocurre un error al abrir
     */
    public static CellularAutomata open00(File file) throws AutomataException {
        throw  new AutomataException(AutomataException.OPEN_UNDER_CONSTRUCTION);
    }

    /**
     * Guarda la clase CellularAutomata en un archivo .dat
     * @param file El archivo/directorio donde se quiere guardar el automata
     * @throws AutomataException Cuando ocurre un error al guardar
     */
    public void save00(File file) throws AutomataException {
        throw  new AutomataException(AutomataException.SAVE_UNDER_CONSTRUCTION);
    }

    /**
     * Importa todos los Items de un archivo txt
     * @param file El archivo txt  que contiene la información de los Items
     * @throws AutomataException Cuando ocurre algún problema al importar
     */
    public static CellularAutomata importFile00 (File file) throws AutomataException {
        throw  new AutomataException(AutomataException.IMPORT_UNDER_CONSTRUCTION);
    }

    /**
     * Exporta todos los los Items en un archivo txt con el formato adecuado
     * @param file El archivo/directorio donde se va a exportar
     * @throws AutomataException Error al exportar
     */
    public void export00(File file) throws AutomataException {
        throw  new AutomataException(AutomataException.EXPORT_UNDER_CONSTRUCTION);
    }



    /**
     * Abre un archivo de tipo .dat que contiene un CellularAutomata
     * @param file El archivo con la información del automata
     * @return El CellularAutomata contenido en el archivo
     * @throws AutomataException Cuando ocurre un error al abrir
     */
    public static CellularAutomata open01(File file) throws AutomataException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            CellularAutomata automata = (CellularAutomata) objectIn.readObject();
            objectIn.close();
            return automata;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        }
    }

    /**
     * Guarda la clase CellularAutomata en un archivo .dat
     * @param file El archivo/directorio donde se quiere guardar el automata
     * @throws AutomataException Cuando ocurre un error al guardar
     */
    public void save01(File file) throws AutomataException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        }
    }


    /**
     * Importa todos los Items de un archivo txt
     * @param file El archivo txt  que contiene la información de los Items
     * @throws AutomataException Cuando ocurre algún problema al importar
     */
    public static CellularAutomata importFile01(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            CellularAutomata automata = new CellularAutomata();
            for (String rLine : lines) {
                String[] splittedLine;
                splittedLine = rLine.trim().split(" ");
                int row = Integer.parseInt(splittedLine[1]);
                int column = Integer.parseInt(splittedLine[2]);
                Class cls = Class.forName("domain." + splittedLine[0]);
                Class[] parameterType = {CellularAutomata.class, int.class, int.class};
                automata.setItem(row,column,(Item)cls.getDeclaredConstructor(parameterType).newInstance(automata, row, column));
            }
            return automata;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        }
    }


    /**
     * Exporta todos los los Items en un archivo txt con el formato adecuado
     * @param file El archivo/directorio donde se va a exportar
     * @throws AutomataException Error al exportar
     */
    public void export01(File file)throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(writeCellularAutomata());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        }
    }


    /**
     * Importa todos los Items de un archivo txt
     * @param file El archivo txt  que contiene la información de los Items
     * @throws AutomataException Cuando ocurre algún problema al importar
     */
    public static CellularAutomata importFile02 (File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            CellularAutomata automata = new CellularAutomata();
            for (String rLine : lines) {
                String[] splittedLine;
                splittedLine = rLine.trim().split(" ");
                int row = Integer.parseInt(splittedLine[1]);
                int column = Integer.parseInt(splittedLine[2]);
                Class cls = Class.forName("domain." + splittedLine[0]);
                Class[] parameterType = {CellularAutomata.class, int.class, int.class};
                automata.setItem(row,column,(Item)cls.getDeclaredConstructor(parameterType).newInstance(automata, row, column));
            }
            return automata;
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASS_NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ILLEGAL_ACCESS);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.INSTANTIATION_ERROR);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NOT_METHOD_FOUND);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.INVOCATION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.IMPORT_ERROR);
        }
    }


    /**
     * Exporta todos los los Items en un archivo txt con el formato adecuado
     * @param file El archivo/directorio donde se va a exportar
     * @throws AutomataException Error al exportar
     */
    public void export02(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(writeCellularAutomata());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.EXPORT_ERROR);
        }
    }



    /**
     * Importa todos los Items de un archivo txt
     * @param file El archivo txt  que contiene la información de los Items
     * @throws AutomataException Cuando ocurre algún problema al importar
     */
    public static CellularAutomata importFile03(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            findErrors(lines);
            CellularAutomata automata = new CellularAutomata();
            for (String rLine : lines) {
                String[] splittedLine;
                splittedLine = rLine.trim().split(" ");
                int row = Integer.parseInt(splittedLine[1]);
                int column = Integer.parseInt(splittedLine[2]);
                Class cls = Class.forName("domain." + splittedLine[0]);
                Class[] parameterType = {CellularAutomata.class, int.class, int.class};
                automata.setItem(row,column,(Item)cls.getDeclaredConstructor(parameterType).newInstance(automata, row, column));
            }
            return automata;
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASS_NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ILLEGAL_ACCESS);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.INSTANTIATION_ERROR);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NOT_METHOD_FOUND);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.INVOCATION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.IMPORT_ERROR);
        }
    }

    /**
     * Exporta todos los los Items en un archivo txt con el formato adecuado
     * @param file El archivo/directorio donde se va a exportar
     * @throws AutomataException Error al exportar
     */
    public void export03(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(writeCellularAutomata());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.EXPORT_ERROR);
        }
    }


    /**
     * Verifica errores en un CellularAutomata de importación
     * @param lines lines enviadas desde el metodo importFile
     * @throws AutomataException IOException
     */
    private static void findErrors(ArrayList<String> lines) throws AutomataException, IOException {
        boolean errors = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] splittedLine;
            splittedLine = lines.get(i).trim().split(" ");
            try {
                Class cls = Class.forName("domain." + splittedLine[0]);
                if (!Item.class.isAssignableFrom(cls)) {
                    throw new ClassCastException();
                }
            } catch (ClassNotFoundException e) {
                errors = true;
                write("No se encontró la clase ", i + 1, splittedLine[0]);
            } catch (ClassCastException e) {
                errors = true;
                write("La clase no implementa Item ", i + 1, splittedLine[0]);
            } catch (Exception e) {
                errors = true;
                write("Error no esperado: ", i + 1, splittedLine[0]);
            }
            if (findErrorsPositions(splittedLine[1], i + 1)) {
                errors = true;
            }
            if (findErrorsPositions(splittedLine[2], i + 1)) {
                errors = true;
            }
        }
        if (errors) {
            throw new AutomataException(AutomataException.IMPORT_ERROR);
        }
    }

    /**
     * Escribe el archivo de errores
     * @param  error que se encontro
     * @param  line linea en la que se encontro el error
     * @param  fail Cual fue el dato que genero el error
     */
    private static void write(String error, int line, String fail) throws IOException {
        File file = new File("automataErrG.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write("Linea " + line +": " + error + fail + "\n");
        fw.close();
    }

    /**
     * Verifica que un entero de la entrada cumpla con las caracteristicas del CellularAutomata
     */
    private  static boolean findErrorsPositions(String number, int line) throws IOException {
        try {
            int x = Integer.parseInt(number);
            if (x < 0 || x >= LENGTH) {
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) {
            write("No es un entero: ", line, number);
            return true;
        } catch (IndexOutOfBoundsException e) {
            write("Entero fuera de rango: ", line, number);
            return true;
        } catch (Exception e) {
            write("Error no esperado: ", line, number);
            return true;
        }
        return false;
    }



}
