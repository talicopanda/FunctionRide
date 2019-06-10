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
    private String levels;
    
    //constructor
    public CompletedLevels(String n, String t){
        name = t;
        levels =n;
        System.out.println("Hello");
    }
    
    //accessors
    public String getLevels(){
        return levels;
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return "Name";
    }
    
    //mutators
    public void setLevels(String n){
        levels = n;
    }
    
    public void setName(String n){
        name=n;
    }
    

    
}
