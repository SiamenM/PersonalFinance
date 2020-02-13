package UI.AddEditDeletePanel;

import UI.AddEditWindow.TransferAddEditDialog;
import UI.Controller;
import mainClasses.Common;
import mainClasses.Transfer;

public class TransferAddDeletePanel extends AddEditDeletePanel {

    private Controller controller;

    public TransferAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void showAddEditWindow(Common common) {
        Transfer transfer = (Transfer) common;
        new TransferAddEditDialog(controller, transfer);
    }
}
