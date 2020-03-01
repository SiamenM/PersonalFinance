package UI.FilterPanel;

import UI.Tables.FinanceTable;
import UI.chartPanel.ChartPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import saveLoad.SaveData;
import settings.Format;

public class FilterPanel extends HBox {
    private FinanceTable financeTable;
    private ChartPanel chartPanel = null;
    private Button step;

    public FilterPanel(FinanceTable financeTable) {
        super();
        this.financeTable = financeTable;

        initFilterPanel();
    }

    public FilterPanel(ChartPanel chartPanel) {
       super();
       this.chartPanel = chartPanel;
        initFilterPanel();
    }

    private void initFilterPanel() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(5, 5, 5, 5));
        step = new Button(Format.getTitleFilter(SaveData.getInstance().getFilter()));
        step.setOnAction(event -> {
            SaveData.getInstance().getFilter().nextPeriod();
            step.setText(Format.getTitleFilter(SaveData.getInstance().getFilter()));
            if(chartPanel==null){
                financeTable.fillIn();
            } else {
                chartPanel.refresh();
            }

        });
        Button left = new Button("", new ImageView("/images/left.png"));
        left.setOnAction(event -> {
            SaveData.getInstance().getFilter().prev();
            step.setText(Format.getTitleFilter(SaveData.getInstance().getFilter()));
            if(chartPanel==null){
                financeTable.fillIn();
            } else {
                chartPanel.refresh();
            }
        });
        Button right = new Button("", new ImageView("/images/right.png"));
        right.setOnAction(event -> {
            SaveData.getInstance().getFilter().next();
            step.setText(Format.getTitleFilter(SaveData.getInstance().getFilter()));
            if(chartPanel==null){
                financeTable.fillIn();
            } else {
                chartPanel.refresh();
            }
        });
        step.setPrefHeight(left.getPrefHeight());
        step.setMinHeight(left.getPrefHeight());
        this.getChildren().addAll(left, step, right);
    }

    public void refreshStepButtonName(){
        step.setText(Format.getTitleFilter(SaveData.getInstance().getFilter()));
    }
}
