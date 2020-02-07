package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Currency;
import saveLoad.SaveData;
import settings.Text;

import java.util.Comparator;

public class CurrencyTable extends FinanceTable {

    public CurrencyTable() {
        this.fillIn();
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.setSortPolicy(t -> {
            Comparator<Currency> comparator = new Comparator<Currency>() {
                @Override
                public int compare(Currency currency1, Currency currency2) {
                    if (currency1.isBase()) {
                        return 0;
                    }
                    if (currency1.isOn() && currency2.isOn() || !currency1.isOn() && !currency2.isOn()) {
                        return currency1.getTitle().compareToIgnoreCase(currency2.getTitle());
                    } else if (currency1.isOn() && !currency2.isOn()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            };
            FXCollections.sort(this.getItems(), comparator);
            return true;
        });
        this.initTable();
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

    @Override
    public void fillIn() {
        ObservableList<Currency> currencies = FXCollections.observableArrayList(SaveData.getInstance().getCurrencies());
        this.setItems(currencies);
    }
}
