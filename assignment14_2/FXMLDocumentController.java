package assignment14_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class FXMLDocumentController implements Initializable {

    @FXML protected TextField inputN;
    @FXML protected TextField inputM;
    @FXML protected Text resultText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * @param e the action event
     */
    @FXML
    private void handleStartButton(ActionEvent e) {
        System.out.println("start");
    }

    /**
     * @param e the action event
     */
    @FXML
    private void handleStopButton(ActionEvent e) {
        System.out.println("stop");
    }

}
