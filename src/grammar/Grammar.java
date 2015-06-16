/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;
import java.util.*;
import java.io.*;

/**
 *
 * @author Pafel
 */
public class Grammar {
    
    Set<String> exceptions = new HashSet<>();
    
    /**
     *
     * @param gfile
     * @throws java.io.IOException
     */
    public Grammar(String gfile)  throws IOException {
        
        try (BufferedReader in = new BufferedReader(
                new FileReader(gfile))) {
            String s;
            while ((s = in.readLine()) != null)
                this.exceptions.add(s);
        }  
        }
    
    /**
     *
     * @param word
     * @return
     */
    public Boolean checkSpelling(String word) {
        
        String lower= word.toLowerCase();
        char[] chars = lower.toCharArray();
        
        int n = chars.length;
        int j = 0;
        
        for (char i : chars) {
            if (i == 'u') {
                if (j == 0) {
                    Iterator<String> it = exceptions.iterator();
                    String s;
                    
                    while(it.hasNext()) {
                        if (it.next() == lower)
                            return true;
                    }
                        
                    return false;
                }
                
                if (j+2 == n && chars[j+1] == 'w' ||
                    j+4 == n && chars[j+1] == 'w' && chars[j+2] == 'k' && chars[j+3] == 'a') {
                    
                    Iterator<String> it = exceptions.iterator();
                    String s;
                    
                    while(it.hasNext()) {
                        if (it.next() == null ? lower == null : it.next().equals(lower))
                            return true;
                    }
                        
                    return false;
                }
            }
        }
        return true;
    }
}