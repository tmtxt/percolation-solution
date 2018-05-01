public class Percolation {
    private int[] id;

    private int n;

    public Percolation(int n) {
        this.n = n;
        int total = n * n;
        id = new int[total];

        for (int i = 0; i < total; i++) {
            // init all sites to be blocked
            id[i] = 0;
        }
    }

    public void open(int row, int col) {

    }

    public boolean isOpen(int row, int col) {

    }

    public boolean isFull(int row, int col) {

    }

    public int numberOfOpenSites() {

    }

    public boolean percolates() {
        
    }

    private int convertToIndex(int row, int col) {
        return row * n + col;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
