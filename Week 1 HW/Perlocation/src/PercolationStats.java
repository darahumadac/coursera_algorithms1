
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darah
 */
public class PercolationStats {
    
    private final int GRID_SIZE;
    private final int TRIAL_COUNT;
    private final double CONFIDENCE_VALUE = 1.96;
    
    private double[] _trialResults;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        GRID_SIZE = n;
        TRIAL_COUNT = trials;
        _trialResults = new double[TRIAL_COUNT];
        
        Percolation percolation;
        int gridRow;
        int gridCol;
        
        for(int trial=0; trial<TRIAL_COUNT; trial++){
            do{
                percolation = new Percolation(GRID_SIZE);
            
                gridRow = StdRandom.uniform(1, GRID_SIZE);
                gridCol = StdRandom.uniform(1, GRID_SIZE);
                
                percolation.open(gridRow, gridCol);
            }while(!percolation.percolates());
            
            _trialResults[trial] = percolation.numberOfOpenSites() / (GRID_SIZE*GRID_SIZE);
        }
    }
    
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(_trialResults);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(_trialResults);
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - CONFIDENCE_VALUE*stddev() / Math.sqrt(TRIAL_COUNT);
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + CONFIDENCE_VALUE*stddev() / Math.sqrt(TRIAL_COUNT);
    }

    // test client (described below)
    public static void main(String[] args){
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(gridSize, trials);
        System.out.println("mean                    = "+stats.mean());
        System.out.println("stddev                  = "+stats.stddev());
        System.out.println("95% confidence interval = ["+stats.confidenceLo()+", ["+ stats.confidenceHi()+"]");
    }
}
