
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
    
    private class Node<Item>
    {
        private Item item;
        private Node prevNode;
        private Node nextNode;

        public Item getItem() 
        {
            return item;
        }
        
        public void setItem(Item item) 
        {
            this.item = item;
        }
        
        public Node getPrevNode()
        {
            return prevNode;
        }

        public void setPrevNode(Node prev)
        {
            this.prevNode = prev;
        }
        
        public Node getNextNode()
        {
            return nextNode;
        }

        public void setNextNode(Node next)
        {
            this.nextNode = next;
        }
        

    }
    
    // construct an empty deque
    public Deque()
    {
        nodeCount = 0;
        
        headSentinelNode = new Node<>();
        headSentinelNode.setItem(null);
        headSentinelNode.setPrevNode(null);
        
        tailSentinelNode = new Node<>();
        tailSentinelNode.setItem(null);
        tailSentinelNode.setPrevNode(headSentinelNode);
        
        headSentinelNode.setNextNode(tailSentinelNode);
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
		
		Node currentFirstNode = headSentinelNode.getNextNode();
		Node newFirstNode = new Node();
		newFirstNode.setNextNode(currentFirstNode);
		newFirstNode.setPrevNode(headSentinelNode);
		
		if(isEmpty())
		{
			tailSentinelNode.setPrevNode(newFirstNode);
		}
		
		headSentinelNode.setNextNode(newFirstNode);
		
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
		
		
    }
    
    // remove and return the item from the front
    public Item removeFirst()
    {
        checkIfEmpty();
    }
    
    // remove and return the item from the end
    public Item removeLast()
    {
        checkIfEmpty();
    }
    
    private void checkIfEmpty()
    {
        if(size() == 0)
        {
            throw new NoSuchElementException();
        }
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        
    }
    
    // unit testing (optional)
    public static void main(String[] args)
    {
    
    }
}
