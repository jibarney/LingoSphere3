/*This class contains a single list of vocabulary words along with all 
  of it's identifying information (List name, List owner)
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
    private String listName ;   // Identifies the list in the dropdown box
    
    private String listCreatorID;  // Identifies Instructor who owns the list
    
    // Contains all <Key,Value> pairs -->  key = English word, value = German word
    private Map<String, String> wordListHashMap = new HashMap<String,String>() ; 
   
    /* Keys (English words) are stored in a List so they can be shuffled.  In hindsight,
       it probably would have been best to store the key-value pair in a different 
       container, since HashMaps cannot be shuffled.
    */
    
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
