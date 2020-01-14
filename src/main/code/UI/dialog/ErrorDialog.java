package UI.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import settings.Text;

public class ErrorDialog {

    public Label ErrorLabel;
    public Button button_error_ok;

    @FXML

    public void initialize() {
        ErrorLabel.setText(Text.get("ERROR"));
        button_error_ok.setText(Text.get("OK"));
    }

    public void exit_error_window(ActionEvent event) {
        Stage error = (Stage) button_error_ok.getScene().getWindow();
        error.close();
    }
}
