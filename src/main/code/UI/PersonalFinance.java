package UI;

import financeException.ModelException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mainClasses.*;
import saveLoad.SaveData;
import settings.Settings;
import settings.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PersonalFinance extends Application {


    public static void main(String[] args) throws Exception {
//        testModel();
//          SaveData saveData = SaveData.getInstance();
//        saveData.updateCurrencies();
        initialization();
        launch();

        //System.out.println(saveData);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/PersonalFinance.fxml"));
        primaryStage.setScene(new Scene(root));

        String stylesheet = getClass().getResource("/UI/Style.css").toExternalForm();
        root.getStylesheets().add(stylesheet);
        primaryStage.setTitle(Text.get("PROGRAM_NAME"));
        primaryStage.setResizable(false);
        Image iconMain = new Image("images/main.png");
        primaryStage.getIcons().add(iconMain);

        primaryStage.show();

    }

    private static void testModel() throws ModelException {
        Currency c1 = new Currency("Белорусский рубль", "BLR", 1, true, true);
        Currency c2 = new Currency("Доллар США", "USD", 65, true, false);
        Currency c3 = new Currency("Евро", "EUR", 75, false, false);
        Currency c4 = new Currency("Гривна", "UAH", 2.5, false, false);

        Account ac1 = new Account("Кошелёк", c1, 1000);
        Account ac2 = new Account("VISA", c1, 0);
        Account ac3 = new Account("deposit in rub", c1, 10000);
        Account ac4 = new Account("deposit in usd", c2, 0);

        Article article1 = new Article("Food");
        Article article2 = new Article("Home");
        Article article3 = new Article("Salary");
        Article article4 = new Article("Cafe");
        Article article5 = new Article("Percents of deposit");

        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(c1);
        currencies.add(c2);
        currencies.add(c3);
        currencies.add(c4);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(ac1);
        accounts.add(ac2);
        accounts.add(ac3);
        accounts.add(ac4);

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(ac2, article3, 30000));
        transactions.add(new Transaction(ac2, article1, -1500, "Buy some food"));
        transactions.add(new Transaction(ac1, article2, -5500, "Home"));
        transactions.add(new Transaction(ac1, article2, -4000, "Second home"));
        transactions.add(new Transaction(ac3, article5, +1000));
        transactions.add(new Transaction(ac2, article3, 25000, new Date((new Date()).getTime() - (long) 86400000 * 30)));
        transactions.add(new Transaction(ac3, article5, 1000, new Date((new Date().getTime() - (long) 86400000 * 30))));

        for (int i = 0; i < 50; i++) {
            Article tempArticle;
            Account tempAccount;
            tempArticle = article1;
            if (Math.random() < 0.5) {
                tempAccount = ac1;
            } else {
                tempAccount = ac2;
            }
            double tempAmount = Math.round(Math.random() * (-1000));
            Date tempDate = new Date((long) (new Date().getTime() - (long) 86400000 * 30 * Math.random()));
            transactions.add(new Transaction(tempAccount, tempArticle, tempAmount, tempDate));
        }

        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(ac2, ac1, 25000, 25000));
        transfers.add(new Transfer(ac2, ac3, 3000, 3000));
        transfers.add(new Transfer(ac2, ac4, 6000, 90));

        for (Account a : accounts) {
            a.setAmountTransactionAndTransfers(transactions, transfers);
        }
        SaveData sd = SaveData.getInstance();
        sd.setArticles(articles);
        sd.setCurrencies(currencies);
        sd.setAccounts(accounts);
        sd.setTransactions(transactions);
        sd.setTransfers(transfers);
        sd.save();
        //sd.load();
        System.out.println(sd);
    }

    private static void initialization() {
        Settings.init();
        Text.init();
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        try {
//            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Settings.FONT_KORNILOW));
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
    }

}
