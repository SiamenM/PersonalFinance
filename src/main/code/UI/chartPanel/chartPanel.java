package UI.chartPanel;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import mainClasses.Statistics;
import settings.Text;

import java.util.HashMap;


public class chartPanel extends VBox {
    private HashMap<String, Double> income;
    private HashMap<String, Double> expense;

    public chartPanel() {
        this.income = Statistics.getDataForChartOnIncomeArticles();
        this.expense = Statistics.getDataChartOnExpArticles();
    }

    private void initIncomeChartPanel() {
        Button incomesExpences = new Button(Text.get("INCOMES_BY_ARTICLES"));
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(Text.get("CHART_INCOMES"));
        NumberAxis yAxis = new NumberAxis();
        // yAxis.setLabel(Text.get());
        BarChart<String, Number> barIncomes = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> dataExpense1 = new XYChart.Series<String, Number>();
    }
}
