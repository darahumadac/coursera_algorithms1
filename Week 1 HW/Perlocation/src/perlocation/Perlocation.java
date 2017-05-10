/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perlocation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 * @author Darah
 */
public class Perlocation {
    
    private WeightedQuickUnionUF unionFind;
    private boolean[][] _grid;
    
    private final boolean BLOCKED = false;
    private final boolean OPEN = true;
    private final int GRID_SIZE;
    private final int VIRTUAL_TOP_SITE = 0;
    private final int VIRTUAL_BOTTOM_SITE;
    
    // create n-by-n grid, with all sites blocked
    public Perlocation(int n){
        GRID_SIZE = n;
        
        if(GRID_SIZE <= 0){
            throw new IllegalArgumentException();
        }
        
        int siteCount = (GRID_SIZE*GRID_SIZE) + 2;
        unionFind = new WeightedQuickUnionUF(siteCount);
        
        VIRTUAL_BOTTOM_SITE = siteCount - 1;
        
        _grid = new boolean[GRID_SIZE][GRID_SIZE];
        for(int row=0; row<GRID_SIZE; row++){
            for(int col=0; col<GRID_SIZE; col++){
                _grid[row][col] = BLOCKED;
            }
        }
    }                
    // open site (row, col) if it is not open already
    public void open(int row, int col){
        validateRowAndCol(row, col);
        if(!isOpen(row, col)){
            _grid[row-1][col-1] = OPEN;
            
            //connect to virtual sites
            if(row == 1){
                unionFind.union(VIRTUAL_TOP_SITE, getUnionFindIndex(row, col));
            }else if(row == GRID_SIZE){
                unionFind.union(VIRTUAL_BOTTOM_SITE, getUnionFindIndex(row, col));
            }
            
            //connect to open neighbors
            
            
        }
    }  
    
    private void validateRowAndCol(int row, int col){
        if(row < 1 || row > GRID_SIZE || col < 1 || col > GRID_SIZE){
            throw new IndexOutOfBoundsException();
        }
    }
    
    private int getUnionFindIndex(int row, int col){
        return GRID_SIZE * (row-1) + col;
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        return _grid[row-1][col-1];
    }
    
    // is site (row, col) full?
    public boolean isFull(int row, int col){
    
    }
    
    public int numberOfOpenSites(){
        
    }
    
    public boolean percolates(){
    
    }
           
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
