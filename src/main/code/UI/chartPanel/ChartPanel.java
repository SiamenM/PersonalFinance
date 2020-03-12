package UI.chartPanel;

import UI.Controller;
import UI.FilterPanel.FilterPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import settings.Text;
import utilityClasses.Statistics;

import java.util.Map;

public class ChartPanel extends VBox {

    private boolean income;
    private FilterPanel filterPanel;
    private Button incomeExpenseButton;
    private PieChart chart;
    private Map<String, Double> mapData;
    private Label noDataText;
    private Label noData;


    public ChartPanel(Controller controller, boolean income) {
        this.income = income;
        this.setAlignment(Pos.CENTER);
        filterPanel = new FilterPanel(controller, this);
        this.mapData = income ? Statistics.getDataForChartOnIncomeArticles() : Statistics.getDataChartOnExpArticles();
        this.incomeExpenseButton = initButtonIncomeExpense();
        noDataText = new Label("No data");
        noDataText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        noData = new Label("", new ImageView("/images/nodata.png"));

        this.initPieChart();
    }

    private void initPieChart() {
        chart = new PieChart(FXCollections.observableArrayList(setRefreshDataForChart()));
        chart.setMaxWidth(600);
        chart.setMaxHeight(400);
        chart.setPrefHeight(600);
        chart.setPrefWidth(400);
        this.getChildren().addAll(filterPanel, incomeExpenseButton, chart);
        incomeExpenseButton.setOnAction((event) -> {
            this.income = !income;
            this.incomeExpenseButton = initButtonIncomeExpense();
            refreshChart();
        });
    }

    public void refreshChart() {
        this.mapData = income ? Statistics.getDataForChartOnIncomeArticles() : Statistics.getDataChartOnExpArticles();
        if (mapData.size() == 0) {
            initEmptyChart();
        } else {
            this.getChildren().contains(noData);
            this.getChildren().remove(noData);
            this.getChildren().contains(noDataText);
            this.getChildren().remove(noDataText);
            ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
            observableList.addAll(setRefreshDataForChart());
            chart.setData(observableList);
            if (!this.getChildren().contains(chart)) {
                this.getChildren().add(chart);
            }
        }
    }

    private void initEmptyChart() {
        this.getChildren().remove(this.chart);
        if (!this.getChildren().contains(noData)) {
            this.getChildren().addAll(noDataText, noData);
        }
    }

    private Button initButtonIncomeExpense() {
        return income ? new Button(Text.get("INCOMES_BY_ARTICLES")) : new Button(Text.get("EXPENSES_BY_ARTICLES"));
    }

    private PieChart.Data[] setRefreshDataForChart() {
        PieChart.Data[] data = new PieChart.Data[mapData.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : mapData.entrySet()) {
            data[i] = new PieChart.Data(entry.getKey(), entry.getValue());
            i++;
        }
        return data;
    }

    public void refreshChartPanelLanguage() {
        filterPanel.refreshStepButtonName();
        if (income) {
            this.incomeExpenseButton.setText(Text.get("INCOMES_BY_ARTICLES"));
        } else {
            this.incomeExpenseButton.setText(Text.get("EXPENSES_BY_ARTICLES"));
        }
    }
}