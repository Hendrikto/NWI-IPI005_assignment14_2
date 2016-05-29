package ackermann;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private final StringProperty status;
    private Task<Integer> task;

    public Ackermann() {
        m = new SimpleIntegerProperty();
        n = new SimpleIntegerProperty();
        result = new SimpleIntegerProperty();
        status = new SimpleStringProperty();
    }

    /**
     * Stop the currently running calculation and start a new one.
     */
    public void startCalculation() {
        stopCalculation();
        task = new AckermannWorker(m.get(), n.get());
        task.setOnSucceeded(this::handleSucceeded);
        task.stateProperty().addListener(this::handleStateChanged);
        new Thread(task).start();
    }

    /**
     * Stop the current calculation.
     */
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

    /**
     * @return the status property
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     * @param e the worker state event
     */
    private void handleSucceeded(WorkerStateEvent e) {
        try {
            result.set(task.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ackermann.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param o the observable
     */
    private void handleStateChanged(Observable o) {
        status.set(task.getState().name().toLowerCase());
    }

}
