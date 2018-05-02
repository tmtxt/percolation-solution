import java.util.Arrays;

public class Percolation {
    // store the site data
    public int[] sites;

    // store the data (root)
    public int[] id;

    // store the tree weight
    public int[] weight;

    public int n;

    public Percolation(int n) {
        this.n = n;
        int total = n * n;

        sites = new int[total];
        id = new int[total];
        weight = new int[total];

        for (int i = 0; i < total; i++) {
            // init all sites to be blocked
            sites[i] = 0;

            // init all sites to point to itself
            id[i] = i;

            // init all tree to have weight 1
            weight[i] = 1;
        }
    }

    public void open(int row, int col) {

    }

    public boolean isOpen(int row, int col) {
        return true;
    }

    public boolean isFull(int row, int col) {
        return true;
    }

    public int numberOfOpenSites() {
        return 1;
    }

    public boolean percolates() {
        return true;
    }

    public void union(int p, int q) {
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
        Percolation per = new Percolation(5);

        per.union(10, 15);
        per.union(12, 21);
        per.union(10, 20);
        per.union(10, 24);
        per.union(24, 2);

        System.out.println(Arrays.toString(per.id));
    }
}
