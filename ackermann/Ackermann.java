package ackermann;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class Ackermann {

    private final IntegerProperty m;
    private final IntegerProperty n;
    private final IntegerProperty result;
    private Task<Integer> task;

    public Ackermann() {
        this.m = new SimpleIntegerProperty();
        this.n = new SimpleIntegerProperty();
        this.result = new SimpleIntegerProperty();
    }

    public void startCalculation() {
        if (task != null) {
            task.cancel();
        }
        task = new AckermannWorker(m.get(), n.get());
        new Thread(task).start();
    }

    public void stopCalculation() {
        task.cancel();
    }

    /**
     * @return the m property
     */
    public IntegerProperty mProperty() {
        return m;
    }

    /**
     * @return the n property
     */
    public IntegerProperty nProperty() {
        return n;
    }

    /**
     * @return the result property
     */
    public IntegerProperty resultProperty() {
        return result;
    }

}
