package UI;

import UI.AddEditDeletePanel.*;
import UI.AddEditWindow.*;
import UI.FilterPanel.FilterPanel;
import UI.Tables.*;
import UI.chartPanel.ChartPanel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainClasses.Common;
import mainClasses.Currency;
import mainClasses.Statistics;
import org.xml.sax.SAXException;
import saveLoad.SaveData;
import settings.Format;
import settings.Settings;
import settings.Text;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private TransactionTable transactionTableOverview;
    private AccountsTable accountsTable;
    private ArticleTables articleTable;
    private TransactionTable transactionTable;
    private TransferTable transferTable;
    private CurrencyTable currencyTable;
    private List<FinanceTable> tables;
    //private AddEditDeletePanel addEditDeletePanel;
    public Stage stage;

    @FXML
    public MenuItem menu_new;
    public MenuItem menu_open;
    public MenuItem menu_save;
    public MenuItem menu_refresh;
    public MenuItem menu_close;
    public MenuItem menu_add;
    public MenuItem menu_change;
    public MenuItem menu_overview;
    public MenuItem menu_accounts;
    public MenuItem menu_articles;
    public MenuItem menu_transaction;
    public MenuItem menu_transfer;
    public MenuItem menu_currencies;
    public MenuItem menu_statistics;
    public MenuItem menu_language;
    public MenuItem menu_about_program;
    public MenuItem menu_delete;
    public Menu menu_file;
    public Menu menu_edit;
    public Menu menu_view;
    public Menu menu_properties;
    public Menu menu_help;
    public Tab menu_view_overview;
    public Tab menu_view_accounts;
    public Tab menu_view_articles;
    public Tab menu_view_transactions;
    public Tab menu_view_transfer;
    public Tab menu_view_currencies;
    public Tab menu_view_statistics;
    public Label label_last_transactions;
    public Label label_accounts;
    public Label label_articles;
    public Label label_transactions;
    public Label label_transfers;
    public Label label_currencies;
    public Label label_statistics;
    public Label label_finish_balance;
    public Label label_currency_balance;
    public ListView<String> list_balance_currencies;
    public ListView<String> list_finish_balance;
    public VBox vbox_overview;
    public VBox vbox_account;
    public VBox vbox_article;
    public VBox vbox_transaction;
    public VBox vbox_transfers;
    public VBox vbox_currencies;
    public VBox vboxStatistics;

    public Controller() {
        transactionTableOverview = new TransactionTable(true);
        accountsTable = new AccountsTable();
        articleTable = new ArticleTables();
        transactionTable = new TransactionTable(false);
        transferTable = new TransferTable();
        currencyTable = new CurrencyTable();
        tables = new LinkedList<>();
        tables.add(transactionTableOverview);
        tables.add(accountsTable);
        tables.add(articleTable);
        tables.add(transactionTable);
        tables.add(transferTable);
        tables.add(currencyTable);
        stage = new Stage();
        //this.addEditDeletePanel = new AddEditDeletePanel(this);
    }

    public void initialize() {
        label_currency_balance.setText(Text.get("BALANCE_CURRENCY"));
        label_finish_balance.setText(Text.get("FINISH_BALANCE"));
        menu_file.setText(Text.get("MENU_FILE"));
        menu_edit.setText(Text.get("MENU_EDIT"));
        menu_view.setText(Text.get("MENU_VIEW"));
        menu_properties.setText(Text.get("MENU_PROPERTIES"));
        menu_help.setText(Text.get("MENU_HELP"));

        menu_new.setText(Text.get("MENU_FILE_NEW"));
        menu_open.setText(Text.get("MENU_FILE_OPEN"));
        menu_save.setText(Text.get("MENU_FILE_SAVE"));
        menu_refresh.setText(Text.get("MENU_FILE_UPDATE_CURRENCIES"));
        menu_close.setText(Text.get("MENU_FILE_EXIT"));

        menu_add.setText(Text.get("MENU_EDIT_ADD"));
        menu_change.setText(Text.get("MENU_EDIT_EDIT"));
        menu_delete.setText(Text.get("MENU_EDIT_DELETE"));
        menu_overview.setText(Text.get("MENU_VIEW_OVERVIEW"));
        label_last_transactions.setText(Text.get("LABEL_LAST_TRANSACTIONS"));
        menu_accounts.setText(Text.get("MENU_VIEW_ACCOUNTS"));
        menu_articles.setText(Text.get("MENU_VIEW_ARTICLES"));
        menu_transaction.setText(Text.get("MENU_VIEW_TRANSACTIONS"));
        menu_transfer.setText(Text.get("MENU_VIEW_TRANSFERS"));

        menu_currencies.setText(Text.get("MENU_VIEW_CURRENCIES"));
        menu_statistics.setText(Text.get("MENU_VIEW_STATISTICS"));

        menu_language.setText(Text.get("MENU_PROPERTIES_LANGUAGE"));
        menu_about_program.setText(Text.get("MENU_HELP_ABOUT"));
        menu_view_overview.setText((Text.get("MENU_VIEW_OVERVIEW")));


        menu_view_accounts.setText((Text.get("MENU_VIEW_ACCOUNTS")));
        label_accounts.setText((Text.get("LABEL_ACCOUNTS")));

        menu_view_articles.setText(Text.get("MENU_VIEW_ARTICLES"));
        label_articles.setText(Text.get("LABEL_ARTICLES"));
        menu_view_transactions.setText((Text.get("MENU_VIEW_TRANSACTIONS")));
        label_transactions.setText(Text.get("LABEL_TRANSACTIONS"));
        menu_view_transfer.setText((Text.get("MENU_VIEW_TRANSFERS")));
        label_transfers.setText(Text.get("LABEL_TRANSFERS"));
        menu_view_statistics.setText((Text.get("MENU_VIEW_STATISTICS")));
        menu_view_currencies.setText((Text.get("MENU_VIEW_CURRENCIES")));
        label_currencies.setText(Text.get("LABEL_CURRENCIES"));
        label_statistics.setText(Text.get("LABEL_STATISTICS"));
        vbox_overview.getChildren().add(transactionTableOverview);
        vbox_account.getChildren().addAll(new AccountAddDeletePanel(this), accountsTable);
        vbox_article.getChildren().addAll(new ArticleAddDeletePanel(this), articleTable);
        vbox_transfers.getChildren().addAll(new TransferAddDeletePanel(this), new FilterPanel(), transferTable);
        vbox_transaction.getChildren().addAll(new TransactionAddDeletePanel(this), new FilterPanel(), transactionTable);
        vbox_currencies.getChildren().addAll(new CurrencyAddDeletePanel(this), currencyTable);
        vbox_article.setAlignment(Pos.TOP_CENTER);
        vboxStatistics.getChildren().add(new ChartPanel(true));
        initListViewBalanceCurrencyAndFinishBalance();

    }

    private void refreshTables() {
        for (FinanceTable table : tables) {
            table.fillIn();
        }
    }

    public void initListViewBalanceCurrencyAndFinishBalance() {
        List<String> currencies = new LinkedList<>();
        List<String> finishBalance = new LinkedList<>();
        for (Currency currency : SaveData.getInstance().getEnableCurrencies()) {
            currencies.add(currency.getTitle() + " " + Statistics.getBalanceCurrency(currency));
            finishBalance.add(currency.getTitle() + " " + Format.amount(Statistics.getBalance(currency)));
        }
        list_balance_currencies.setPrefHeight(currencies.size() * 25);
        list_balance_currencies.setItems(FXCollections.observableArrayList(currencies));
        list_finish_balance.setPrefHeight(finishBalance.size() * 25);
        list_finish_balance.setItems(FXCollections.observableArrayList(finishBalance));

    }


    public void pressAbout(ActionEvent event) throws IOException {
        Parent aboutRoot = FXMLLoader.load(getClass().getResource("/UI/about/About.fxml"));
        Image iconAbout = new Image("images/about.png");
        setAndShowStage(aboutRoot, iconAbout);

    }

    private void setAndShowStage(Parent parent, Image icon) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(Text.get("ERROR"));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label_last_transactions.getScene().getWindow());
        stage.getIcons().add(icon);
        stage.show();
    }

    public void pressMenuNew(ActionEvent event) {
        Settings.setFileSave(null);
        SaveData.getInstance().clear();
        refreshTables();
        initListViewBalanceCurrencyAndFinishBalance();
    }

    public void pressMenuOpen(ActionEvent event) {
        Stage stage = new Stage();
        stage.centerOnScreen();
        FileChooser fileChooserOpen = new FileChooser();
        fileChooserOpen.setTitle(Text.get("OPEN"));
        fileChooserOpen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("myfin", "*.myfin"));
        fileChooserOpen.setInitialDirectory(new File("src/saves"));
        File selectedFile = fileChooserOpen.showOpenDialog(stage);
        if (selectedFile != null) {
            Settings.setFileSave(selectedFile);
            SaveData.getInstance().clear();
            SaveData.getInstance().load();
            refreshTables();
        }
    }

    public void pressMenuSave(ActionEvent event) {
        if (Settings.getFileSave() == null) {
            Stage stage = new Stage();
            stage.centerOnScreen();
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(label_last_transactions.getScene().getWindow());
            FileChooser fileChooserSave = new FileChooser();

            fileChooserSave.setTitle(Text.get("SAVE"));
            fileChooserSave.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("myfin", "*.myfin"));
            fileChooserSave.setInitialDirectory(new File("src/saves"));
            File selectedFile = fileChooserSave.showSaveDialog(stage);
            if (selectedFile != null) {
                String pathFile = selectedFile.getAbsolutePath();
                Settings.setFileSave(new File(pathFile));
            }
            stage.close();
        }
        if (Settings.getFileSave() != null) {
            SaveData.getInstance().save();
        }
    }

    public void pressMenuRefreshCurrency(ActionEvent event) {
        try {
            SaveData.getInstance().updateCurrencies();
            initListViewBalanceCurrencyAndFinishBalance();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            showAlert(Text.get("ERROR_UPDATE_CURRENCIES"));
        }
    }

    public static void showAlert(String errorCode) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Text.get("ERROR"));
        alert.setHeaderText(null);
        alert.setContentText(errorCode);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/error.png"));
        alert.showAndWait();
    }


    public void pressExit(ActionEvent event) {
        if (SaveData.getInstance().isSaved()) {
            System.exit(1);
        } else {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle(Text.get("CONFIRM_EXIT_TITLE"));
            confirmDialog.setHeaderText(Text.get("CONFIRM_EXIT_TEXT"));
            Stage stage = (Stage) confirmDialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/images/question.png"));
            ButtonType exit = new ButtonType(Text.get("EXIT"));
            ButtonType saveAndExit = new ButtonType(Text.get("SAVE_AND_EXIT"));
            ButtonType cancel = new ButtonType(Text.get("CANCEL"));
            confirmDialog.getButtonTypes().clear();
            confirmDialog.getButtonTypes().addAll(exit, saveAndExit, cancel);
            Optional<ButtonType> option = confirmDialog.showAndWait();
            if (option.get() == exit) {
                System.exit(1);
            } else if (option.get() == saveAndExit) {
                pressMenuSave(event);
                System.exit(1);
            } else {
                confirmDialog.close();
            }
        }
    }

    public TransactionTable getTransactionTableOverview() {
        return transactionTableOverview;
    }

    public AccountsTable getAccountsTable() {
        return accountsTable;
    }

    public ArticleTables getArticleTable() {
        return articleTable;
    }

    public TransactionTable getTransactionTable() {
        return transactionTable;
    }

    public TransferTable getTransferTable() {
        return transferTable;
    }

    public CurrencyTable getCurrencyTable() {
        return currencyTable;
    }

}
