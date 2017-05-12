
import java.util.Iterator;
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
    public void Add_First_Item(){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        Iterator<Integer> iterator = deque.iterator();
        
        assertTrue(iterator.hasNext());
        //assertEquals((Integer)1, (Integer)iterator.next());
        
    }
    
}
