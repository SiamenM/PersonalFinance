package UI.AddEditWindow;

import UI.Controller;
import financeException.ModelException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mainClasses.Account;
import mainClasses.Common;
import mainClasses.Currency;
import saveLoad.SaveData;
import settings.Format;

public class AccountAddEditDialog extends AddEditWindow {


    public AccountAddEditDialog(Controller controller, Account account) {
        super(controller,account);
    }

    @Override
    protected void init() {

        components.put("TITLE", new TextField());
        components.put("START_BALANCE", new TextField());
        components.put("CURRENCY", initComboBox(SaveData.getInstance().getEnableCurrencies()));

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
            Currency currency = (Currency) ((ComboBox) components.get("CURRENCY")).getSelectionModel().getSelectedItem();
            return new Account(title, currency, Format.fromAmountToNumber(startAmount));
        } catch (NumberFormatException e) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }

}
