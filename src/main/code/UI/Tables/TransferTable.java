package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Transfer;
import saveLoad.SaveData;
import settings.Format;
import settings.Settings;
import settings.Text;

public class TransferTable extends FinanceTable {


    public TransferTable() {
        this.fillIn();
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.initTable();
    }

    @Override
    public FinanceTable initTable() {
        TableColumn<Transfer, String> columnDates = new TableColumn<>(Text.get("DATE"));
        columnDates.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertyData = new SimpleObjectProperty<>();
                    String dateString = Settings.PARSER_DATE.format(Transfer.getValue().getDate());
                    propertyData.setValue(dateString);
                    return propertyData;
                });
        TableColumn<Transfer, String> columnSource = new TableColumn<>(Text.get("SOURCE"));
        columnSource.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertySource = new SimpleObjectProperty<>();
                    propertySource.setValue(Transfer.getValue().getFromAccount().getTitle());
                    return propertySource;
                });
        TableColumn<Transfer, String> columnTarget = new TableColumn<>(Text.get("TARGET"));
        columnTarget.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertyTarget = new SimpleObjectProperty<>();
                    propertyTarget.setValue(Transfer.getValue().getToAccount().getTitle());
                    return propertyTarget;
                });
        TableColumn<Transfer, String> columnMarkedOff = new TableColumn<>(Text.get("MARKED_OFF"));
        columnMarkedOff.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertyMarkedOff = new SimpleObjectProperty<>();
                    propertyMarkedOff.setValue(Format.amount(Transfer.getValue().getFromAmount(), Transfer.getValue().getFromAccount().getCurrency()));
                    return propertyMarkedOff;
                });
        TableColumn<Transfer, String> columnAccepted = new TableColumn<>(Text.get("ACCEPTED"));
        columnAccepted.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertyAccepted = new SimpleObjectProperty<>();
                    propertyAccepted.setValue(Format.amount(Transfer.getValue().getToAmount(), Transfer.getValue().getToAccount().getCurrency()));
                    return propertyAccepted;
                });
        TableColumn<Transfer, String> columnNotices = new TableColumn<>(Text.get("NOTICE"));
        columnNotices.setCellValueFactory(
                Transfer -> {
                    SimpleObjectProperty<String> propertyNotice = new SimpleObjectProperty<>();
                    propertyNotice.setValue(Transfer.getValue().getNotice());
                    return propertyNotice;
                });
        this.getColumns().addAll(columnDates, columnSource, columnTarget, columnMarkedOff, columnAccepted, columnNotices);
        return this;
    }

    @Override
    public void fillIn() {
        ObservableList<Transfer> transfers = FXCollections.observableArrayList(SaveData.getInstance().getFilterTransfers());
        this.setItems(transfers);
    }

}
