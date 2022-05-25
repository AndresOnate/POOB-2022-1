package domain;

import java.awt.Color;


public abstract class Agent{
    
    public final static char UNKNOWN='u', ALIVE='a', DEAD='d';
    protected char state;
    private int age;

    /**Create a new agent
     * 
     */
    public Agent(){
        state=UNKNOWN;
        age=0;
    }


    /**The agent turns one life span old
     * 
     */
    protected void turn(){
        age++;
    }    
    
     /**Returns the age
    @return 
     */   
    public final int getAge(){
        return age;
    }    

    /**Returns if alive
    @return true, if ALIVE; false, otherwise
     */
    public final boolean isAlive(){
        return (state == Agent.ALIVE) ;
    }
    
}
