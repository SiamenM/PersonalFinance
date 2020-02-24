package UI.AddEditWindow;

import UI.Controller;
import financeException.ModelException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Account;
import mainClasses.Common;
import mainClasses.Transfer;
import saveLoad.SaveData;
import settings.Format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TransferAddEditDialog extends AddEditWindow {

    private SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    private Controller controller;
    private Stage stage;

    public TransferAddEditDialog(Controller controller, Transfer transfer) {
        super(controller,transfer);
        this.controller = controller;
        this.stage = super.stage;
    }

    @Override
    protected void init() {
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        components.put("DATE", datePicker);
        components.put("SOURCE", initComboBox(SaveData.getInstance().getAccounts()));
        components.put("TARGET", initComboBox(SaveData.getInstance().getAccounts()));
        components.put("MARKED_OFF", new TextField());
        components.put("ACCEPTED", new TextField());
        components.put("NOTICE", new TextField());
        images.put("DATE", StyleAddEditDialog.ICON_DATE);
        images.put("SOURCE", StyleAddEditDialog.ICON_ACCOUNT);
        images.put("TARGET", StyleAddEditDialog.ICON_ACCOUNT);
        images.put("MARKED_OFF", StyleAddEditDialog.ICON_MARK_OFF);
        images.put("ACCEPTED", StyleAddEditDialog.ICON_ACCEPTED);
        images.put("AMOUNT", StyleAddEditDialog.ICON_START_BALANCE);
        images.put("NOTICE", StyleAddEditDialog.ICON_NOTICE);
        values.put("MARKED_OFF", Format.amount(0));
        values.put("ACCEPTED", Format.amount(0));
    }

    @Override
    protected void setValues() {
        Transfer transfer = (Transfer) common;
        values.put("DATE", transfer.getDate());
        values.put("SOURCE", transfer.getFromAccount());
        values.put("TARGET", transfer.getToAccount());
        values.put("MARKED_OFF", transfer.getFromAmount());
        values.put("ACCEPTED", transfer.getToAmount());
        values.put("NOTICE", transfer.getNotice());
    }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        try {
            Date date = parser.parse(components.get("DATE").getAccessibleText());
            Account fromAccount = (Account) ((ComboBox) components.get("SOURCE")).getValue();
            Account toAccount = (Account) ((ComboBox) components.get("TARGET")).getValue();
            String fromAmount = ((TextField) components.get("MARKED_OFF")).getText();
            String toAmount = ((TextField) components.get("ACCEPTED")).getText();
            String notice = ((TextField) components.get("NOTICE")).getText();
            return new Transfer(fromAccount, toAccount, Format.fromAmountToNumber(fromAmount), Format.fromAmountToNumber(toAmount), notice, date);
        } catch (NumberFormatException e) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        } catch (ParseException e) {
            throw new ModelException(ModelException.DATE_FORMAT);
        }
    }

}
