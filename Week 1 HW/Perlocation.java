//all should have O(1) except for initialization which should be O(N^2)
public class Perlocation{

	private const int CLOSED_SITE = -1000;  
	private const int TOP_SITE = -1;
	// private int[] OPEN_TOP_SITES;
	// private int[] OPEN_BOTTOM_SITES;
	
	private int _openSites;

	// create N-by-N grid, with all sites blocked O(N^2)
	public Percolation(int n){
		if(n<1) throw new IllegalArgumentException();
		_openSites = 0;
		
		OPEN_TOP_SITES = new int[n];
		OPEN_BOTTOM_SITES = new int[n];
		
		for(int row=0; row<n; row++){
			for(int col=0; col<n; col++){
				grid[row][col] = CLOSED_SITE;
			}
		}
	}    
	
	// open site (row i, column j) if it is not open already
	public void open(int row, int col){
		if(row < 1 || row > n || col < 1 || col > n){
			throw new IndexOutOfBoundsException();
		}
	
		if(!isOpen(row, col)){
			grid[row][col] = (row*n) + col; //set to its index;
			_openSites++;
			
			int leftNeighbor = grid[row][col-1] 
			int rightNeighbor = grid[row][col+1];
			int topNeighbor = grid[row-1][col];
			int bottomNeighbor = grid[row+1][col];
			
			if(leftNeighbor != CLOSED_SITE){
				grid[row][col-1] = Math.min(leftNeighbor, grid[row][col]);
			}
			if(rightNeighbor != CLOSED_SITE){
				grid[row][col+1] = Math.min(rightNeighbor, grid[row][col]);
			}
			if(topNeighbor != CLOSED_SITE){
				grid[row-1][col] = Math.min(topNeighbor, grid[row][col]);
			}
			if(bottomNeighbor != CLOSED_SITE){
				grid[row+1][col] = Math.min(botttomNeighbor, grid[row][col]);
			}
		}
	}
	
	// is site (row row, column col) open?
	public boolean isOpen(int row, int col){ throws 
		return grid[row][col] != CLOSED_SITE;
	}     
	
	// is site (row row, column col) full? Full site - can be connected to the top row via neighboring open sites
	public boolean isFull(int row, int col){
		return (grid[row][col-1] == TOP_SITE || grid[row][col+1] == TOP_SITE || 
				grid[row-1][col] == TOP_SITE || grid[row+1][col] == TOP_SITE);
	}
	
	// number of open sites
	public int numberOfOpenSites(){
		return _openSites;
	}
	
	// does the system percolate?
	public boolean percolates(){

		if(OPEN_TOP_SITES.length == 0 || OPEN_BOTTOM_SITES.length == 0){
			return false;
		}
	
	}

	// test client (optional)	
	public static void main(String[] args){
	
	}   

}