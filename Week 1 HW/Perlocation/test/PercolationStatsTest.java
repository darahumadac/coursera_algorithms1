
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
public class PercolationStatsTest {
    
    @Test
    public void test_1(){
        PercolationStats.main(new String[]{"200", "100"});
    }
    
    @Test
    public void test_2(){
        PercolationStats.main(new String[]{"200", "100"});
    }
    
    @Test
    public void test_3(){
        PercolationStats.main(new String[]{"2", "1000"});
    }
    
    @Test
    public void test_4(){
        PercolationStats.main(new String[]{"2", "1000"});
    }
    
    //corner cases
    @Test(expected = IllegalArgumentException.class)
    public void grid_lessthan_1(){
        PercolationStats.main(new String[]{"0", "1000"});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void trial_lessthan_1(){
        PercolationStats.main(new String[]{"2", "-1"});
    }
    
}
