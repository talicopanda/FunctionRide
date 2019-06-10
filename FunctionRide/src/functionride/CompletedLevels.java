package functionride;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nm
 */
public class CompletedLevels {  
    private String name;
    private int levels;
    
    //constructor
    public CompletedLevels(int n, String t){
        name = t;
        levels =n;
        
    }
    
    //accessors
    public int getLevels(){
        return levels;
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return "Name"+":"+" "+this.name+"\n"+"Levels completed"+":"+this.levels;
    }
    
    //mutators
    public void setLevels(int n){
        levels = n;
    }
    
    public void setName(String n){
        name=n;
    }
    

    
}