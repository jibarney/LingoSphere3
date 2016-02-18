/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JoAnn
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set ;
import java.util.HashSet ;
import java.util.AbstractMap ;
import java.util.List ;
import java.util.ArrayList ;
import java.util.Collections;

public class StudyList {
    private String listName ;
    private Map<String, String> wordListHashMap = new HashMap<String,String>() ; 
    Set wordKeySet  = wordListHashMap.keySet() ;
    List wordKeys ;
    Iterator iterator ;
    
    public StudyList(String listName) 
    {
        this.listName = listName ;
    }
    
    String getListName() { return this.listName; }
    
    Map<String,String> getMapping(){ return wordListHashMap ; } ;
    
    void setListName(String lname) { this.listName = lname; }
    
    void addPairToHashMap(String eWord, String gWord) 
    {
       wordListHashMap.put(eWord, gWord);   
    }
   
 // To iterate through list, we are iterating over the key set rather than
 // the Hash set entrys in order to allow for shuffling the set.  A hash
 // map cannot be shuffled, but an arraylist can be.  
    
    void initiateList()
    {
        wordKeys = new ArrayList<String>(wordKeySet) ;
        iterator = wordKeys.iterator() ;
    }
    
    void shuffleMap()
    {
       Set s = wordListHashMap.keySet() ;
       List reorderedKeys = new ArrayList(s);
       Collections.shuffle(reorderedKeys);
       iterator = reorderedKeys.iterator() ;
       
    }
    
    Map.Entry<String,String> getNextEntry()
    {
        String key ="";
        
        if (iterator.hasNext())
        {
            
         key = (String) iterator.next() ;
         String val = (String) wordListHashMap.get(key) ;
         
          Map.Entry<String,String> entry = 
                new AbstractMap.SimpleEntry<String, String>(key, val);
            return entry;
          }
        else
            return null ;
    }
   
    public String toString()
    {
        String contents = listName+'\n';
        for (String key : wordListHashMap.keySet()) 
        {    
          contents+= key+" "+wordListHashMap.get(key)+'\n';
        }      
        return contents;
    }
    
}
