package ackermann;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

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
        stopCalculation();
        task = new AckermannWorker(m.get(), n.get());
        new Thread(task).start();
        task.setOnSucceeded(this::handleSucceeded);
    }

    public void stopCalculation() {
        if (task != null) {
            task.cancel();
        }
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

    private void handleSucceeded(WorkerStateEvent e) {
        try {
            result.set(task.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ackermann.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
