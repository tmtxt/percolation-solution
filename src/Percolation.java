import java.util.Arrays;

public class Percolation {
    // store the site data
    private int[] sites;

    // store the data (root)
    private int[] id;

    // store the tree weight
    private int[] weight;

    private int virtualTopIdx;
    private int virtualBottomIdx;

    private int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        int total = n * n;

        // +2 for the virtual top and virtual bottom
        sites = new int[total];
        id = new int[total + 2];
        weight = new int[total + 2];

        for (int i = 0; i < total; i++) {
            // init all sites to be blocked
            sites[i] = 0;

            // init all sites to point to itself
            id[i] = i;

            // init all tree to have weight 1
            weight[i] = 1;
        }

        //
        virtualTopIdx = total;
        virtualBottomIdx = total + 1;

        // virtual top and bottom point to itself
        id[virtualTopIdx] = virtualTopIdx;
        id[virtualBottomIdx] = virtualBottomIdx;
        weight[virtualTopIdx] = 1;
        weight[virtualBottomIdx] = 1;

        // link all the sites on the top row to virtual top
        for (int i = 0; i < n; i++) {
            id[i] = virtualTopIdx;
        }

        // link all the bottom sites to the virtual bottom
        for (int i = total - n; i < total; i++) {
            id[i] = virtualBottomIdx;
        }
    }

    public void open(int row, int col) {
        // not open again if already open
        if (isOpen(row, col)) {
            return;
        }

        row = validateArgument(row);
        col = validateArgument(col);
        int idx = convertToIndex(row, col);

        // set this site to open
        sites[idx] = 1;

        // we have the left to link
        if (col > 0) {
            int leftIdx = convertToIndex(row, col - 1);
            union(idx, leftIdx);
        }

        if (col < n - 1) {
            int rightIdx = convertToIndex(row, col + 1);
            union(idx, rightIdx);
        }

        if (row > 0) {
            int downIdx = convertToIndex(row + 1, col);
            union(idx, downIdx);
        }

        if (row < n - 1) {
            int upIdx = convertToIndex(row - 1, col);
            union(idx, upIdx);
        }
    }

    public boolean isOpen(int row, int col) {
        row = validateArgument(row);
        col = validateArgument(col);
        int idx = convertToIndex(row, col);

        return sites[idx] == 1;
    }

    public boolean isFull(int row, int col) {
        return true;
    }

    public int numberOfOpenSites() {
        return 1;
    }

    public boolean percolates() {
        return isConnect(virtualTopIdx, virtualBottomIdx);
    }

    private int validateArgument(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("i cannot be less than 1");
        }
        if (i > n) {
            throw new IllegalArgumentException("i cannot be greater than n");
        }

        return i - 1;
    }

    private void union(int p, int q) {
        int rootP = this.root(p);
        int rootQ = this.root(q);
        int weightP = weight[rootP];
        int weightQ = weight[rootQ];

        if (weightP >= weightQ) {
            id[rootQ] = rootP;
            weight[rootP] += weight[rootQ];
        } else {
            id[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
    }

    private boolean isConnect(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        return rootP == rootQ;
    }

    /**
     * Find root of one item
     *
     * @param i
     * @return
     */
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }

        return i;
    }

    private int convertToIndex(int row, int col) {
        return row * n + col;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);

        percolation.open(2, 3);
        System.out.println(Arrays.toString(percolation.id));
        System.out.println(percolation.percolates());

        percolation.open(3, 3);
        System.out.println(Arrays.toString(percolation.id));
        System.out.println(percolation.percolates());

        percolation.open(4, 3);
        System.out.println(Arrays.toString(percolation.id));
        System.out.println(percolation.percolates());
    }
}
