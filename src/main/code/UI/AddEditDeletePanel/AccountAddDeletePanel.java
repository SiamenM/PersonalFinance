package UI.AddEditDeletePanel;

import UI.AddEditWindow.AccountAddEditDialog;
import UI.Controller;
import financeException.ModelException;
import mainClasses.Account;
import mainClasses.Common;
import saveLoad.SaveData;

public class AccountAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;

    public AccountAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void deleteRow(Common common) throws ModelException {
        SaveData.getInstance().remove(common);
        controller.getAccountsTable().fillIn();
    }

    @Override
    void showAddEditWindow(Common common) {
        new AccountAddEditDialog(controller, (Account) common);
    }

    @Override
    Common getSelectedCommon() {
        return controller.getAccountsTable().getCommon();
    }

}
