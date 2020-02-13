package UI.AddEditDeletePanel;

import UI.AddEditWindow.AccountAddEditDialog;
import UI.Controller;
import mainClasses.Account;
import mainClasses.Common;

public class AccountAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;
    public AccountAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void showAddEditWindow(Common common) {
        Account account = (Account) common;
        new AccountAddEditDialog(controller,account);
    }

}
