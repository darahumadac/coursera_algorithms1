
import org.junit.Assert;
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
public class BoardTests {
    
    @Test
    public void Hamming_Test_Blank_InMiddle()
    {
        int[][] blocks = new int[][]
        {
            {8,1,3},
            {4,0,2},
            {7,6,5}
        };
        
        Board board = new Board(blocks);
        Assert.assertEquals(5, board.hamming());
    }
    
    @Test
    public void Hamming_Test_InFirstBlock()
    {
        int[][] blocks = new int[][]
        {
            {0,1,3},
            {4,2,5},
            {7,8,6}
        };
        
        Board board = new Board(blocks);
        Assert.assertEquals(4, board.hamming());
    }
    
}
