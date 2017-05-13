
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class Permutation {
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        
        String[] stringSequence = StdIn.readAllStrings();
        for(String content : stringSequence)
        {
            randomQueue.enqueue(content);
        }
        
        Iterator<String> iterator = randomQueue.iterator();
        
        int counter = 0;
        while(counter != k && iterator.hasNext())
        {
            StdOut.println(iterator.next());
            counter++;
        }
    }
}
