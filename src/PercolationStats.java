import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int n;
    private int total;
    private int trials;
    private double[] res;

    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }

        this.n = n;
        this.total = n * n;
        this.trials = trials;
        this.res = new double[trials];

        this.runAll();
    }

    // run all trials times
    private void runAll() {
        for (int i = 0; i < trials; i++) {
            this.runOne(i);
        }
    }

    // run each trial and store the result to this.res
    private void runOne(int order) {
        Percolation percolation = new Percolation(n);
        int count = 0;

        do {
            int row = StdRandom.uniform(1, this.n + 1);
            int col = StdRandom.uniform(1, this.n + 1);

            if (percolation.isOpen(row, col)) {
                continue;
            }

            percolation.open(row, col);
            count++;
        } while (!percolation.percolates());

        this.res[order] = (double) count / this.total;
    }

    public double mean() {
        return StdStats.mean(this.res);
    }

    public double stddev() {
        return StdStats.stddev(this.res);
    }

    public double confidenceLo() {
        return StdStats.min(this.res);
    }

    public double confidenceHi() {
        return StdStats.max(this.res);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stat = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stat.mean());
        System.out.println("stddev                  = " + stat.stddev());
        System.out.println("95% confidence interval = [" + stat.confidenceLo() + ", " + stat.confidenceHi() + "]");
    }
}
