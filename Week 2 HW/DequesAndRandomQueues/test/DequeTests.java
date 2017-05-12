
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class DequeTests {
    
    @Test
    public void Empty_Deque(){
        Deque<Integer> deque = new Deque<>();
        Iterator<Integer> iterator = deque.iterator();
        
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
        assertTrue(!iterator.hasNext());
    }
            
    @Test
    public void Add_First_Item(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        Iterator<Integer> iterator = deque.iterator();
        
        assertTrue(iterator.hasNext());
        assertEquals((Integer)1, (Integer)iterator.next());
        assertEquals(1, deque.size());
        assertTrue(!deque.isEmpty());
    }
    
    @Test
    public void Add_Last_Item()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        
        assertTrue(iterator.hasNext());
        assertEquals((Integer)1, (Integer)iterator.next());
        assertEquals(1, deque.size());
        assertTrue(!deque.isEmpty());
    }
    
    @Test
    public void Add_First_Multiple_Items(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        Iterator<Integer> iterator = deque.iterator();
        
        assertEquals(3, deque.size());
        assertTrue(!deque.isEmpty());
        
        assertEquals("321", print(iterator));
        
    }
    
    public String print(Iterator<Integer> iterator)
    {
        String dequeContents = "";
        while(iterator.hasNext())
        {
            dequeContents += iterator.next().toString();
        }
        
        return dequeContents;
    }
    
    @Test
    public void Add_Last_Multiple_Items()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> iterator = deque.iterator();
        
        assertEquals(3, deque.size());
        assertTrue(!deque.isEmpty());
        
        assertEquals("123", print(iterator));
    }
    
    @Test
    public void Add_First_Last_MultipleItems()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        Iterator<Integer> iterator = deque.iterator();
        
        assertEquals(4, deque.size());
        assertTrue(!deque.isEmpty());
        
        assertEquals("4123", print(iterator));
        
    }
    
    @Test
    public void Add_Last_First_MultipleItems()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        Iterator<Integer> iterator = deque.iterator();
        
        assertEquals(3, deque.size());
        assertTrue(!deque.isEmpty());
        
        assertEquals("213", print(iterator));
    }
    
    @Test
    public void Remove_First_Item()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        Iterator<Integer> iterator = deque.iterator();
        
        Integer first = deque.removeFirst();
        assertEquals((Integer)1, (Integer)first);
        assertEquals(2, deque.size());
        assertEquals("23", print(iterator));
        assertTrue(!deque.isEmpty());   
    }
    
    @Test
    public void Remove_Last_Item()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        Iterator<Integer> iterator = deque.iterator();
        
        Integer first = deque.removeLast();
        assertEquals((Integer)3, (Integer)first);
        assertEquals(2, deque.size());
        assertEquals("12", print(iterator));
        assertTrue(!deque.isEmpty());   
    }
    
    @Test
    public void RemoveFirst_All_Items()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        Iterator<Integer> iterator = deque.iterator();
        
        for(int i = 0; i<3; i++)
        {
            deque.removeFirst();
        }
        
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
        assertEquals("", print(iterator));
    }
    
    @Test
    public void RemoveLast_All_Items()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        Iterator<Integer> iterator = deque.iterator();
        
        for(int i = 0; i<3; i++)
        {
            deque.removeLast();
        }
        
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
        assertEquals("", print(iterator));
    }
    
    //Corner Cases
    @Test(expected = NoSuchElementException.class)
    public void RemoveFirst_On_Empty_Deque()
    {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void RemoveLast_On_Empty_Deque()
    {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }
    
    @Test(expected = NullPointerException.class)
    public void Add_Null_First_Item()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void Add_Null_Last_Item()
    {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(null);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void Call_Remove_In_Iterator()
    {
        Deque<Integer> deque = new Deque<>();
        deque.iterator().remove();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Call_Next_On_Empty_Deque()
    {
        Deque<Integer> deque = new Deque<>();
        deque.iterator().next();
    }
    
    
    
}
