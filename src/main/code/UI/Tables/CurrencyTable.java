package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Article;
import mainClasses.Currency;
import mainClasses.Transaction;
import settings.Text;

import java.util.List;

public class CurrencyTable extends FinanceTable {

    public CurrencyTable(List<Currency> currencies) {
        ObservableList<Currency> items = FXCollections.observableArrayList(currencies);
        this.setItems(items);
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    public FinanceTable initTable() {
        TableColumn<Currency, String> columnTitle = new TableColumn<>(Text.get("TITLE"));
        columnTitle.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyTitle = new SimpleObjectProperty<>();
                    propertyTitle.setValue(Currency.getValue().getTitle());
                    return propertyTitle;
                });

        TableColumn<Currency, String> columnCode = new TableColumn<>(Text.get("CODE"));
        columnCode.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyCode = new SimpleObjectProperty<>();
                    propertyCode.setValue(Currency.getValue().getCode());
                    return propertyCode;
                });
        TableColumn<Currency, String> columnCourse = new TableColumn<>(Text.get("COURSE"));
        columnCourse.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyCourse = new SimpleObjectProperty<>();
                    propertyCourse.setValue(String.valueOf(Currency.getValue().getRate()));
                    return propertyCourse;
                });
        TableColumn<Currency, String> columnIsOn = new TableColumn<>(Text.get("ON"));
        columnIsOn.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyIsOn = new SimpleObjectProperty<>();
                    if (Currency.getValue().isOn()) {
                        propertyIsOn.setValue("+");
                    } else {
                        propertyIsOn.setValue("-");
                    }
                    return propertyIsOn;
                });
        TableColumn<Currency, String> columnIsBase = new TableColumn<>(Text.get("BASE"));
        columnIsBase.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyIsBase = new SimpleObjectProperty<>();
                    if (Currency.getValue().isBase()) {
                        propertyIsBase.setValue("+");
                    } else {
                        propertyIsBase.setValue("-");
                    }
                    return propertyIsBase;
                });
        this.getColumns().addAll(columnTitle, columnCode, columnCourse, columnIsOn, columnIsBase);
        return this;
    }
}
