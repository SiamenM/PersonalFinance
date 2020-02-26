package UI.AddEditWindow;

import UI.Controller;
import financeException.ModelException;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mainClasses.Common;
import mainClasses.Currency;
import settings.Format;
import settings.Settings;
import settings.Text;

public class CurrencyAddEditDialog extends AddEditWindow {

    public CurrencyAddEditDialog(Controller controller, Currency currency) {
        super(controller,currency);
    }

    @Override
    protected void init() {
        String[] yesNo = new String[] {Text.get("YES"),Text.get("NO")};
        components.put("TITLE", new TextField());
        components.put("CODE", initComboBox(Settings.CURRENCIES_CODES));
        components.put("COURSE", new TextField());
        components.put("ON",initComboBox(yesNo));
        components.put("BASE", initComboBox(yesNo));
        images.put("TITLE", StyleAddEditDialog.ICON_TITLE);
        images.put("CODE", StyleAddEditDialog.ICON_CODE_CURRENCY);
        images.put("ON", StyleAddEditDialog.ICON_ON_CURRENCY);
        images.put("COURSE", StyleAddEditDialog.ICON_RATE_CURRENCY);
        images.put("BASE", StyleAddEditDialog.ICON_BASE_CURRENCY);
        values.put("RATE", Format.amount(1));
    }

    private ComboBox initComboBox(String [] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setMinWidth(250);
        comboBox.setItems(FXCollections.observableArrayList(strings));
        comboBox.setValue(strings[0]);
        return comboBox;
    }

    @Override
    protected void setValues() {
        Currency currency = (Currency) common;
        values.put("TITLE", currency.getTitle());
        values.put("CODE", currency.getCode());
        values.put("COURSE", currency.getRate());
        if (currency.isOn()) {
            values.put("ON", Text.get("YES"));
        } else {
            values.put("ON", Text.get("NO"));
        }
        if (currency.isBase()) {
            values.put("BASE", Text.get("YES"));
        } else {
            values.put("BASE", Text.get("NO"));
        }
           }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        try {
            String title = ((TextField) components.get("TITLE")).getText();
            String code = (String) ((ComboBox) components.get("CODE")).getValue();
            String rate = ((TextField) components.get("COURSE")).getText();
            boolean isOn = false;
            if (((ComboBox) components.get("ON")).getValue().equals(Text.get("YES"))) {
                isOn = true;
            }
            boolean isBase = false;
            if (((ComboBox) components.get("BASE")).getValue().equals("YES")) {
                isBase = true;
            }
            if (!isBase && common != null && ((Currency) common).isBase()) {
                throw new ModelException(ModelException.NO_BASE_CURRENCY);
            }
            return new Currency(title,code,Format.fromAmountToNumber(rate),isOn,isBase);
        } catch (NumberFormatException e) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }
}
