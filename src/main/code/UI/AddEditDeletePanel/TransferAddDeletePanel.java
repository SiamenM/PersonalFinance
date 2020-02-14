package UI.AddEditDeletePanel;

import UI.AddEditWindow.TransferAddEditDialog;
import UI.Controller;
import financeException.ModelException;
import mainClasses.Common;
import mainClasses.Transfer;
import saveLoad.SaveData;

public class TransferAddDeletePanel extends AddEditDeletePanel {

    private Controller controller;

    public TransferAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void deleteRow(Common common) throws ModelException {
        SaveData.getInstance().remove(common);
        controller.getTransferTable().fillIn();
    }

    @Override
    void showAddEditWindow(Common common) {
        new TransferAddEditDialog(controller, (Transfer) common);
    }

    @Override
    Common getSelectedCommon() {
        return controller.getTransferTable().getCommon();
    }
}
