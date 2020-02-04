package UI.FilterPanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import saveLoad.SaveData;
import settings.Format;

public class FilterPanel extends HBox {

    public FilterPanel() {
        super();
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(5, 5, 5, 5));
        initFilterPanel();
    }

    private void initFilterPanel() {
        Button left = new Button("", new ImageView("/images/left.png"));
        Button step = new Button(Format.getTitleFilter(SaveData.getInstance().getFilter()));
        step.setFont(Font.font("Verdana", 14));
        Button right = new Button("", new ImageView("/images/right.png"));
        this.getChildren().addAll(left, step, right);
    }
}
