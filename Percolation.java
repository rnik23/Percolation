import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private int n; //grid size
    private int countOpen; //number open
    private WeightedQuickUnionUF uf; //union find for percolation checks
    private WeightedQuickUnionUF uf2; //union find for fullness checks
    private int top;
    private int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        opened = new boolean[n][n];
        countOpen = 0;

        // set up virtual node indices
        top = n * n;
        bottom = n * n + 1;
        // Initialize union-find structures:
        // uf: includes two virtual nodes
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        // Validate indices: row and col should be between 1 and n
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + n);
        }
        if(!isOpen(row,col)){
            opened[row-1][col-1] = true;
            countOpen ++;
            // Convert 2D index to 1D index for the union-find structures
            int index = (row -1) * n + (col -1);

            if (row == 1){
                uf.union(index,top);
                uf2.union(index,top);
            }
            if(row == n) {
                uf.union(index, bottom);
            }
            // Check and connect to the neighbor above
            if(row > 1 && isOpen(row-1,col)){
                uf.union(index, index - n);    // index - n is the site directly above
                uf2.union(index, index - n);
            }
            if(row < n && isOpen(row + 1,col)){
                uf.union(index, index + n);
                uf2.union(index, index + n);
            }
            if(col<n && isOpen(row,col+1)){
                uf.union(index,index+1);
                uf2.union(index, index+1);
            }
            if(col>1 && isOpen(row,col-1)){
                uf.union(index,index-1);
                uf2.union(index,index-1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + n);
        }
        return opened[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        // Validate indices: row and col must be within 1 and n.
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + n);
        }
        int index = (row -1) * n + col;
        return top == uf.find(index);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.find(top) == uf.find(bottom);
    }

}
