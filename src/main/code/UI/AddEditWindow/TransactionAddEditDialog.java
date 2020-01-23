package UI.AddEditWindow;

import financeException.ModelException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Account;
import mainClasses.Article;
import mainClasses.Common;
import mainClasses.Transaction;
import saveLoad.SaveData;
import settings.Format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TransactionAddEditDialog extends AddEditWindow {
    private SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

    public TransactionAddEditDialog(Stage stage) {
        super(stage);
    }

    @Override
    protected void init() {
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        components.put("DATE", datePicker);
        components.put("AMOUNT", new TextField());
        components.put("NOTICE", new TextField());
        components.put("ACCOUNT", initComboBox(SaveData.getInstance().getAccounts()));
        components.put("ARTICLE", initComboBox(SaveData.getInstance().getArticles()));
        images.put("DATE", Style.ICON_DATE);
        images.put("ACCOUNT", Style.ICON_ACCOUNT);
        images.put("ARTICLE", Style.ICON_ARTICLE);
        images.put("AMOUNT", Style.ICON_START_BALANCE);
        images.put("NOTICE", Style.ICON_NOTICE);

        values.put("AMOUNT", Format.amount(0));
    }

    @Override
    protected void setValues() {
        Transaction transaction = (Transaction) common;
        values.put("DATE", transaction.getDate());
        values.put("ACCOUNT", transaction.getAccount());
        values.put("ARTICLE", transaction.getArticle());
        values.put("AMOUNT", transaction.getAmount());
        values.put("NOTICE", transaction.getNotice());
    }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        try {

            Date date = parser.parse(components.get("DATE").getAccessibleText());
            Account account = (Account) ((ComboBox) components.get("ACCOUNT")).getValue();
            Article article = (Article) ((ComboBox) components.get("ARTICLE")).getValue();
            String amount = ((TextField) components.get("AMOUNT")).getText();
            String notice = ((TextField) components.get("NOTICE")).getText();
            return new Transaction(account, article, Format.fromAmountToNumber(amount), notice, date);
        } catch (NumberFormatException e) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        } catch (ParseException e) {
            throw new ModelException(ModelException.DATE_FORMAT);
        }
    }
}