
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
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
public class SolverTests {
    
    @Test(expected = NullPointerException.class)
    public void NullArgument_WasPassed()
    {
        Solver solver = new Solver(null);
        
    }
    
    @Test
    public void puzzle04_Test()
    {
        testFromFile("test/puzzle04.txt");
    }
    
    @Test
    public void unsolvable_2x2()
    {
        int[][] blocks = new int[][]
        {
            {2,1},
            {3,0}
        };
        
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        Assert.assertFalse(solver.isSolvable());
        
    }
    
    
    //@Test
    public void puzzle3x3_Unsolvable()
    {
        //testFromFile("test/puzzle3x3-unsolvable.txt");
        int[][] blocks = new int[][]
        {
            {1,2,3},
            {4,6,5},
            {7,8,0}
        };
        
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        Assert.assertFalse(solver.isSolvable());
    }
    
    
    
    private void testFromFile(String filename)
    {
        // create initial board from file
        In in = new In(filename);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
    
    
    
}
