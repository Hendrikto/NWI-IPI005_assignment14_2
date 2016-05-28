package ackermann;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.m = new SimpleIntegerProperty();
        this.n = new SimpleIntegerProperty();
        this.result = new SimpleIntegerProperty();
        this.status = new SimpleStringProperty();
    }

    public void startCalculation() {
        stopCalculation();
        task = new AckermannWorker(m.get(), n.get());
        task.setOnSucceeded(this::handleSucceeded);
        task.messageProperty().addListener(e -> status.set(task.getMessage()));
        new Thread(task).start();
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

    /**
     * @return the status property
     */
    public StringProperty statusProperty() {
        return status;
    }

    private void handleSucceeded(WorkerStateEvent e) {
        try {
            result.set(task.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ackermann.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
