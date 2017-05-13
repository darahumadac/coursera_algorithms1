
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] randomQueue;
    private int size;
    private int initialSize = 2;
    
   // construct an empty randomized queue
    public RandomizedQueue()
    {
        size = 0;
        randomQueue = (Item[])new Object[initialSize];
    }
    
    // is the queue empty?
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // return the number of items on the queue
    public int size()
    {
        return size;
    }
    
    // add the item
    public void enqueue(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        
        randomQueue[size] = item;
        size++;
        
        if(size == randomQueue.length)
        {
            resize(2 * size);
        }
    }
    
    private void resize(int newSize)
    {
        Item[] tempQueue = (Item[])new Object[newSize];
        
        for(int i = 0; i < size; i++)
        {
            tempQueue[i] = randomQueue[i];
        }
        
        randomQueue = tempQueue;
    }
    
    // remove and return a random item
    public Item dequeue()
    {
        checkIfEmpty();
        int index = StdRandom.uniform(size);
        Item randomItem = randomQueue[index];
        int lastIndex = size-1;
        
        if(index < lastIndex)
        {
            randomQueue[index] = randomQueue[lastIndex];
        }
        
        randomQueue[lastIndex] = null;
        size--;
        
        if(size < randomQueue.length/4)
        {
            resize(randomQueue.length/2);
        }
        
        return randomItem;
    }
    
    private void checkIfEmpty()
    {
        if(size == 0)
        {
            throw new NoSuchElementException();
        }
    }
    
    // return (but do not remove) a random item
    public Item sample()
    {
        checkIfEmpty();
        int index = StdRandom.uniform(size);
        return randomQueue[index];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomQueueIterator();
    }
    
    private class RandomQueueIterator implements Iterator<Item>
    {
        private int[] randomIndices;
        private int currentIndex = 0;
        
        private RandomQueueIterator()
        {
            randomIndices = new int[size];
            for(int i = 0; i < size; i++)
            {
                randomIndices[i] = i;
            }
            
            StdRandom.shuffle(randomIndices);
        }
        
        
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Item next() {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            
            int itemIndex = randomIndices[currentIndex];
            currentIndex++;
            
            return randomQueue[itemIndex];
            
        }
        
    }
    
    // unit testing (optional)
    public static void main(String[] args)
    {}
}
