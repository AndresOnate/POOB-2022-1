package domain;

public class SimpleActivity extends Activity{
    private Integer credits;
    private String learningResults;
    
    public SimpleActivity(String code, String name,Integer credits) throws ActivityException {
        super(code,name);
        this.credits=credits;
    }
    
    public SimpleActivity(String code, String name, String learningResults, Integer credits) throws ActivityException {
        super(code,name);
        this.learningResults=learningResults;
        this.credits=credits;
    }
    
    @Override
    public String results(){
        return learningResults;
    }
    
    @Override
     /**Calculate the number of credits 
     * @throws  SIMPLE_WITHOUT_CREDITS if credits = NULL
     * @return credits 
     * */
     public int credits() throws ActivityException{
        if (credits == null) throw new ActivityException(ActivityException.SIMPLE_WITHOUT_CREDITS);
        return credits;           
    }
    
    @Override
     /**Calculate the number of credits conscodeering the well-defined activities
     * @return int credits well-defined
     * */
    public int definedCredits(){
        return (credits != null ? credits: 0);           
    } 
        
    @Override
    /**Calculate the number of a specific activity
    * @param name of the activity
    * @return credits
    * */
    public int credits(String name) throws ActivityException{
        return (this.getName() != name ? credits:0);       
    }
    
    /**
     * @return A string with the information of the simple activity
     */
    public String toString() {
        String informacion = "Sigla: "+ this.getCode() +"\n"+"Creditos:"+ credits +"\n"+ this.getName() +"\n"+ learningResults;
        return informacion.length()<=160? informacion: informacion.substring(0,160)+"...";
    }
}

