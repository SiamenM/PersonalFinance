package UI.about;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import settings.Text;

import java.io.IOException;

public class ControllerAbout {

    @FXML
    public Button buttonOk;
    public Label labelWithRespect;
    public Label labelNameProgram;
    public Label labelDescriptionProgram;
    public Label labelVersion;

    public void initialize() {

        labelWithRespect.setText(Text.get("LABEL_WITH_RESPECT"));
        labelNameProgram.setText(Text.get("LABEL_NAME_PROGRAM"));
        labelDescriptionProgram.setText(Text.get("LABEL_DESCRIPTION_PROGRAM"));
        labelVersion.setText(Text.get("LABEL_VERSION"));
        buttonOk.setText(Text.get("OK"));

    }

    public void pressOkAbout(ActionEvent event) throws IOException {
        Stage stageAbout = (Stage) buttonOk.getScene().getWindow();
        stageAbout.close();
    }
}


