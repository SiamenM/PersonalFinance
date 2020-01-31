package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Transaction;
import settings.Format;
import settings.Settings;
import settings.Text;

import java.util.List;

public class TransactionTable extends FinanceTable {

    public TransactionTable(List<Transaction> transactions) {
        ObservableList<Transaction> items = FXCollections.observableArrayList(transactions);
        this.setItems(items);
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
    }



    @Override
    public FinanceTable<Transaction> initTable() {
        TableColumn<Transaction, String> columnDates = new TableColumn<>(Text.get("DATE"));
        columnDates.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyData = new SimpleObjectProperty<>();
                    String dateString = Settings.PARSER_DATE.format(Transaction.getValue().getDate());
                    propertyData.setValue(dateString);
                    return propertyData;
                });

        TableColumn<Transaction, String> columnAccounts = new TableColumn<>(Text.get("ACCOUNT"));
        columnAccounts.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyAccount = new SimpleObjectProperty<>();
                    propertyAccount.setValue(Transaction.getValue().getAccount().getTitle());
                    return propertyAccount;
                });

        TableColumn<Transaction, String> columnArticles = new TableColumn<>(Text.get("ARTICLE"));
        columnArticles.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyArticles = new SimpleObjectProperty<>();
                    propertyArticles.setValue(Transaction.getValue().getArticle().getTitle());
                    return propertyArticles;
                });

        TableColumn<Transaction, String> columnAmount = new TableColumn<>(Text.get("AMOUNT"));
        columnAmount.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyAmount = new SimpleObjectProperty<>();
                    double amount = Transaction.getValue().getAmount();
                    if (amount > 0) {
                        propertyAmount.setValue("+" + Format.amount(amount, Transaction.getValue().getAccount().getCurrency()));
                    } else {
                        propertyAmount.setValue(Format.amount(amount, Transaction.getValue().getAccount().getCurrency()));
                    }
                    return propertyAmount;
                }
        );

        TableColumn<Transaction, String> columnNotices = new TableColumn<>(Text.get("NOTICE"));
        columnNotices.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyNotice = new SimpleObjectProperty<>();
                    propertyNotice.setValue(Transaction.getValue().getNotice());
                    return propertyNotice;
                });
        this.getColumns().addAll(columnDates, columnAccounts, columnAmount, columnArticles, columnNotices);
        return this;
    }

}
