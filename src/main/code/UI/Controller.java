package UI;

import UI.AddEditDeletePanel.*;
import UI.FilterPanel.FilterPanel;
import UI.Tables.*;
import UI.chartPanel.ChartPanel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    private AccountAddDeletePanel accountAddDeletePanel;
    private ArticleAddDeletePanel articleAddDeletePanel;
    private TransferAddDeletePanel transferAddDeletePanel;
    private TransactionAddDeletePanel transactionAddDeletePanel;
    private CurrencyAddDeletePanel currencyAddDeletePanel;
    private FilterPanel transferFilterPanel;
    private FilterPanel transactionFilterPanel;
    private ChartPanel chartPanel;

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
    public MenuItem menuAboutProgram;
    public Menu menuFile;
    public Menu menuView;
    public Menu menuProperties;
    public Menu menuLanguage;
    public MenuItem menuRussian;
    public MenuItem menuEnglish;
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
        accountAddDeletePanel = new AccountAddDeletePanel(this);
        articleAddDeletePanel = new ArticleAddDeletePanel(this);
        transferAddDeletePanel = new TransferAddDeletePanel(this);
        transactionAddDeletePanel = new TransactionAddDeletePanel(this);
        currencyAddDeletePanel = new CurrencyAddDeletePanel(this);
        transferFilterPanel = new FilterPanel(transferTable);
        transactionFilterPanel = new FilterPanel(transactionTable);
        chartPanel = new ChartPanel(this,true);
        tables = new LinkedList<>();
        tables.add(transactionTableOverview);
        tables.add(accountsTable);
        tables.add(articleTable);
        tables.add(transactionTable);
        tables.add(transferTable);
        tables.add(currencyTable);
    }

    public void initialize() {
        setComponentsName();
        vboxOverview.getChildren().add(transactionTableOverview);
        vboxAccount.getChildren().addAll(accountAddDeletePanel, accountsTable);
        vboxArticle.getChildren().addAll(articleAddDeletePanel, articleTable);
        vboxTransfers.getChildren().addAll(transferAddDeletePanel, transferFilterPanel, transferTable);
        vboxTransaction.getChildren().addAll(transactionAddDeletePanel, transactionFilterPanel, transactionTable);
        vboxCurrencies.getChildren().addAll(currencyAddDeletePanel, currencyTable);
        vboxStatistics.getChildren().addAll(chartPanel);
        initListViewBalanceCurrencyAndFinishBalance();
        this.selectionModel = tabPane.getSelectionModel();
    }

    private void setComponentsName() {
        labelCurrencyBalance.setText(Text.get("BALANCE_CURRENCY"));
        labelFinishBalance.setText(Text.get("FINISH_BALANCE"));
        menuFile.setText(Text.get("MENU_FILE"));
        menuView.setText(Text.get("MENU_VIEW"));
        menuProperties.setText(Text.get("MENU_PROPERTIES"));
        menuLanguage.setText(Text.get("LANGUAGE"));
        menuRussian.setText(Text.get("RUSSIAN"));
        menuEnglish.setText(Text.get("ENGLISH"));
        menuHelp.setText(Text.get("MENU_HELP"));
        menuNew.setText(Text.get("MENU_FILE_NEW"));
        menuOpen.setText(Text.get("OPEN"));
        menuSave.setText(Text.get("SAVE"));
        menuRefresh.setText(Text.get("MENU_FILE_UPDATE_CURRENCIES"));
        menuClose.setText(Text.get("MENU_FILE_EXIT"));

        menuOverview.setText(Text.get("MENU_VIEW_OVERVIEW"));
        labelLastTransactions.setText(Text.get("LABEL_LAST_TRANSACTIONS"));
        menuAccounts.setText(Text.get("ACCOUNTS"));
        menuArticles.setText(Text.get("ARTICLES"));
        menuTransaction.setText(Text.get("TRANSACTIONS"));
        menuTransfer.setText(Text.get("TRANSFERS"));

        menuCurrencies.setText(Text.get("CURRENCIES"));
        menuStatistics.setText(Text.get("STATISTICS"));

        menuLanguage.setText(Text.get("MENU_PROPERTIES_LANGUAGE"));
        menuAboutProgram.setText(Text.get("MENU_HELP_ABOUT"));
        menuViewOverview.setText((Text.get("MENU_VIEW_OVERVIEW")));

        menuViewAccounts.setText((Text.get("ACCOUNTS")));
        labelAccounts.setText((Text.get("ACCOUNTS")));

        menuViewArticles.setText(Text.get("ARTICLES"));
        labelArticles.setText(Text.get("ARTICLES"));
        menuViewTransactions.setText((Text.get("TRANSACTIONS")));
        labelTransactions.setText(Text.get("TRANSACTIONS"));
        menuViewTransfer.setText((Text.get("TRANSFERS")));
        labelTransfers.setText(Text.get("TRANSFERS"));
        menuViewStatistics.setText((Text.get("STATISTICS")));
        menuViewCurrencies.setText((Text.get("CURRENCIES")));
        labelCurrencies.setText(Text.get("CURRENCIES"));
        labelStatistics.setText(Text.get("STATISTICS"));
    }

    public void refreshTables() {
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

    public void pressMenuNew() {
        Settings.setFileSave(null);
        SaveData.getInstance().clear();
        refreshTables();
        initListViewBalanceCurrencyAndFinishBalance();
    }

    public void pressMenuOpen() {
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
            initListViewBalanceCurrencyAndFinishBalance();
        }
    }

    public void pressMenuSave() {
        if (Settings.getFileSave() == null) {
            Stage stage = new Stage();
            stage.centerOnScreen();
            //stage.setScene(labelLastTransactions.getScene());
            stage.initOwner(this.labelLastTransactions.getScene().getWindow());
            // setParentWindowFromController(stage);
            //  stage.initOwner(labelLastTransactions.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
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
        } else {
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
            } else if (option.get() == cancel) {
                confirmDialog.close();
            }
        }
    }

    public void setParentWindowFromController(Stage stage) {
        stage.initOwner(labelLastTransactions.getScene().getWindow());
    }

    public void setChartPanel(ChartPanel chartPanel){
        this.chartPanel = chartPanel;
    }
    private void changeLanguage(String language) {
        if (Settings.getProgramLanguage().equals(language)) {
            return;
        }
        Settings.setProgramLanguage(language);
        Text.init();
        setComponentsName();
        accountAddDeletePanel.refreshButtonsName();
        articleAddDeletePanel.refreshButtonsName();
        transferAddDeletePanel.refreshButtonsName();
        transactionAddDeletePanel.refreshButtonsName();
        currencyAddDeletePanel.refreshButtonsName();
        transferFilterPanel.refreshStepButtonName();
        transactionFilterPanel.refreshStepButtonName();
        chartPanel.refreshChartPanelLanguage();
        for (FinanceTable table : tables) {
            table.refreshTableLanguage();
        }
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

    public void clickMenuEnglish() {
        changeLanguage("en");
    }

    public void clickMenuRussian() {
        changeLanguage("ru");
    }
}
