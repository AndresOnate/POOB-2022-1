package domain;
import java.util.ArrayList;

public class CompoundActivity extends Activity{
    private ArrayList<Activity> activities;
    
    public CompoundActivity(String code, String name) {
        super(code,name);
        activities= new ArrayList<Activity>();
    }
    
    public void addActivity(Activity a){
        activities.add(a);
    }
    
    @Override
     /**Calculate the number of credits
     * @throws  EMPTY_COMPOUND if activities is Empty
     * @return int credits 
     * */
    public int credits() throws ActivityException{
        int credits = 0;
        if (activities.isEmpty()) throw new ActivityException(ActivityException.EMPTY_COMPOUND);
        for (Activity actividad: this.activities){
            credits += actividad.credits();
        }
        return credits;
    }   
    
    @Override
     /**Calculate the number of credits conscodeering the well-defined activities
     * @return int credits well-defined
     * */
    public int definedCredits(){
        int tcredits = 0;
        for (Activity actividad: this.activities){
            tcredits += actividad.definedCredits();
        }
        return tcredits;         
    } 
    
    @Override
    /**Calculate the number of a specific activity
    * @param name of the activity
    * @return credits
    * */
    public int credits(String name) throws ActivityException{
        int totalCredits = 0;
        for (Activity actividad: this.activities){
            int cont = 0;
            totalCredits += actividad.definedCredits();
            if (totalCredits != 0){
                cont ++;
            }
            if (cont == 2) throw new ActivityException(ActivityException.ACTIVITY_IS_REPEATED);
        }
        return totalCredits;         
    }
}
