/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stat;
import java.util.*;

/**
 *
 * @author Pafel
 */
public class Stat {
    Map<String, Integer> gramlist = new HashMap<String, Integer>();
    
    public void addToStat(String s) {
        Integer is = this.gramlist.get(s);
        this.gramlist.put(s, is == null ? 1 : is+1);
        }
    
    public StringBuilder statText() {
        Map<String, Integer> map = sortMap();
        StringBuilder s = new StringBuilder(200);
        int n = 0;
        
         for (Map.Entry<String, Integer> entry : map.entrySet()) {
             if (n < 10)
                s.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
             n++;
         }
    
         s.append("Użyto ").append(n).append(" n-gramów");
         
         return s;
    }

    private HashMap sortMap () {
        List list = new LinkedList(this.gramlist.entrySet());
        StringBuilder s = new StringBuilder(300);
        
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return -((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
            
        HashMap sortedHashMap = new LinkedHashMap<>();
        for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
        
        return sortedHashMap;
    }
}
