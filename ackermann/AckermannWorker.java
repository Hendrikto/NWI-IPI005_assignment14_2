package ackermann;

import javafx.concurrent.Task;

/**
 * A task that calculates Ackermann values.
 *
 * @author Hendrik Werner // s4549775
 */
public class AckermannWorker extends Task<Integer> {

    private final int m;
    private final int n;

    public AckermannWorker(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    protected Integer call() throws Exception {
        return calculate(m, n);
    }

    private int calculate(int m, int n) {
        if (m == 0) {
            return n + 1;
        }
        if (n == 0) {
            return calculate(m - 1, 1);
        }
        return calculate(m - 1, calculate(m, n - 1));
    }

}
