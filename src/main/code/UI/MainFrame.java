package UI;

import javafx.application.Application;
import javafx.stage.Stage;
import settings.Text;

public class MainFrame extends Application {
    private Stage window;

        @Override
    public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle(Text.get("PROGRAM_NAME"));

    }

}
