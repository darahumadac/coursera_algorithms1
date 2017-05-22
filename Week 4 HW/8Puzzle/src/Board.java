
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class Board {
    
    private int[][] board;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        board = Arrays.copyOf(blocks, blocks.length);
    }
    
    // board dimension n
    public int dimension()    
    {
        return board.length;
    }
    
    // number of blocks out of place
    public int hamming()
    {
        int boardSize = board.length;
        int blockCount = boardSize*boardSize;
        
        int outOfPlaceBlocks = 0;
        
        int blankBlock = 0;
        int expectedBlock;
        
        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {   
                if(board[i][j] != blankBlock)
                {
                    expectedBlock = (((i*boardSize) + j) + 1) % blockCount;
                    if (board[i][j] != expectedBlock)
                    {
                        outOfPlaceBlocks++;
                    }
                }
                
            }
        }
        
        return outOfPlaceBlocks;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        return 0;
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
        return false;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        return null;
    }
    
    // does this board equal y?
    public boolean equals(Object y)
    {
        return false;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        return null;
    }
    
    // string representation of this board (in the output format specified below)
    public String toString()
    {
        return null;
    }

    // unit tests (not graded)
    public static void main(String[] args)
    {
    
    }
}
