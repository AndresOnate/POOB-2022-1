package domain; 

import java.util.LinkedList;
import java.util.ArrayList;


public class Curriculum{
    private ArrayList<Activity> activities;

    /**
     * Constructs an empty curriculum
     */
    public Curriculum(){
        activities= new ArrayList<Activity>();
    }
    
    /**
     * Add some activities
     */
    public void addSimpleActivities() throws  ActivityException {
        Activity examples[] = {
            new SimpleActivity("AYED", "Analisis de Algoritmos y Estructuras de Datos",
                "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar soluciones informáticas "+
                "efectivas y modulares a problemas puntuales reales aplicando técnicas y herramientas adecuadas.",
                4),
            new SimpleActivity("POOB" , "Programacin Orientada a Objetos",
                "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar aplicaciones software "+
                "de tamaño pequeño en el paradigma orientado a objetos aplicando metodologías, técnicas y herramientas adecuadas.",
                4),        
            new SimpleActivity("MBDA", "Modelos y bases de Datos", 
                "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar modelos de datos "+
                 "y diseñar, construir y manipular bases de datos relacionales.",
                4),
                
            new SimpleActivity("IAIA", "Fundamentos de Inteligancia Artificial", 
                "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar artefactos que procedan " +
                 "inteligentemente para la solución de problemas simples.s.",
                4),
        };
        for(Activity a : examples) {
            activities.add(a);
        }
    }
    
  
    /**
     * Get an activity
     * @param code 
     */
    public Activity getActivity(String code){
        Activity res=null;
        for(Activity a: activities){
            if (a.getCode().equals(code)){
                   res=a;
            }
        }
        return res;
    }


    /**
     * Add a new activity
     * @param code
     * @param name
     * @param credits
     * @param learningResults
     */
    public void addSimple(String code, String name, String credits, String learningResults) throws ActivityException{
        if(code.equals("")) throw new ActivityException(ActivityException.MISSING_CODE);
        if(name.equals("")) throw new ActivityException(ActivityException.MISSING_NAME);
        for (Activity a: activities){
            if (a.getCode().equals(code)) throw new ActivityException(ActivityException.EXISTING_CODE);
        }
        Integer creditos;

        try{
            creditos = Integer.parseInt(credits);
            if(creditos < 0 || creditos > 10) throw new ActivityException(ActivityException.RANGE_CREDITS);
        } catch (NumberFormatException e){
            throw new ActivityException(ActivityException.NUMERIC_CREDITS);   
        }
        activities.add(new SimpleActivity(code.equals("") ? "No especificado":code, name, learningResults, credits.equals("") ? null: Integer.parseInt(credits)));
        if (learningResults.length() < 10) throw new ActivityException (ActivityException.learningResults_);
   
    }
 
    /**
     * Returns activities that contain a pattern in their name
     * @param pattern the search pattern
     * @return 
     */
    public ArrayList<Activity> search(String pattern){
        ArrayList<Activity> resultados= new ArrayList<Activity>();
        for(Activity a: activities){
            if(a.getName().contains(pattern)){
                resultados.add(a);
            }   
        }
        return resultados;
    }


    @Override
    public String toString(){
        StringBuffer resultado=new StringBuffer();
        String credits; 
        for(Activity a: activities) {
            try{
                credits=a.credits()+" ";
            }catch(ActivityException e){
                credits="No definidos";
            }
            String informacion="Sigla: "+a.getCode()+"\n"+"Creditos:"+credits+"\n"+a.getName()+"\n"+a.results();
            resultado.append(informacion.length()<=160? informacion: informacion.substring(0,160)+"...");
            resultado.append('\n');
            resultado.append('\n');
        }
        return resultado.toString();
    }
}
