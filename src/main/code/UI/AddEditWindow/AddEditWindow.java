package UI.AddEditWindow;

import financeException.ModelException;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mainClasses.Common;
import settings.Text;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

abstract class AddEditWindow extends Dialog {

    protected Map<String, Node> components = new LinkedHashMap<>();
    protected Map<String, ImageView> images = new LinkedHashMap<>();
    protected Map<String, Object> values = new LinkedHashMap<>();
    protected Common common;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    AddEditWindow(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(root);
        init();
        if (isAdd()) {
            stage.setTitle(Text.get("MENU_EDIT_ADD"));
            stage.getIcons().add(new Image("images/add.png"));
        } else {
            setValues();
            stage.setTitle(Text.get("DIALOG_EDIT"));
            stage.getIcons().add(new Image("images/edit.png"));
        }
        for (Map.Entry<String, Node> entry : components.entrySet()) {
            String key = entry.getKey();
            Label label = new Label(Text.get(key), images.get(key));
            Node component = entry.getValue();
            if (component instanceof TextField) {
                if (values.containsKey(key)) {
                    ((TextField) component).setText((String.valueOf(values.get(key))));
                    ((TextField) component).setMaxWidth(250);
                }
            } else if (component instanceof ComboBox) {
                if (values.containsKey(key)) {
                    ((ComboBox) component).setValue(values.get(key));
                }
            } else if (component instanceof DatePicker) {
                if (values.containsKey(key)) {
                    // ((DatePicker) component).setValue(LocalDate.parse(dateTimeFormatter.format(LocalDate.now())));
                }
            }
            root.getChildren().addAll(label, component);
        }
        HBox hBoxForButtons = new HBox();
        ImageView imageViewOk = new ImageView("images/ok.png");
        Button ok = new Button(Text.get("MENU_EDIT_ADD"), imageViewOk);
        if (!isAdd()) {
            ok.setText(Text.get("MENU_EDIT_EDIT"));
        }
        ImageView imageViewCancel = new ImageView("images/cancel.png");
        Button cancel = new Button(Text.get("MENU_EDIT_CANCEL"), imageViewCancel);
        hBoxForButtons.setSpacing(40);
        hBoxForButtons.getChildren().addAll(ok, cancel);
        root.getChildren().addAll(hBoxForButtons);
        hBoxForButtons.setAlignment(Pos.CENTER);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public final void CloseDialog(Stage stage) {
        this.common = null;
        images.clear();
        values.clear();
        components.clear();
        stage.close();
    }

    protected ComboBox initComboBox(List<? extends Common> commons) {
        ComboBox<Common> comboBox = new ComboBox<>();
        comboBox.setMinWidth(250);
        comboBox.setItems(FXCollections.observableArrayList(commons));
        comboBox.setConverter(new StringConverter<Common>() {
            @Override
            public String toString(Common object) {
                return object.getValueForComboBox();
            }

            @Override
            public Common fromString(String string) {
                return null;
            }
        });
        comboBox.setValue(commons.get(0));
        comboBox.valueProperty().asString(commons.get(0).getValueForComboBox());
        return comboBox;
    }

    private boolean isAdd() {
        return common == null;
    }

    abstract protected void init();

    abstract protected void setValues();

    abstract protected Common getCommonFromForm() throws ModelException;

}

