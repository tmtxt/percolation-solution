public class PercolationStats {
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }
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

    }
}
