
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
    
    private int[] board;
    private int boardSize;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
		initializeBoard(blocks);
    }
	
	private void initializeBoard(int[][] blocks)
	{
		blockSize = blocks.length;
		boardSize = blockSize * blockSize;
		
        board = new int[boardSize];
		int boardIndex;
		for (int i = 0; i < blockSize; i++)
		{
			for (int j = 0; j < blockSize; j++)
			{
				boardIndex = ((i*blockSize) + j);
				board[boardIndex] = board[i][j];
			}
			
		}
	}
    
    // board dimension n
    public int dimension()    
    {
        return blockSize;
    }
    
    // number of blocks out of place
    public int hamming()
    {
        int outOfPlaceBlocks = 0;
        
        int blankBlock = 0;
        int block;
        for (int i = 0; i < boardSize; i++)
		{
			block = board[i][j];
			if (block != blankBlock && block != i)
			{
				outOfPlaceBlocks++;
			}
		}
        
        return outOfPlaceBlocks;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        int manhattanDistances = 0;
        int blankBlock = 0;
        
        int expectedRow;
        int expectedCol;
        int block;
        for (int i = 0; i < blockSize; i++)
        {
            for (int j = 0; j < blockSize; j++)
            {
                block = board[i][j];
                if(block != blankBlock)
                {
                    expectedRow = (block-1) / blockSize;
                    expectedCol = (block-1) % blockSize;

                    manhattanDistances += (Math.abs(expectedRow - i)+(Math.abs(expectedCol - j)));
                }
                
            }
        }
        
        return manhattanDistances;
        
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
