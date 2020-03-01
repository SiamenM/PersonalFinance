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

    private TableColumn<Currency, String> columnTitle;
    private TableColumn<Currency, String> columnCode;
    private TableColumn<Currency, String> columnCourse;
    private TableColumn<Currency, String> columnIsOn;
    TableColumn<Currency, String> columnIsBase;

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
        columnTitle = new TableColumn<>(Text.get("TITLE"));
        columnTitle.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyTitle = new SimpleObjectProperty<>();
                    propertyTitle.setValue(Currency.getValue().getTitle());
                    return propertyTitle;
                });
        columnCode = new TableColumn<>(Text.get("CODE"));
        columnCode.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyCode = new SimpleObjectProperty<>();
                    propertyCode.setValue(Currency.getValue().getCode());
                    return propertyCode;
                });
        columnCourse = new TableColumn<>(Text.get("COURSE"));
        columnCourse.setCellValueFactory(
                Currency -> {
                    SimpleObjectProperty<String> propertyCourse = new SimpleObjectProperty<>();
                    propertyCourse.setValue(String.valueOf(Currency.getValue().getRate()));
                    return propertyCourse;
                });
        columnIsOn = new TableColumn<>(Text.get("ON"));
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
        columnIsBase = new TableColumn<>(Text.get("BASE"));
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

    @Override
    public void refreshTableLanguage() {
        columnTitle.setText(Text.get("TITLE"));
        columnCode.setText(Text.get("CODE"));
        columnCourse.setText(Text.get("COURSE"));
        columnIsOn.setText(Text.get("ON"));
        columnIsBase.setText(Text.get("BASE"));
    }
}
