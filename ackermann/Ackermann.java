package ackermann;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class Ackermann {

    private final IntegerProperty m;
    private final IntegerProperty n;
    private boolean stop;

    public Ackermann() {
        this.m = new SimpleIntegerProperty();
        this.n = new SimpleIntegerProperty();
        stop = false;
    }

    public void startCalculation() {
        calculate(m.get(), n.get());
    }

    public void stopCalculation() {
        stop = false;
    }

    /**
     * @return the m
     */
    public IntegerProperty mProperty() {
        return m;
    }

    /**
     * @return the n
     */
    public IntegerProperty nProperty() {
        return n;
    }

    private int calculate(int m, int n) {
        if (stop) {
            return 0;
        }
        if (m == 0) {
            return n + 1;
        }
        if (n == 0) {
            return calculate(m - 1, 1);
        }
        return calculate(m - 1, calculate(m, n - 1));
    }

}
