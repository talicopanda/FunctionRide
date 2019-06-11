/*
 * Sukhraj, Tales, Sergio
 * June 8 2019
 * keeps track of info to put in leaderboard
 */


package functionride;


public class CompletedLevels {  
    //attributes
    private String name;
    private int levels;
    
    /**
     * main constructor 
     * @param n the number of levels the player has completed
     * @param t the name of the player
     */
    public CompletedLevels(int n, String t){
        name = t;
        levels =n;
        
    }
    
    /**
     * allows us to access the number of levels a player has completed
     * @return number of levels
     */
    public int getLevels(){
        return levels;
    }
    /**
     * allows us to access the name of a player
     * @return their name
     */
    public String getName(){
        return name;
    }
    
    /**
     * output all the info about a player
     * @return a string that lists all the info
     */
    public String toString(){
        return "Name"+":"+" "+this.name+"\n"+"Levels completed"+":"+this.levels;
    }
    
    /**
     * allows us to set the number of levels a player has completed
     * @param n the number of levels
     */
    public void setLevels(int n){
        levels = n;
    }
    
    /**
     * allows us to set the name of a player
     * @param n their name
     */
    public void setName(String n){
        name=n;
    } 
    

    
}