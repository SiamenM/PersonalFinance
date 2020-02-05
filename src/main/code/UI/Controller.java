package UI;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainClasses.Currency;
import mainClasses.Statistics;
import saveLoad.SaveData;
import settings.Format;
import settings.Settings;
import settings.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Controller {

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
    public Button button_accounts_edit;
    public Button button_accounts_delete;
    public Button button_accounts_add;
    public Button button_articles_add;
    public Button button_articles_edit;
    public Button button_articles_delete;
    public Button button_transactions_add;
    public Button button_transactions_edit;
    public Button button_transactions_delete;
    public Button button_transfers_add;
    public Button button_transfers_edit;
    public Button button_transfers_delete;
    public Button button_currencies_add;
    public Button button_currencies_edit;
    public Button button_currencies_delete;
    public ListView<String> list_balance_currencies;
    public ListView<String> list_finish_balance;
    public VBox vbox_overview;
    public VBox vbox_account;
    public VBox vbox_article;
    public VBox vbox_transaction;
    public VBox vbox_transfers;
    public VBox vbox_currencies;
    public VBox vboxStatistics;


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
        button_accounts_add.setText(Text.get("BUTTON_ADD"));

        button_accounts_edit.setText(Text.get("BUTTON_EDIT"));
        button_accounts_delete.setText(Text.get("BUTTON_DELETE"));
        menu_view_articles.setText(Text.get("MENU_VIEW_ARTICLES"));
        label_articles.setText(Text.get("LABEL_ARTICLES"));
        button_articles_add.setText(Text.get("MENU_EDIT_ADD"));
        button_articles_edit.setText(Text.get("MENU_EDIT_EDIT"));
        button_articles_delete.setText(Text.get("MENU_EDIT_DELETE"));
        menu_view_transactions.setText((Text.get("MENU_VIEW_TRANSACTIONS")));
        label_transactions.setText(Text.get("LABEL_TRANSACTIONS"));
        button_transactions_add.setText(Text.get("MENU_EDIT_ADD"));
        button_transactions_edit.setText(Text.get("MENU_EDIT_EDIT"));
        button_transactions_delete.setText(Text.get("MENU_EDIT_DELETE"));
        menu_view_transfer.setText((Text.get("MENU_VIEW_TRANSFERS")));
        label_transfers.setText(Text.get("LABEL_TRANSFERS"));
        button_transfers_add.setText(Text.get("MENU_EDIT_ADD"));
        button_transfers_edit.setText(Text.get("MENU_EDIT_EDIT"));
        button_transfers_delete.setText(Text.get("MENU_EDIT_DELETE"));
        menu_view_currencies.setText((Text.get("MENU_VIEW_CURRENCIES")));
        label_currencies.setText(Text.get("LABEL_CURRENCIES"));
        button_currencies_add.setText(Text.get("MENU_EDIT_ADD"));
        button_currencies_edit.setText(Text.get("MENU_EDIT_EDIT"));
        button_currencies_delete.setText(Text.get("MENU_EDIT_DELETE"));
        menu_view_statistics.setText((Text.get("MENU_VIEW_STATISTICS")));
        label_statistics.setText(Text.get("LABEL_STATISTICS"));
        vbox_overview.getChildren().add(new TransactionTable(SaveData.getInstance().getTransactionsOnCount(10)).initTable());
        vbox_account.getChildren().add(new AccountsTable(SaveData.getInstance().getAccounts()).initTable());
        vbox_article.getChildren().add(new ArticleTables(SaveData.getInstance().getArticles()).initTable());
        vbox_transfers.getChildren().addAll(new FilterPanel(), new TransferTable(SaveData.getInstance().getTransfers()).initTable());
        vbox_transaction.getChildren().addAll(new FilterPanel(), new TransactionTable(SaveData.getInstance().getFilterTransactions()).initTable());
        vbox_currencies.getChildren().add(new CurrencyTable(SaveData.getInstance().getCurrencies()).initTable());
        vbox_article.setAlignment(Pos.TOP_CENTER);
        vboxStatistics.getChildren().add(new ChartPanel(true));
        initListViewBalanceCurrencyAndFinishBalance();
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

    public void error(ActionEvent event) throws IOException {
        Parent errorDialog = FXMLLoader.load(getClass().getResource("/UI/dialogError/ErrorDialog.fxml"));
        Image iconError = new Image("images/error.png");
        setAndShowStage(errorDialog, iconError);
    }

    private void setAndShowStage(Parent parent, Image icon) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(Text.get("ERROR"));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(button_accounts_add.getScene().getWindow());
        stage.getIcons().add(icon);
        stage.show();
    }

    public void pressAddAccounts(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(button_accounts_add.getScene().getWindow());
        new AccountEditDialog(stage);
    }

    public void pressAddArticle(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(button_accounts_add.getScene().getWindow());
        new ArticleAddEditDialog(stage);
    }

    public void pressAddTransaction(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(button_accounts_add.getScene().getWindow());
        new TransactionAddEditDialog(stage);
    }

    public void pressAddTransfers(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(button_accounts_add.getScene().getWindow());
        new TransferAddEditDialog(stage);
    }

    public void pressAddCurrency(ActionEvent event) {
        Stage stage = new Stage();
        stage.initOwner(button_accounts_add.getScene().getWindow());
        new CurrencyAddEditDialog(stage);
    }

    public void pressMenuNew(ActionEvent event) {
        Settings.setFileSave(null);
        SaveData.getInstance().clear();

    }

    public void pressMenuOpen(ActionEvent event) {

    }

    public void pressMenuSave(ActionEvent event) {

    }

    public void pressMenuRefreshCurrency(ActionEvent event) {
        try {
            SaveData.getInstance().updateCurrencies();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Text.get("ERROR"));
            alert.setHeaderText(null);
            alert.setContentText(Text.get("ERROR_UPDATE_CURRENCIES"));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/images/error.png"));
            alert.showAndWait();
        }
    }

    public void pressExit() {
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
                //обработать сохранение
                System.exit(1);
            } else {
                confirmDialog.close();
            }

        }

    }
}
