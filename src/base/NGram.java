/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;
import java.util.*;

/**
 *
 * @author Pafel
 */
class NGram {
    String[] gramText;
    Map<Integer, Integer> nextIndex;
    
    NGram(String a, String b)
    {
        this.gramText = new String[2];
        this.nextIndex = new HashMap<>();
        
        this.gramText[0] = a;
        this.gramText[1] = b;
        
    }
    
    void addGramInt(int a , int b)
    {
        Integer is = this.nextIndex.get(a);
        this.nextIndex.put(a, is == null ? b : is+b);
    }
    

}
