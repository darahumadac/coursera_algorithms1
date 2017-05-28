
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    public void Is_GoalBoard2()
    {
        int[][] blocks = new int[][]
        {
            {1,  2,  0}, 
            {4,  8,  3},
            {7,  6,  2},
        };
        
        Board board = new Board(blocks);
        Assert.assertFalse(board.isGoal());
        
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
    
    @Test
    public void GetNeighbors_BlankBlock_FirstBlock()
    {
        int[][] blocks = new int[][]
        {
            {0,2,3},
            {4,5,6},
            {7,8,1}
        };
        
        int[][] neighbor1Block = new int[][]
        {
            {2,0,3},
            {4,5,6},
            {7,8,1}
        };
        Board neighbor1 = new Board(neighbor1Block);
        
        
        int[][] neighbor2Block = new int[][]
        {
            {4,2,3},
            {0,5,6},
            {7,8,1}
        };
        Board neighbor2 = new Board(neighbor2Block);
        
        Board board = new Board(blocks);
        Iterable<Board> boardNeighbors = board.neighbors();
        
        //printBoardAndNeighbors(board, boardNeighbors);
        
        ArrayList<Board> list = new ArrayList<>((Collection) boardNeighbors);
        
        Assert.assertEquals(2, list.size());
        Assert.assertEquals((Board)neighbor1, (Board)list.get(0));
        Assert.assertEquals((Board)neighbor2, (Board)list.get(1));
        
    }
    
    private void printBoardAndNeighbors(Board board, 
                                        Iterable<Board> boardNeighbors)
    {
        System.out.println("Board:");
        System.out.println(board.toString());
        
        System.out.println("Neighbors:");
        for(Board neighbor : boardNeighbors)
        {
            System.out.println(neighbor.toString());
        }
    }
    
    @Test
    public void GetNeighbors_BlankBlock_LastBlock()
    {
        int[][] blocks = new int[][]
        {
            {1,2,3},
            {4,6,5},
            {7,8,0}
        };
        
        int[][] neighbor1Block = new int[][]
        {
            {1,2,3},
            {4,6,0},
            {7,8,5}
        };
        Board neighbor1 = new Board(neighbor1Block);
        
        
        int[][] neighbor2Block = new int[][]
        {
            {1,2,3},
            {4,6,5},
            {7,0,8}
        };
        Board neighbor2 = new Board(neighbor2Block);
        
        ArrayList<Board> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(neighbor1);
        expectedNeighbors.add(neighbor2);
        
        Board board = new Board(blocks);
        Iterable<Board> boardNeighbors = board.neighbors();
        
        //printBoardAndNeighbors(board, boardNeighbors);
        
        ArrayList<Board> neighborList = 
                new ArrayList<>((Collection) boardNeighbors);
        
        Assert.assertEquals(expectedNeighbors.size(), neighborList.size());
        
        for (int i = 0; i < neighborList.size(); i++)
        {
            Assert.assertTrue(neighborList.contains(expectedNeighbors.get(i)));
        }
        
    }
    
    @Test
    public void GetNeighbors_BlankBlock_MiddleBlock()
    {
        int[][] blocks = new int[][]
        {
            {1,2,3},
            {4,0,5},
            {7,8,6}
        };
        
        int[][] neighbor1Block = new int[][]
        {
            {1,0,3},
            {4,2,5},
            {7,8,6}
        };
        Board neighbor1 = new Board(neighbor1Block);
        
        
        int[][] neighbor2Block = new int[][]
        {
            {1,2,3},
            {4,8,5},
            {7,0,6}
        };
        Board neighbor2 = new Board(neighbor2Block);
        
        int[][] neighbor3Block = new int[][]
        {
            {1,2,3},
            {0,4,5},
            {7,8,6}
        };
        Board neighbor3 = new Board(neighbor3Block);
        
        int[][] neighbor4Block = new int[][]
        {
            {1,2,3},
            {4,5,0},
            {7,8,6}
        };
        Board neighbor4 = new Board(neighbor4Block);
        
        ArrayList<Board> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(neighbor1);
        expectedNeighbors.add(neighbor2);
        expectedNeighbors.add(neighbor3);
        expectedNeighbors.add(neighbor4);
        
        Board board = new Board(blocks);
        Iterable<Board> boardNeighbors = board.neighbors();
        
        printBoardAndNeighbors(board, boardNeighbors);
        
        ArrayList<Board> neighborList = 
                new ArrayList<>((Collection) boardNeighbors);
        
        Assert.assertEquals(expectedNeighbors.size(), neighborList.size());
        
        for (int i = 0; i < neighborList.size(); i++)
        {
            Assert.assertTrue(neighborList.contains(expectedNeighbors.get(i)));
        }
        
    }
    
    @Test
    public void GetNeighbors_BlankBlock_MidLeftBlock()
    {
        int[][] blocks = new int[][]
        {
            {1,2,3},
            {0,4,5},
            {7,8,6}
        };
        
        int[][] neighbor1Block = new int[][]
        {
            {1,2,3},
            {4,0,5},
            {7,8,6}
        };
        Board neighbor1 = new Board(neighbor1Block);
        
        
        int[][] neighbor2Block = new int[][]
        {
            {0,2,3},
            {1,4,5},
            {7,8,6}
        };
        Board neighbor2 = new Board(neighbor2Block);
        
        int[][] neighbor3Block = new int[][]
        {
            {1,2,3},
            {7,4,5},
            {0,8,6}
        };
        Board neighbor3 = new Board(neighbor3Block);
        
        
        ArrayList<Board> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(neighbor1);
        expectedNeighbors.add(neighbor2);
        expectedNeighbors.add(neighbor3);
        
        
        Board board = new Board(blocks);
        Iterable<Board> boardNeighbors = board.neighbors();
        
        //printBoardAndNeighbors(board, boardNeighbors);
        
        ArrayList<Board> neighborList = 
                new ArrayList<>((Collection) boardNeighbors);
        
        Assert.assertEquals(expectedNeighbors.size(), neighborList.size());
        
        for (int i = 0; i < neighborList.size(); i++)
        {
            Assert.assertTrue(neighborList.contains(expectedNeighbors.get(i)));
        }
        
    }
    
    
}
