package UI.AddEditWindow;

import financeException.ModelException;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Account;
import mainClasses.Common;
import mainClasses.Currency;
import saveLoad.SaveData;
import settings.Format;

import java.util.ArrayList;
import java.util.List;

public class AccountEditDialog extends AddEditWindow {


    public AccountEditDialog(Stage stage) {
        super(stage);
    }

    @Override
    protected void init() {

        components.put("TITLE", new TextField());
        components.put("START_BALANCE", new TextField());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setMinWidth(250);
        comboBox.getItems().addAll(getCurrenciesForComboBox());
        components.put("CURRENCY", comboBox);

        images.put("TITLE", Style.ICON_TITLE);
        images.put("CURRENCY", Style.ICON_CURRENCY);
        images.put("START_BALANCE", Style.ICON_START_BALANCE);

        values.put("START_BALANCE", Format.amount(0));
    }

    @Override
    protected void setValues() {
        Account account = (Account) common;
        values.put("TITLE", account.getTitle());
        values.put("CURRENCY", account.getCurrency());
        values.put("START_BALANCE", account.getAmount());
    }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        try {
            String title = ((TextField) components.get("TITLE")).getText();
            String startAmount = ((TextField) components.get("START_BALANCE")).getText();
            Currency currency = (Currency) ((ComboBox) components.get("CURRENCY")).getSelectionModel().getSelectedItem(); //ComboBOX
            return new Account(title, currency, Format.fromAmountToNumber(startAmount));
        } catch (NumberFormatException e) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }

    private List<String> getCurrenciesForComboBox() {
        ArrayList<Currency> currencies = SaveData.getInstance().getEnableCurrencies();
        List<String> nameOfCurrencies = new ArrayList<>();
        for (Currency c : currencies) {
            nameOfCurrencies.add(c.getTitle());
        }
        return nameOfCurrencies;
    }
}
