package UI.AddEditDeletePanel;

import UI.AddEditWindow.TransactionAddEditDialog;
import UI.Controller;
import financeException.ModelException;
import mainClasses.Common;
import mainClasses.Transaction;
import saveLoad.SaveData;

public class TransactionAddDeletePanel extends AddEditDeletePanel {

    private Controller controller;

    public TransactionAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void deleteRow(Common common) throws ModelException {
        SaveData.getInstance().remove(common);
        controller.getTransactionTable().fillIn();
    }

    @Override
    void showAddEditWindow(Common common) {
        new TransactionAddEditDialog(controller,(Transaction) common);

    }

    @Override
    Common getSelectedCommon() {
        return controller.getTransactionTable().getCommon();
    }
}
