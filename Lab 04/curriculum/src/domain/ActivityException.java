package domain;


/**
 * Write a description of class ActivityException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ActivityException extends Exception{
    public static final String EMPTY_COMPOUND = "Error, no compounds found ";
    public static final String SIMPLE_WITHOUT_CREDITS = "Current activity have no defined credits";
    public static final String ACTIVITY_IS_REPEATED = "The activity is repeated";
    public static final String DUPLICATE_ACTIVITY = "The activity is duplicate";
    public static final String MISSING_CODE = "No ingresa la sigla del curso";
    public static final String EXISTING_CODE = "Da una sigla de un curso que ya existe ";
    public static final String NUMERIC_CREDITS = "Los creditos deben ser un valor numerico ";
    public static final String RANGE_CREDITS = "Los creditos estan fuera del rango";
    public static final String MISSING_NAME = "No ingresa el nombre del curso";
    public static final String learningResults_ = "Descripción de los resultados de aprendizaje muy corta ";
    /**
     * Constructor for objects of class ActivityException
     */
     public ActivityException(String message)
    {
        super(message);
    } 
}
