package UI.chartPanel;

import UI.Controller;
import UI.FilterPanel.FilterPanel;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mainClasses.Statistics;
import settings.Text;

import java.util.Map;

public class ChartPanel extends VBox {

    private boolean income;
    private FilterPanel filterPanel;
    private Button button;
    private Controller controller;
    private PieChart chart;
    private Map<String, Double> mapData;

    public ChartPanel(Controller controller, boolean income) {
        this.income = income;
        this.setAlignment(Pos.CENTER);
        filterPanel = new FilterPanel(controller, this);
        this.controller = controller;
        this.initChartPanel();
    }

    //предусмотреть смену панелей
    public void initChartPanel() {
        if (income) {
            mapData = Statistics.getDataForChartOnIncomeArticles();
            button = new Button(Text.get("INCOMES_BY_ARTICLES"));
        } else {
            mapData = Statistics.getDataChartOnExpArticles();
            button = new Button(Text.get("EXPENSES_BY_ARTICLES"));
        }
        initPieChart(mapData);
    }


    private void initPieChart(Map<String, Double> mapData) {
        if (mapData.size() == 0) {
            initEmptyChart();
        } else {
            PieChart.Data[] data = new PieChart.Data[mapData.size()];
            int i = 0;
            for (Map.Entry<String, Double> entry : mapData.entrySet()) {
                data[i] = new PieChart.Data(entry.getKey(), entry.getValue());
                i++;
            }
            chart = new PieChart(FXCollections.observableArrayList(data));
            chart.setMaxWidth(600);
            chart.setMaxHeight(400);
            chart.setPrefHeight(600);
            chart.setPrefWidth(400);
            this.getChildren().addAll(filterPanel, button, chart);
            // return new PieChart(FXCollections.observableArrayList(data));
        }
    }

    private void initEmptyChart() {
        Label noDataText = new Label("No data");
        noDataText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Label noData = new Label("", new ImageView("/images/nodata.png"));
        this.getChildren().addAll(filterPanel, button, noDataText, noData);
    }


//        public void refresh () {
//            this.getChildren().removeAll();
//            this.initChartPanel();
//        }

    public void refreshChartPanelLanguage() {
        filterPanel.refreshStepButtonName();
        if (income) {
            button.setText(Text.get("INCOMES_BY_ARTICLES"));
        } else {
            button.setText(Text.get("EXPENSES_BY_ARTICLES"));
        }
    }
}


