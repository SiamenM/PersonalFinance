package UI;

import UI.AddEditDeletePanel.*;
import UI.FilterPanel.FilterPanel;
import UI.Tables.*;
import UI.chartPanel.ChartPanel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private SingleSelectionModel<Tab> selectionModel;

    @FXML
    public TabPane tabPane;
    public MenuItem menuNew;
    public MenuItem menuOpen;
    public MenuItem menuSave;
    public MenuItem menuRefresh;
    public MenuItem menuClose;
    public MenuItem menuOverview;
    public MenuItem menuAccounts;
    public MenuItem menuArticles;
    public MenuItem menuTransaction;
    public MenuItem menuTransfer;
    public MenuItem menuCurrencies;
    public MenuItem menuStatistics;
    public MenuItem menuLanguage;
    public MenuItem menuAboutProgram;
    public Menu menuFile;
    public Menu menuView;
    public Menu menuProperties;
    public Menu menuHelp;
    public Tab menuViewOverview;
    public Tab menuViewAccounts;
    public Tab menuViewArticles;
    public Tab menuViewTransactions;
    public Tab menuViewTransfer;
    public Tab menuViewCurrencies;
    public Tab menuViewStatistics;
    public Label labelLastTransactions;
    public Label labelAccounts;
    public Label labelArticles;
    public Label labelTransactions;
    public Label labelTransfers;
    public Label labelCurrencies;
    public Label labelStatistics;
    public Label labelFinishBalance;
    public Label labelCurrencyBalance;
    public ListView<String> listBalanceCurrencies;
    public ListView<String> listFinishBalance;
    public VBox vboxOverview;
    public VBox vboxAccount;
    public VBox vboxArticle;
    public VBox vboxTransaction;
    public VBox vboxTransfers;
    public VBox vboxCurrencies;
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
    }

    public void initialize() {
        labelCurrencyBalance.setText(Text.get("BALANCE_CURRENCY"));
        labelFinishBalance.setText(Text.get("FINISH_BALANCE"));
        menuFile.setText(Text.get("MENU_FILE"));
        menuView.setText(Text.get("MENU_VIEW"));
        menuProperties.setText(Text.get("MENU_PROPERTIES"));
        menuHelp.setText(Text.get("MENU_HELP"));

        menuNew.setText(Text.get("MENU_FILE_NEW"));
        menuOpen.setText(Text.get("MENU_FILE_OPEN"));
        menuSave.setText(Text.get("MENU_FILE_SAVE"));
        menuRefresh.setText(Text.get("MENU_FILE_UPDATE_CURRENCIES"));
        menuClose.setText(Text.get("MENU_FILE_EXIT"));

        menuOverview.setText(Text.get("MENU_VIEW_OVERVIEW"));
        labelLastTransactions.setText(Text.get("LABEL_LAST_TRANSACTIONS"));
        menuAccounts.setText(Text.get("MENU_VIEW_ACCOUNTS"));
        menuArticles.setText(Text.get("MENU_VIEW_ARTICLES"));
        menuTransaction.setText(Text.get("MENU_VIEW_TRANSACTIONS"));
        menuTransfer.setText(Text.get("MENU_VIEW_TRANSFERS"));

        menuCurrencies.setText(Text.get("MENU_VIEW_CURRENCIES"));
        menuStatistics.setText(Text.get("MENU_VIEW_STATISTICS"));

        menuLanguage.setText(Text.get("MENU_PROPERTIES_LANGUAGE"));
        menuAboutProgram.setText(Text.get("MENU_HELP_ABOUT"));
        menuViewOverview.setText((Text.get("MENU_VIEW_OVERVIEW")));

        menuViewAccounts.setText((Text.get("MENU_VIEW_ACCOUNTS")));
        labelAccounts.setText((Text.get("LABEL_ACCOUNTS")));

        menuViewArticles.setText(Text.get("MENU_VIEW_ARTICLES"));
        labelArticles.setText(Text.get("LABEL_ARTICLES"));
        menuViewTransactions.setText((Text.get("MENU_VIEW_TRANSACTIONS")));
        labelTransactions.setText(Text.get("LABEL_TRANSACTIONS"));
        menuViewTransfer.setText((Text.get("MENU_VIEW_TRANSFERS")));
        labelTransfers.setText(Text.get("LABEL_TRANSFERS"));
        menuViewStatistics.setText((Text.get("MENU_VIEW_STATISTICS")));
        menuViewCurrencies.setText((Text.get("MENU_VIEW_CURRENCIES")));
        labelCurrencies.setText(Text.get("LABEL_CURRENCIES"));
        labelStatistics.setText(Text.get("LABEL_STATISTICS"));
        vboxOverview.getChildren().add(transactionTableOverview);
        vboxAccount.getChildren().addAll(new AccountAddDeletePanel(this), accountsTable);
        vboxArticle.getChildren().addAll(new ArticleAddDeletePanel(this), articleTable);
        vboxTransfers.getChildren().addAll(new TransferAddDeletePanel(this), new FilterPanel(transferTable), transferTable);
        vboxTransaction.getChildren().addAll(new TransactionAddDeletePanel(this), new FilterPanel(transactionTable), transactionTable);
        vboxCurrencies.getChildren().addAll(new CurrencyAddDeletePanel(this), currencyTable);
        vboxArticle.setAlignment(Pos.TOP_CENTER);
        vboxStatistics.getChildren().add(new ChartPanel(true));
        initListViewBalanceCurrencyAndFinishBalance();
        this.selectionModel = tabPane.getSelectionModel();
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
        listBalanceCurrencies.setPrefHeight(currencies.size() * 25);
        listBalanceCurrencies.setItems(FXCollections.observableArrayList(currencies));
        listFinishBalance.setPrefHeight(finishBalance.size() * 25);
        listFinishBalance.setItems(FXCollections.observableArrayList(finishBalance));
    }

    public void pressAbout() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle(Text.get("MENU_HELP_ABOUT"));
        about.setGraphic(new ImageView("/images/main.png"));
        about.setHeaderText(Text.get("LABEL_NAME_PROGRAM"));
        about.setContentText(Text.get("LABEL_DESCRIPTION_PROGRAM"));
        Stage stage = (Stage) about.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/main.png"));
        about.showAndWait();
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

    public void pressMenuSave() {
        if (Settings.getFileSave() == null) {
            Stage stage = new Stage();
            stage.centerOnScreen();
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(labelLastTransactions.getScene().getWindow());
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

    public void pressMenuRefreshCurrency() {
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

    public void pressExit() {
        exitProgram();
    }

    void exitProgram() {
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
                pressMenuSave();
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

    public void clickMenuOverview() {
        selectionModel.select(menuViewOverview);
    }

    public void clickMenuAccounts() {
        selectionModel.select(menuViewAccounts);
    }

    public void clickMenuArticles() {
        selectionModel.select(menuViewArticles);
    }

    public void clickMenuTransaction() {
        selectionModel.select(menuViewTransactions);
    }

    public void clickMenuTransfer() {
        selectionModel.select(menuViewTransfer);
    }

    public void clickMenuCurrencies() {
        selectionModel.select(menuViewCurrencies);
    }

    public void clickMenuStatistics() {
        selectionModel.select(menuViewStatistics);
    }

}
