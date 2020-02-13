package UI.AddEditDeletePanel;

import UI.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mainClasses.Common;
import settings.Text;

public abstract class AddEditDeletePanel extends HBox {
    private Controller controller;

    public AddEditDeletePanel(Controller controller) {
        super();
        this.controller = controller;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
        setSpacing(10);
        initPanel();
    }

    private void initPanel() {
        Button add = new Button(Text.get("MENU_EDIT_ADD"), new ImageView("/images/add.png"));
        add.setMinWidth(120);
        add.setMaxWidth(120);
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAddEditWindow(null);
            }
        });
        Button edit = new Button(Text.get("MENU_EDIT_EDIT"), new ImageView("/images/edit.png"));
        edit.setMinWidth(120);
        edit.setMaxWidth(120);
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Common common = getSelectedCommon();
                if (common != null) {
                    showAddEditWindow(common);
                } else {
                    Controller.showAlert(Text.get("ERROR_NULL_ROW"));
                }
            }
        });
        Button delete = new Button(Text.get("MENU_EDIT_DELETE"), new ImageView("/images/delete.png"));
        delete.setMinWidth(120);
        delete.setMaxWidth(120);
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Common common = getSelectedCommon();
                if (common != null) {

                } else {
                    Controller.showAlert(Text.get("ERROR_NULL_ROW"));
                }
            }
        });
        this.getChildren().addAll(add, edit, delete);
    }

    abstract void showAddEditWindow(Common c);

    private Common getSelectedCommon() {
        return controller.getAccountsTable().getCommon();
    }
}

