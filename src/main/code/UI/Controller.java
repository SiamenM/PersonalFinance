package UI;

import UI.AddEditWindow.AccountEditDialog;
import UI.AddEditWindow.ArticleAddEditDialog;
import UI.AddEditWindow.TransactionAddEditDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import settings.Text;

import java.io.IOException;

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
    public Button button_statistics_income_on_articles;
    public DatePicker datePicker;


    public void initialize() {
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
        button_statistics_income_on_articles.setText(Text.get("INCOME_ON_ARTICLES"));

    }

    public void pressExit() {
        System.exit(1);
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

    }

    public void pressAddCurrency(ActionEvent event) {

    }
}
