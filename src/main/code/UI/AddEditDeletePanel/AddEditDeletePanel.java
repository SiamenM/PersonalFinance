package UI.AddEditDeletePanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import settings.Text;

public class AddEditDeletePanel extends HBox {

    public AddEditDeletePanel() {
        super();
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
        setSpacing(10);
        initPanel();
    }

    private void initPanel() {
        Button add = new Button(Text.get("MENU_EDIT_ADD"), new ImageView("/images/add.png"));
        add.setMinWidth(120);
        add.setMaxWidth(120);
        //add.setFont(Font.font("Helvetica", 13));
        Button edit = new Button(Text.get("MENU_EDIT_EDIT"), new ImageView("/images/edit.png"));
        edit.setMinWidth(120);
        edit.setMaxWidth(120);
        //edit.setFont(Font.font("Helvetica", 13));
        Button delete = new Button(Text.get("MENU_EDIT_DELETE"), new ImageView("/images/delete.png"));
        delete.setMinWidth(120);
        delete.setMaxWidth(120);
        //delete.setFont(Font.font("Helvetica", 13));
        this.getChildren().addAll(add, edit, delete);
    }
}

