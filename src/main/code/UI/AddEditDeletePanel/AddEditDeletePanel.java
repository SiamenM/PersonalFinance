package UI.AddEditDeletePanel;

import UI.Controller;
import financeException.ModelException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainClasses.Common;
import settings.Text;

import java.util.Optional;

abstract class AddEditDeletePanel extends HBox {
    private Controller controller;

    AddEditDeletePanel(Controller controller) {
        super();
        this.controller = controller;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
        setSpacing(10);
        initPanel();
    }

    private void initPanel() {
        Button add = new Button(Text.get("ADD"), new ImageView("/images/add.png"));
        add.setMinWidth(120);
        add.setMaxWidth(120);
        add.setOnAction(event -> showAddEditWindow(null));
        Button edit = new Button(Text.get("EDIT"), new ImageView("/images/edit.png"));
        edit.setMinWidth(120);
        edit.setMaxWidth(120);
        edit.setOnAction(event -> {
            Common common = getSelectedCommon();
            if (common != null) {
                showAddEditWindow(common);
            } else {
                Controller.showAlert(Text.get("ERROR_NULL_ROW"));
            }
        });
        Button delete = new Button(Text.get("DELETE"), new ImageView("/images/delete.png"));
        delete.setMinWidth(120);
        delete.setMaxWidth(120);
        delete.setOnAction(event -> {
            Common common = getSelectedCommon();
            if (common != null) {
                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle(Text.get("CONFIRM_DELETE_TITLE"));
                confirmDialog.setHeaderText(Text.get("CONFIRM_DELETE_TEXT"));
                ButtonType delete1 = new ButtonType(Text.get("MENU_EDIT_DELETE"));
                ButtonType cancel = new ButtonType(Text.get("CANCEL"));
                Stage stage = (Stage) confirmDialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/images/question.png"));
                confirmDialog.getButtonTypes().clear();
                confirmDialog.getButtonTypes().addAll(delete1, cancel);
                Optional<ButtonType> option = confirmDialog.showAndWait();
                if (option.get() == delete1) {
                    try {
                        deleteRow(common);
                        controller.initListViewBalanceCurrencyAndFinishBalance();
                    } catch (ModelException e) {
                        e.printStackTrace();
                    }
                } else if (option.get() == cancel) {
                    confirmDialog.close();
                }
            } else {
                Controller.showAlert(Text.get("ERROR_NULL_ROW"));
            }
        });
        this.getChildren().addAll(add, edit, delete);
    }

    abstract void deleteRow(Common c) throws ModelException;

    abstract void showAddEditWindow(Common c);

    abstract Common getSelectedCommon();

}

