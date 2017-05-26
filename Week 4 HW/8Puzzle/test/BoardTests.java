
import java.util.Arrays;
import java.util.Date;
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
    
    @Test
    public void Hamming_Test_InLastBlock()
    {
        int[][] blocks = new int[][]
        {
            {6,1,3},
            {4,2,5},
            {7,8,0}
        };
        
        Board board = new Board(blocks);
        Assert.assertEquals(4, board.hamming());
    }
    
    @Test
    public void Manhattan_Test_InMiddle()
    {
        int[][] blocks = new int[][]
        {
            {8,1,3},
            {4,0,2},
            {7,6,5}
        };
        
        Board board = new Board(blocks);
        Assert.assertEquals(10, board.manhattan());
    }
    
    @Test
    public void Not_GoalBoard()
    {
        int[][] blocks = new int[][]
        {
            {8,1,3},
            {4,0,2},
            {7,6,5}
        };
        
        Board board = new Board(blocks);
        Assert.assertFalse(board.isGoal());
    }
    
    @Test
    public void Is_GoalBoard()
    {
        int[][] blocks = new int[][]
        {
            {1,2,3},
            {4,5,6},
            {7,8,0}
        };
        
        Board board = new Board(blocks);
        Assert.assertTrue(board.isGoal());
    }
    
    @Test
    public void Twin()
    {
        int[][] blocks = new int[][]
        {
            {2,1,3},
            {4,5,6},
            {7,8,0}
        };
        
        Board board = new Board(blocks);
        Assert.assertNotNull(board.twin());
    }
    
    @Test
    public void Board_ToString()
    {
        int[][] blocks = new int[][]
        {
            {2,1,3},
            {4,5,6},
            {7,8,0}
        };
        
        Board board = new Board(blocks);
        System.out.println(board.toString());
        Assert.assertEquals("3\n 2  1  3 \n 4  5  6 \n 7  8  0 \n", board.toString());
    }
    
    @Test
    public void Board_Equals_True()
    {
        int[][] blocks1 = new int[][]
        {
            {2,1,3},
            {4,5,6},
            {7,8,0}
        };
        Board board1 = new Board(blocks1);
        
        int[][] blocks2 = Arrays.copyOf(blocks1, blocks1.length);
        Board board2 = new Board(blocks2);
        
        Assert.assertTrue(board1.equals(board2));
    }
    
    @Test
    public void Board_Not_Equals_SameClass()
    {
        int[][] blocks1 = new int[][]
        {
            {2,1,3},
            {4,5,6},
            {7,8,0}
        };
        Board board1 = new Board(blocks1);
        
        int[][] blocks2 = new int[][]
        {
            {5,1,3},
            {4,2,6},
            {7,8,0}
        };
        Board board2 = new Board(blocks2);
        
        Assert.assertFalse(board1.equals(board2));
    }
    
    @Test
    public void Board_Not_Equals_DifferentClass()
    {
        int[][] blocks1 = new int[][]
        {
            {2,1,3},
            {4,5,6},
            {7,8,0}
        };
        Board board1 = new Board(blocks1);
        
        Date date = new Date();
        
        Assert.assertFalse(board1.equals(date));
    }
}
