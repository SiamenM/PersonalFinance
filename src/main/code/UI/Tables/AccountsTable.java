package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import mainClasses.Account;
import mainClasses.Common;
import saveLoad.SaveData;
import settings.Format;
import settings.Text;


public class AccountsTable extends FinanceTable {

    public AccountsTable() {
        this.fillIn();
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.initTable();
    }

    @Override
    public FinanceTable initTable() {
        TableColumn<Account, String> columnAccounts = new TableColumn<>(Text.get("ACCOUNT"));
        columnAccounts.setCellValueFactory(
                Account -> {
                    SimpleObjectProperty<String> propertyAccount = new SimpleObjectProperty<>();
                    propertyAccount.setValue(Account.getValue().getTitle());
                    return propertyAccount;
                });
        TableColumn<Account, String> columnAmount = new TableColumn<>(Text.get("AMOUNT"));
        columnAmount.setCellValueFactory(
                Account -> {
                    SimpleObjectProperty<String> propertyAmount = new SimpleObjectProperty<>();
                    propertyAmount.setValue(Format.amount(Account.getValue().getAmount(), Account.getValue().getCurrency()));
                    return propertyAmount;
                });
        this.getColumns().addAll(columnAccounts, columnAmount);
        return this;
    }

    @Override
    public void fillIn() {
        ObservableList<Account> accounts = FXCollections.observableArrayList(SaveData.getInstance().getAccounts());
        this.setItems(accounts);
    }

//    @Override
//    public Common getCommon() {
//        TablePosition position = (TablePosition) this.getSelectionModel().getSelectedCells().get(0);
//        int index = position.getRow();
//        Account account = (Account)this.getItems().get(index);
//        return account;
//    }

}
