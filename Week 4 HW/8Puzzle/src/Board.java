
import java.util.ArrayList;
import java.util.Arrays;

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* *
 *
 * @author Darah
 */
public class Board {
    
    private int[] board;
    private int blankBlockIndex;
    private String boardString = "";
    private final int boardSize;
    private final int blockSize;
    private final int[][] blocksInput;
    private final int blankBlock = 0;
    private int[] goalBoard;

    private int outOfPlaceBlocks;
    private int manhattanDistances;

    private final int notInitialized = -1;
    //  construct a board from an n-by-n array of blocks
    //  (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        blockSize = blocks.length;
        blocksInput = Arrays.copyOf(blocks, blockSize);
        boardSize = blockSize * blockSize;

        outOfPlaceBlocks = notInitialized;
        manhattanDistances = notInitialized;

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
        
        setBlankBlockIndex();
        setStringRepresentation();
    }
    
    private void setBlankBlockIndex()
    {
        for (int i = 0; i < boardSize; i++)
        {
            if (board[i] == blankBlock)
            {
                blankBlockIndex = i;
                break;
            }
        }
    }
    
    private void setStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append(blockSize).append("\n");
        for (int i = 0; i < blockSize; i++)
        {
            for (int j = 0; j < blockSize; j++)
            {
                sb.append(String.format("%2d ", blocksInput[i][j]));
            }
            sb.append("\n");
        }
        
        boardString = sb.toString();
    }
	
    private void createGoalBoard()
    {
        for (int i = 0; i < boardSize; i++)
        {
            goalBoard[i] = (i+1) % boardSize;
        }
    }
    
    //  board dimension n
    public int dimension()    
    {
        return blockSize;
    }
    
    //  number of blocks out of place
    public int hamming()
    {
        if (outOfPlaceBlocks == notInitialized)
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
    
    //  sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        if (manhattanDistances == notInitialized)
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
    
    //  is this board the goal board?
    public boolean isGoal()
    {
        return hamming() == 0;
    }
    
    //  a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        int[] boardCopy = Arrays.copyOf(board, boardSize);
        // form 2d array of blocks with non blank blocks switched
        int firstBlockIndex = -1;
        int secondBlockIndex = -1;
        
        for (int i = 0; i < boardSize; i++)
        {
            if (boardCopy[i] != blankBlock)
            {
                if (firstBlockIndex == -1)
                {
                    firstBlockIndex = i;
                }
                
                if (secondBlockIndex == -1 && firstBlockIndex != i)
                {
                    secondBlockIndex = i;
                }
            }
        }
        
        // switch
        int tempBlock = boardCopy[firstBlockIndex];
        boardCopy[firstBlockIndex] = boardCopy[secondBlockIndex];
        boardCopy[secondBlockIndex] = tempBlock;
        
        int[][] newBlocks = make2DArray(boardCopy);
        
        return new Board(newBlocks);
        
    }
    
    // Assumption is that the array passed in has same dimensions as original array
    private int[][] make2DArray(int[] oneDimArray)
    {
        int[][] twoDimensionArray = new int[blockSize][blockSize];
        int row;
        int col;
        for (int i = 0; i < boardSize; i++)
        {
            row = i / blockSize;
            col = i % blockSize;
            
            twoDimensionArray[row][col] = oneDimArray[i];
        }
        
        return twoDimensionArray;
    }
    
    //  does this board equal y?
    public boolean equals(Object y)
    {
        if (y != null && y.getClass() == this.getClass())
        {
            return ((Board) y).toString().equals(boardString);
        }
        return false;
    }
    
    //  all neighboring boards
    public Iterable<Board> neighbors()
    {
        int blankBlockRow = blankBlockIndex / blockSize;
        int blankBlockCol = blankBlockIndex % blockSize;
        
        ArrayList<Board> neighbors = new ArrayList<>();
        
        int leftNeighbor = blankBlockCol - 1;
        if (leftNeighbor > -1)
        {
            int[][] leftShiftBoard = 
            swap(blocksInput, blankBlockRow, blankBlockCol, 
                                                blankBlockRow, leftNeighbor);
            
            neighbors.add(new Board(leftShiftBoard));
        }
        
        int rightNeighbor = blankBlockCol + 1;
        if (rightNeighbor < blockSize)
        {
            int[][] rightShiftBoard = 
            swap(blocksInput, blankBlockRow, blankBlockCol,
                                                 blankBlockRow, rightNeighbor);
            
            neighbors.add(new Board(rightShiftBoard));
        }
        
        int bottomNeighbor = blankBlockRow + 1;
        if (bottomNeighbor < blockSize)
        {
            int[][] bottomShiftBoard = 
            swap(blocksInput, blankBlockRow, blankBlockCol,
                                                  bottomNeighbor, blankBlockCol);
            
            neighbors.add(new Board(bottomShiftBoard));
        }
        
        int topNeighbor = blankBlockRow - 1;
        if (topNeighbor > -1)
        {
            int[][] topShiftBoard = 
            swap(blocksInput, blankBlockRow, blankBlockCol,
                                               topNeighbor, blankBlockCol);
            
            neighbors.add(new Board(topShiftBoard));
        }
        
        return neighbors;
    }
    
    private int[][] swap(int[][] array, int originRow, int originCol, 
                                                    int destinationRow,
                                                    int destinationCol)
    {
        int[][] arrayCopy = deepCopy(array);
        
        int temp = arrayCopy[originRow][originCol];
        arrayCopy[originRow][originCol] = 
                arrayCopy[destinationRow][destinationCol];
        arrayCopy[destinationRow][destinationCol] = temp;
        
        return arrayCopy;
    }
    
    private int[][] deepCopy(int[][] originalArray)
    {
        int size = originalArray.length;
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                copy[i][j] = originalArray[i][j];
            }
        }
        return copy;
    }
    
    //  string representation of this board (in the output format specified below)
    public String toString()
    {
        return boardString;
    }

    //  unit tests (not graded)
    public static void main(String[] args)
    {
    
    }
}
