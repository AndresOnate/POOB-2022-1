package domain;

public abstract class Activity{
    private String code;
    private String name;
    
    public Activity(String code, String name){
        this.code=code;
        this.name=name;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getName(){
        return name;
    }
    
    public String results(){
        return "";
    }
        
    /**Calculate the number of credits 
     * @return int credits 
    * */
    public abstract int credits() throws ActivityException;
    
    /**Calculate the number of credits conscodeering the well-defined activities
     * @return 
     * */
    public abstract int definedCredits();
    
    /**Calculate the number of a specific activity
    * @param name of the activity
    * @return credits
    * */
    public abstract int credits(String name) throws ActivityException;

    
}
