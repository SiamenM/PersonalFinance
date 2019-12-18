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

    public void pressExit(ActionEvent exit) {
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

    public void pressAddAccount(ActionEvent addAccount) throws IOException {
        Parent addWindow = FXMLLoader.load(getClass().getResource("/UI/AddAccountWindow.fxml"));
        Stage addStage = new Stage();
        addStage.setScene(new Scene(addWindow));
        addStage.setTitle(Text.get("MENU_EDIT_ADD"));
        addStage.setResizable(false);
        Image iconMain = new Image(new File("images/add.png").toURI().toString());
        addStage.getIcons().add(iconMain);
        addStage.show();
    }

    public void press(ActionEvent event) {

    }
}
