
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
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    
    private Node headSentinelNode;
    private Node tailSentinelNode;
    
    private int nodeCount;
    
    private class Node
    {
        private Item nodeItem;
        private Node prevNode;
        private Node nextNode;
    }
    
    // construct an empty deque
    public Deque()
    {
        nodeCount = 0;
        
        headSentinelNode = new Node();
        headSentinelNode.nodeItem = null;
        headSentinelNode.prevNode = null;
        
        tailSentinelNode = new Node();
        tailSentinelNode.nodeItem = null;
        tailSentinelNode.prevNode = headSentinelNode;
        tailSentinelNode.nextNode = null;
        
        headSentinelNode.nextNode = tailSentinelNode;
    }            
    
    // is the deque empty?
    public boolean isEmpty()
    {
        return nodeCount == 0;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return nodeCount;
    }
    
    // add the item to the front
    public void addFirst(Item item)
    {
        checkItemIfNull(item);
		
        Node currentFirstNode = headSentinelNode.nextNode;
        Node newFirstNode = new Node();
        newFirstNode.nodeItem = item;
        newFirstNode.nextNode = currentFirstNode;
        newFirstNode.prevNode = headSentinelNode;

        currentFirstNode.prevNode = newFirstNode;
        headSentinelNode.nextNode = newFirstNode;

        nodeCount++;
    }
    
    private void checkItemIfNull(Item item){
        if(item == null)
        {
            throw new NullPointerException();
        }
    }
    
    // add the item to the end
    public void addLast(Item item) 
    {
        checkItemIfNull(item);
		
        Node currentLastNode = tailSentinelNode.prevNode;
        Node newLastNode = new Node();
        newLastNode.nodeItem = item;
        newLastNode.nextNode = tailSentinelNode;
        newLastNode.prevNode = currentLastNode;

        currentLastNode.nextNode = newLastNode;
        tailSentinelNode.prevNode = newLastNode;

        nodeCount++;
		
    }
    
    // remove and return the item from the front
    public Item removeFirst()
    {
        checkIfEmpty();
		
        Node currentFirstNode = headSentinelNode.nextNode;
        Node newFirstNode = currentFirstNode.nextNode;

        headSentinelNode.nextNode = newFirstNode;
        newFirstNode.prevNode = headSentinelNode;

        nodeCount--;
        
        return currentFirstNode.nodeItem;
    }
    
    // remove and return the item from the end
    public Item removeLast()
    {
        checkIfEmpty();
        
        Node currentLastNode = tailSentinelNode.prevNode;
        Node newLastNode = currentLastNode.prevNode;
        
        tailSentinelNode.prevNode = newLastNode;
        newLastNode.nextNode = tailSentinelNode;
        
        nodeCount--;
        
        return currentLastNode.nodeItem;
    }
    
    private void checkIfEmpty()
    {
        if(nodeCount == 0)
        {
            throw new NoSuchElementException();
        }
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>
    {
            private Node currentNode = headSentinelNode;

            @Override
            public boolean hasNext() {
                return currentNode.nextNode.nodeItem != null;
            }

            @Override
            public Item next() {
                if(!hasNext())
                {
                    throw new NoSuchElementException();
                }
                
                currentNode = currentNode.nextNode;
                return currentNode.nodeItem;
            }

    }
    
    // unit testing (optional)
    public static void main(String[] args)
    {
        
    }
}
