/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.util.*;
import java.io.IOException;

/**
 *
 * @author Pafel
 */
public class BaseTest {
    public static void main(String[] args) throws IOException {
        Base base = new Base("test.txt");

        System.out.println("Zawartość bazy:");
        
        Iterator<NGram> it = base.gram.iterator();
        NGram g;
        
        while (it.hasNext()) {
                StringBuilder line = new StringBuilder(100);
                g = it.next();
                line.append(g.nextIndex.size()).append(" ").append(g.gramText[0]).append(" ").append(g.gramText[1]);
                for (Map.Entry<Integer, Integer> entry : g.nextIndex.entrySet() ) {
                    line.append(" ").append(entry.getKey()).append(".").append(entry.getValue());
                }
            System.out.println(line);
        }
        
        System.out.println("Dodanie nowego n-gramu 'Oczy węża' będącego surfixem pierwszego n-gramu");
        base.addGram(0, "Oczy", "węża");
        
        it = base.gram.iterator();
        while (it.hasNext()) {
                StringBuilder line = new StringBuilder(100);
                g = it.next();
                line.append(g.nextIndex.size()).append(" ").append(g.gramText[0]).append(" ").append(g.gramText[1]);
                for (Map.Entry<Integer, Integer> entry : g.nextIndex.entrySet() ) {
                    line.append(" ").append(entry.getKey()).append(".").append(entry.getValue());
                }
            System.out.println(line);
        }
        
        System.out.println("Losowanie 5 surfixów dla peirwszego n-gramu");
        
        for (int i = 0 ; i < 5 ; i++)
            System.out.println(base.randomIndex(0));
        
        if (base.isEnd("Kupa.") == 0)
            System.out.println("Zapis nowej bazy do pliku 'testout.txt'");
        
        base.saveToTheFile("testout.txt");
    }
}