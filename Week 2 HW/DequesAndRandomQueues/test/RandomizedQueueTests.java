
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class RandomizedQueueTests {
    
    RandomizedQueue<Integer> queue;
    
    @Before
    public void CreateEmptyQueue()
    {
        queue = new RandomizedQueue<>();
    }
    
    @Test
    public void Empty_Random_Queue()
    {
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }
    
    @Test
    public void Enqueue_1_Item()
    {
        queue.enqueue(1);
        
        Assert.assertEquals(1, queue.size());
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals((Integer)1, queue.sample());
        
        Iterator<Integer> iterator = queue.iterator();
        Assert.assertEquals("1", print(iterator));
    }
    
    private String print(Iterator<Integer> iterator)
    {
        String queueContents = "";
        while(iterator.hasNext())
        {
            queueContents += iterator.next().toString();
        }
        return queueContents;
    }
    
    @Test
    public void Dequeue_1_Item()
    {
        queue.enqueue(1);
        queue.dequeue();
        
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }
    
    @Test
    public void Enqueue_Multiple_Items()
    {
        for(int i=1; i<=5; i++)
        {
            queue.enqueue(i);
        }
        
        Assert.assertEquals(5, queue.size());
        Assert.assertTrue(!queue.isEmpty());
        
        Iterator<Integer> iterator = queue.iterator();
        String contents = print(iterator);
        Assert.assertNotEquals("", contents);
        
        System.out.println(contents);
    }
    
    @Test
    public void Dequeue_Multiple_Items()
    {
        for(int i=1; i<=5; i++)
        {
            queue.enqueue(i);
        }
        
        for(int j=1; j<=2; j++)
        {
            queue.dequeue();
        }
        
        Assert.assertEquals(3, queue.size());
        Assert.assertTrue(!queue.isEmpty());
        
        Iterator<Integer> iterator = queue.iterator();
        String contents = print(iterator);
        Assert.assertNotEquals("", contents);
        
        System.out.println(contents);
    }
    
    @Test
    public void Enqueue_Then_Empty_Queue_By_Dequeue()
    {
        for(int i=1; i<=5; i++)
        {
            queue.enqueue(i);
        }
        
        for(int i=1;i<=5;i++)
        {
            queue.dequeue();
        }
        
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());        
    }
    
    @Test
    public void Sample_NonEmptyQueue()
    {
        for(int i=1; i<=52; i++)
        {
            queue.enqueue(i);
        }
        
        int sample1 = queue.sample();
        int sample2 = queue.sample();
        
        Assert.assertNotEquals(sample1, sample2);
    }
    
    @Test
    public void Iterators_Mutually_Exclusive()
    {
        for(int i=1; i<=9; i++)
        {
            queue.enqueue(i);
        }
        
        Iterator<Integer> iterator1 = queue.iterator();
        Iterator<Integer> iterator2 = queue.iterator();
        
        String it1Contents = print(iterator1);
        String it2Contents = print(iterator2);
        
        Assert.assertNotEquals(it1Contents, it2Contents);
        System.out.println("Iterator 1: "+it1Contents);
        System.out.println("Iterator 2: "+it2Contents);
    }
    
    //Corner Cases
    @Test(expected = NoSuchElementException.class)
    public void Call_Sample_On_Empty_Random_Queue()
    {
        queue.sample();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Call_Dequeue_On_Empty_Random_Queue()
    {
        queue.dequeue();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void Call_Remove_On_Iterator()
    {
        Iterator<Integer> iterator = queue.iterator();
        iterator.remove();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Call_Next_On_Iterator_EmptyQueue()
    {
        Iterator<Integer> iterator = queue.iterator();
        iterator.next();
    }
    
}
