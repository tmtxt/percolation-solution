public class Percolation {
    // store the site data
    private int[] sites;

    // store the data (root)
    private int[] id;

    // store the tree weight
    private int[] weight;

    // count the number of open sites
    private int openCount;

    private int virtualTopIdx;
    private int virtualBottomIdx;

    private int n;
    private int total;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        int total = n * n;
        this.total = total;

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

        // link all the sites on the top row to virtual top
//        for (int i = 0; i < n; i++) {
//            id[i] = virtualTopIdx;
//        }
//
//        // link all the bottom sites to the virtual bottom
//        for (int i = total - n; i < total; i++) {
//            id[i] = virtualBottomIdx;
//        }

        weight[virtualTopIdx] = 1;
        weight[virtualBottomIdx] = 1;

        // virtual top and bottom are always open
//        sites[virtualTopIdx] = 1;
//        sites[virtualBottomIdx] = 1;

        openCount = 0;
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
        openCount++;

        // we have the left to link
        if (col > 0) {
            int leftIdx = convertToIndex(row, col - 1);
            unionWhenOpen(idx, leftIdx);
        }

        if (col < n - 1) {
            int rightIdx = convertToIndex(row, col + 1);
            unionWhenOpen(idx, rightIdx);
        }

        if (row < n - 1) {
            int downIdx = convertToIndex(row + 1, col);
            unionWhenOpen(idx, downIdx);
        }

        if (row > 0) {
            int upIdx = convertToIndex(row - 1, col);
            unionWhenOpen(idx, upIdx);
        }

        // first row, link to virtual top
        if (row == 0) {
            union(idx, virtualTopIdx);
        }

        if (row == n - 1) {
            union(idx, virtualBottomIdx);
        }
    }

    // union only when q is open site
    private void unionWhenOpen(int p, int q) {
        if (sites[q] != 1) {
            return;
        }

        union(p, q);
    }

    public boolean isOpen(int row, int col) {
        row = validateArgument(row);
        col = validateArgument(col);
        int idx = convertToIndex(row, col);

        return sites[idx] == 1;
    }

    public boolean isFull(int row, int col) {
        row = validateArgument(row);
        col = validateArgument(col);
        int idx = convertToIndex(row, col);

        return isConnect(virtualTopIdx, idx);
    }

    public int numberOfOpenSites() {
        return this.openCount;
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
//        Percolation percolation = new Percolation(5);
//        System.out.println("initial");
//        System.out.println(Arrays.toString(percolation.id));
//        System.out.println("------");
//
//        percolation.open(1, 3);
//        System.out.println("id " + Arrays.toString(percolation.id));
//        System.out.println("percolates " + percolation.percolates());
//        System.out.println("openSites " + percolation.numberOfOpenSites());
//        System.out.println("isFull " + percolation.isFull(3, 3));
//        System.out.println("------");
//
//        percolation.open(2, 3);
//        System.out.println("id " + Arrays.toString(percolation.id));
//        System.out.println("percolates " + percolation.percolates());
//        System.out.println("openSites " + percolation.numberOfOpenSites());
//        System.out.println("isFull " + percolation.isFull(3, 3));
//        System.out.println("------");
//
//        percolation.open(3, 3);
//        System.out.println("id " + Arrays.toString(percolation.id));
//        System.out.println("percolates " + percolation.percolates());
//        System.out.println("openSites " + percolation.numberOfOpenSites());
//        System.out.println("isFull " + percolation.isFull(3, 3));
//        System.out.println("------");
//
//        percolation.open(4, 3);
//        System.out.println("id " + Arrays.toString(percolation.id));
//        System.out.println("percolates " + percolation.percolates());
//        System.out.println("openSites " + percolation.numberOfOpenSites());
//        System.out.println("isFull " + percolation.isFull(3, 3));
//        System.out.println("------");
//
//        percolation.open(5, 3);
//        System.out.println("id " + Arrays.toString(percolation.id));
//        System.out.println("percolates " + percolation.percolates());
//        System.out.println("openSites " + percolation.numberOfOpenSites());
//        System.out.println("isFull " + percolation.isFull(3, 3));
//        System.out.println("------");
    }
}
