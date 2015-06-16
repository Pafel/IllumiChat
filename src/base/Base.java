/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.*;
import java.util.*;



public class Base {
    List<NGram> gram = new LinkedList<>();
    private int index = 0;
    
    public Base(String basefile) throws IOException
    {
        try (BufferedReader in = new BufferedReader(
                new FileReader(basefile))) {
            String s;
            int n;
            
            while((s = in.readLine()) != null)
            {
                String parts[] = s.split(" ");
                n = Integer.parseInt(parts[0]);
                
                
                this.gram.add(new NGram(parts[1], parts[2]));
                
                int i = 0;
                NGram tmp = this.gram.get(index);
                while (i < n*2)
                {
                    tmp.addGramInt(Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4]));
                    i += 2;
                }
                
                index++;
            }
        }
    }
    
    public int findGram(String word1, String word2 )
    {
        Iterator<NGram> it = this.gram.iterator();
            int i = 0;
            NGram f;
            while (it.hasNext()) {
                f = it.next();
                if (f.gramText[0].equals(word1)) {
                    if (f.gramText[1].equals(word2))
                        return i;
                }
                i++;
            }
        return index;
    }
    
    public int addGram(int indexprev, String word1, String word2)
    {
        int i = this.findGram(word1, word2);
        if (indexprev == -1) {
            this.gram.add(new NGram(word1, word2));
            this.index++;
        }
        else if (i != index) {
            this.gram.get(indexprev).addGramInt(i, 1);
        }
        else {
            this.gram.add(new NGram(word1, word2));
            this.gram.get(indexprev).addGramInt(index, 1);
            this.index++;
        }        
        return i;
    }
    
    
    public void saveToTheFile(String filename) throws FileNotFoundException {
        try (PrintWriter save = new PrintWriter(filename)) {
            NGram g;
            Iterator<NGram> it = this.gram.iterator();
            while (it.hasNext()) {
                StringBuilder line = new StringBuilder(100);
                g = it.next();
                line.append(g.nextIndex.size()).append(" ").append(g.gramText[0]).append(" ").append(g.gramText[1]);
                for (Map.Entry<Integer, Integer> entry : g.nextIndex.entrySet() ) {
                    line.append(" ").append(entry.getKey()).append(" ").append(entry.getValue());
                }
                save.println(line);
            }
        }
    }
    
    public int randomIndex(int k)
    {
        NGram g = this.gram.get(k);
        int n = 0;
        
        for (Map.Entry<Integer, Integer> entry : g.nextIndex.entrySet() )
            n += entry.getValue();
        
        if (n == 0)
            return -1;
        
        Random generator = new Random();
        int i = generator.nextInt(n);
        
        for (Map.Entry<Integer, Integer> entry : g.nextIndex.entrySet() ) {
            i -= entry.getValue();
            if (i < 0)
                return entry.getKey();
        }       
        return -1;
    }
    
    public String firstGramWord(int i) {
        return this.gram.get(i).gramText[0];
    }
    
    public String lastGramWord(int i) {
        return this.gram.get(i).gramText[1];
    }
    
}