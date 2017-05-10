//all should have O(1) except for initialization which should be O(N^2)
public class Perlocation{

	//Constants
	private const int BLOCKED_SITE = -1000;  
	private const int TOP_SITE;
	private const int BOTTOM_SITE;
	private const int GRID_SIZE;
	
	private int[][] _grid;
	private int _openSites;

	// create N-by-N grid, with all sites blocked O(N^2)
	public Percolation(int n){
		if(n<1) throw new IllegalArgumentException();
		
		TOP_SITE = n*-1;
		BOTTOM_SITE = n;
		GRID_SIZE = n;
		
		for(int row=0; row<n; row++){
			for(int col=0; col<n; col++){
				_grid[row][col] = BLOCKED_SITE;
			}
		}
		
		_openSites = 0;
	}    
	
	//TODO: Fix this!! // Use recursion here
	// open site (row i, column j) if it is not open already
	public void open(int row, int col){
		if(row < 1 || row > n || col < 1 || col > n){
			throw new IndexOutOfBoundsException();
		}
		
		int siteValue = (row*n) + col;
		int root = siteValue;
		
		if(!isOpen(row, col)){
			_grid[row][col] =  siteValue;
			_openSites++;
			
			int leftNeighbor = _grid[row][col-1] 
			int rightNeighbor = _grid[row][col+1];
			int topNeighbor = _grid[row-1][col];
			int bottomNeighbor = _grid[row+1][col];
			
			if(leftNeighbor != BLOCKED_SITE){
				root = Math.min(leftNeighbor, root);
			}
			if(rightNeighbor != BLOCKED_SITE){
				root = Math.min(rightNeighbor, root);
			}
			if(topNeighbor != BLOCKED_SITE){
				root = Math.min(topNeighbor, root);
			}
			if(bottomNeighbor != BLOCKED_SITE){
				root = Math.min(botttomNeighbor, root);
			}
			
			_grid[row][col] = root;
			
			//connect neighbors recursively
			connectNeighbors(row, col-1);
			connectNeighbors(row, col+1);
			connectNeighbors(row-1, col);
			connectNeighbors(row+1, col);
			
		}
	}
	
	private void connectNeighbors(int row, int col){
		if(_grid[row][col] != BLOCKED_SITE && 
		  (row >= 0 && row < GRID_SIZE) && (col >= 0 && col < GRID_SIZE)){
			_grid[row][col] = root;
			
			connectNeighbors(row, col-1);
			connectNeighbors(row, col+1);
			connectNeighbors(row-1, col);
			connectNeighbors(row+1, col);
		}
	}
	
	private void printGrid(){
		int row = 0;
		int col = 0;
		for(row = 0; row<GRID_SIZE; row++){
			for(col = 0; col<GRID_SIZE; col++){
				System.out.print(_grid[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	// is site (row row, column col) open?
	public boolean isOpen(int row, int col){
		return _grid[row][col] != BLOCKED_SITE;
	}     
	
	// is site (row row, column col) full? Full site - can be connected to the top row via neighboring open sites
	public boolean isFull(int row, int col){
		return (_grid[row][col-1] == TOP_SITE || _grid[row][col+1] == TOP_SITE || 
				_grid[row-1][col] == TOP_SITE || _grid[row+1][col] == TOP_SITE);
	}
	
	// number of open sites
	public int numberOfOpenSites(){
		return _openSites;
	}
	
	// does the system percolate?
	public boolean percolates(){
		int col;
		for(col = 0; col<GRID_SIZE; col++){
			if(isFull(GRID_SIZE, col)){
				return true;
			}
		}
		return false;
	}

	// test client (optional)	
	public static void main(String[] args){
	
	}   

}