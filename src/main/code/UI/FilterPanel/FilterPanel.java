package UI.FilterPanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        Button right = new Button("", new ImageView("/images/right.png"));
        left.setMaxHeight(20);
        left.setPrefHeight(20);
        right.setMaxHeight(20);
        step.setMinWidth(50);
        step.setMaxHeight(left.getMaxHeight());
        step.setPrefHeight(left.getMaxHeight());
        this.getChildren().addAll(left, step, right);
    }
}
