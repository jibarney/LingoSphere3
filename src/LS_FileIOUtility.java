
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LS_FileIOUtility {
     
    public static ArrayList readVocabFile(InputStream fis)
    {
        ArrayList<StudyList> allWordLists = new ArrayList<>();
        String listName;
        String EnglishWord;
        String GermanWord;
        
        // File file =new File(wordListFile);
        // FileInputStream fis = null ;
        
         BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
       
          try{ 
            
            String line = reader.readLine() ;
            while (line != null)
             {
            
              int idx1 = line.indexOf(':',0);
              if (idx1 >0 )
              {
                 listName = (line.substring(0,idx1)).trim();
                 int idx2 = line.indexOf(':',idx1+1);
                 EnglishWord = (line.substring(idx1+1,idx2).trim());
                 GermanWord = (line.substring(idx2+1)).trim();
                
                /* Add the English-Germain word pair to the proper list */
                addWordPair(allWordLists,listName, EnglishWord, GermanWord);
              }
              line = reader.readLine();
             }
         
          }
          catch (FileNotFoundException ex) {} 
          catch (Exception ex){
              ex.printStackTrace() ;
          }
          
    
        
        return allWordLists;
    }
    
 /* After reading the list name, English word, and German word from the file, the data is stored in a StudyList, which consists of a
    String filename and a HashMap containing all pairs.
  */    
    static void addWordPair(ArrayList<StudyList> listToUpdate,String whichList, String EngWord, String GerWord)
    {
        boolean listExists = false ;
        /* If this is the first word pair in this list, start a new list.  Otherwise, add the 
           translation to an existing list.
        */
        for (StudyList thisList: listToUpdate)
                 {
                    if (thisList.getListName().equals(whichList))
                    {
                        listExists = true;
                        thisList.addPairToHashMap(EngWord, GerWord) ;
                     }
                 }
                 if (!listExists)
                 {
                     StudyList newList = new StudyList(whichList);
                     listToUpdate.add(newList);
                     newList.addPairToHashMap(EngWord, GerWord) ;
                 } 
    }
    
    
}
