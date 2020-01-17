package UI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import mainClasses.Common;
import settings.Text;

import java.util.LinkedHashMap;
import java.util.Map;

public class AddEditWindow extends Dialog {

    protected Map<String, Node> components = new LinkedHashMap<>();
    protected Map<String, Image> images = new LinkedHashMap<>();
    protected Map<String, Object> values = new LinkedHashMap<>();
    protected Common common;

    public AddEditWindow(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("ggsdfh");
        stage.setScene(scene);
        stage.show();
        setResizable(false);
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }
}

