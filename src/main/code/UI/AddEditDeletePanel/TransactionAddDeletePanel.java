package UI.AddEditDeletePanel;

import UI.AddEditWindow.TransactionAddEditDialog;
import UI.Controller;
import mainClasses.Common;
import mainClasses.Transaction;

public class TransactionAddDeletePanel extends AddEditDeletePanel {

    private Controller controller;

    public TransactionAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void showAddEditWindow(Common common) {
        Transaction transaction = (Transaction) common;
        new TransactionAddEditDialog(controller, transaction);

    }
}
