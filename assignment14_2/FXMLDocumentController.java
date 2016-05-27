package assignment14_2;

import ackermann.Ackermann;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Hendrik Werner // s4549775
 */
public class FXMLDocumentController implements Initializable {

    private Ackermann generator;

    @FXML protected TextField inputN;
    @FXML protected TextField inputM;
    @FXML protected Text resultText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generator = new Ackermann();
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(inputM.textProperty(), generator.mProperty(), converter);
        Bindings.bindBidirectional(inputN.textProperty(), generator.nProperty(), converter);
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
