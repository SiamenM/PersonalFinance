package UI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import settings.Text;

import java.io.File;
import java.io.IOException;

public class Controller {

    public void pressExit(ActionEvent exit){
        Platform.exit();
    }

    public void pressAbout(ActionEvent about) throws IOException {
        Parent aboutRoot = FXMLLoader.load(getClass().getResource("/UI/About.fxml"));
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(aboutRoot));
        aboutStage.setTitle(Text.get("MENU_HELP_ABOUT"));
        aboutStage.setResizable(false);
        Image iconMain = new Image(new File("images/about.png").toURI().toString());
        aboutStage.getIcons().add(iconMain);
        aboutStage.show();
    }
    public void press(ActionEvent event) {

    }
}
