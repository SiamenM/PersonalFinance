package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Transaction;
import saveLoad.SaveData;
import settings.Format;
import settings.Settings;
import settings.Text;

public class TransactionTable extends FinanceTable {

    private boolean isOverview;
    private boolean isFiltered;
    private TableColumn<Transaction, String> columnDates;
    private TableColumn<Transaction, String> columnAccounts;
    private TableColumn<Transaction, String> columnArticles;
    private TableColumn<Transaction, String> columnAmount;
    private TableColumn<Transaction, String> columnNotices;

    @Override
    public FinanceTable<Transaction> initTable() {
        columnDates = new TableColumn<>(Text.get("DATE"));
        columnDates.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyData = new SimpleObjectProperty<>();
                    String dateString = Settings.PARSER_DATE.format(Transaction.getValue().getDate());
                    propertyData.setValue(dateString);
                    return propertyData;
                });
        columnAccounts = new TableColumn<>(Text.get("ACCOUNT"));
        columnAccounts.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyAccount = new SimpleObjectProperty<>();
                    propertyAccount.setValue(Transaction.getValue().getAccount().getTitle());
                    return propertyAccount;
                });
        columnArticles = new TableColumn<>(Text.get("ARTICLE"));
        columnArticles.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyArticles = new SimpleObjectProperty<>();
                    propertyArticles.setValue(Transaction.getValue().getArticle().getTitle());
                    return propertyArticles;
                });
        columnAmount = new TableColumn<>(Text.get("AMOUNT"));
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
        columnNotices = new TableColumn<>(Text.get("NOTICE"));
        columnNotices.setCellValueFactory(
                Transaction -> {
                    SimpleObjectProperty<String> propertyNotice = new SimpleObjectProperty<>();
                    propertyNotice.setValue(Transaction.getValue().getNotice());
                    return propertyNotice;
                });
        this.getColumns().addAll(columnDates, columnAccounts, columnAmount, columnArticles, columnNotices);
        return this;
    }

    public TransactionTable(boolean isOverview) {
        this.isOverview = isOverview;
        this.isFiltered = !isOverview;
        this.fillIn();
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.initTable();
    }

    public void fillIn() {
        ObservableList<Transaction> transactions;
        if (isFiltered) {
            transactions = FXCollections.observableArrayList(SaveData.getInstance().getFilterTransactions());
        } else {
            transactions = initItems();
        }
        this.setItems(transactions);
    }

    @Override
    public void refreshTableLanguage() {
        columnDates.setText(Text.get("DATE"));
        columnAccounts.setText(Text.get("ACCOUNT"));
        columnArticles.setText(Text.get("ARTICLE"));
        columnAmount.setText(Text.get("AMOUNT"));
        columnNotices.setText(Text.get("NOTICE"));
    }

    private ObservableList<Transaction> initItems() {
        if (isOverview) {
            return FXCollections.observableArrayList(SaveData.getInstance().getTransactionsOnCount(10));
        } else {
            return FXCollections.observableArrayList(SaveData.getInstance().getTransactions());
        }
    }

}
