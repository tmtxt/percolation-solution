import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

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
        return 0;
    }

    public double stddev() {
        return 0;
    }

    public double confidenceLo() {
        return 0;
    }

    public double confidenceHi() {
        return 0;
    }

    public static void main(String[] args) {
        PercolationStats stat = new PercolationStats(5, 2);
        System.out.println(Arrays.toString(stat.res));
    }
}
