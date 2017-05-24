
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
    private int blockSize;
    private int[][] blocksInput;
    private final int blankBlock = 0;
    private int[] goalBoard;

    private int outOfPlaceBlocks;
    private int manhattanDistances;

    private final int notComputed = -1;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        blockSize = blocks.length;
        blocksInput = Arrays.copyOf(blocks, blockSize);
        boardSize = blockSize * blockSize;

        outOfPlaceBlocks = notComputed;
        manhattanDistances = notComputed;

        initializeBoard();
        createGoalBoard();
    }
	
    private void initializeBoard()
    {
        board = new int[boardSize];
        goalBoard = new int[boardSize];

        int boardIndex;
        for (int i = 0; i < blockSize; i++)
        {
            for (int j = 0; j < blockSize; j++)
            {
                boardIndex = ((i*blockSize) + j);
                board[boardIndex] = blocksInput[i][j];
            }

        }
    }
	
	private void createGoalBoard()
	{
		for (int i = 0; i < boardSize; i++)
		{
			goalBoard[i] = (i+1)%boardSize;
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
        if (outOfPlaceBlocks == notComputed)
        {
            outOfPlaceBlocks = 0;

            int block;
            for (int i = 0; i < boardSize; i++)
            {
                block = board[i];
                if (block != blankBlock && block != i+1)
                {
                    outOfPlaceBlocks++;
                }
            }
        }
        
        return outOfPlaceBlocks;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        if (manhattanDistances == notComputed)
        {
            manhattanDistances = 0;

            int currentRow;
            int currentCol;
            int expectedRow;
            int expectedCol;

            int block;

            for (int i = 0; i < boardSize; i++)
            {
                block = board[i];
                if (block != blankBlock)
                {
                    currentRow = i / blockSize;
                    currentCol = i % blockSize;

                    expectedRow = (block-1) / blockSize;
                    expectedCol = (block-1) % blockSize;

                    manhattanDistances += (Math.abs(expectedRow-currentRow) + 
                    Math.abs(expectedCol-currentCol));
                }
            }
        }
		
        return manhattanDistances;
        
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
        return hamming() == 0;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        //form 2d array of blocks with non blank blocks switched
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
