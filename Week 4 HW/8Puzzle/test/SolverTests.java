
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
        testFromFile("test/puzzle04.txt", true, 4);
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
    
    @Test
    public void puzzle2x2_Unsolvable()
    {
        testFromFile("test/puzzle2x2-unsolvable2.txt", false, -1);
        
    }
    
    @Test
    public void puzzle2x2_MoreThan2Moves()
    {
        testFromFile("test/puzzle2x2-03.txt", true, 3);
        testFromFile("test/puzzle2x2-05.txt", true, 5);
        testFromFile("test/puzzle2x2-06.txt", true, 6);
    }
    
    @Test
    public void puzzle2x2_3Moves()
    {
        testFromFile("test/puzzle03.txt", true, 3);
    }
    
    @Test
    public void puzzle3x3_Max5Moves()
    {
        testFromFile("test/puzzle3x3-00.txt", true, 0);
        testFromFile("test/puzzle3x3-01.txt", true, 1);
        testFromFile("test/puzzle3x3-02.txt", true, 2);
        testFromFile("test/puzzle3x3-03.txt", true, 3);
        testFromFile("test/puzzle3x3-05.txt", true, 5);
    }
    
    @Test
    public void puzzle3x3_Solvable_MoreThan5Moves()
    {
        testFromFile("test/puzzle3x3-06.txt", true, 6);
        testFromFile("test/puzzle3x3-07.txt", true, 7);
    }
    
    @Test
    public void puzzle4x4_Max5Moves()
    {
        testFromFile("test/puzzle04.txt", true, 4);
        testFromFile("test/puzzle4x4-05.txt", true, 5);
    }
    
    @Test
    public void puzzle4x4_MoreThan5Moves()
    {
        testFromFile("test/puzzle4x4-06.txt", true, 6);
        testFromFile("test/puzzle4x4-07.txt", true, 7);
        testFromFile("test/puzzle4x4-10.txt", true, 10);
    }
    
    
    @Test
    public void puzzle3x3_Unsolvable()
    {
        testFromFile("test/puzzle3x3-unsolvable.txt", false, -1);
    }
    
    
    
    private void testFromFile(String filename, boolean isSolvable, int moves)
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
        StdOut.println("---------" + filename + "---------");
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        
        Assert.assertEquals(isSolvable, solver.isSolvable());
        Assert.assertEquals(moves, solver.moves());
    }
    
    
    
}
