package UI.AddEditDeletePanel;

import UI.AddEditWindow.CurrencyAddEditDialog;
import UI.Controller;
import financeException.ModelException;
import mainClasses.Common;
import mainClasses.Currency;
import saveLoad.SaveData;

public class CurrencyAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;

    public CurrencyAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void deleteRow(Common common) throws ModelException {
        SaveData.getInstance().remove(common);
        controller.getCurrencyTable().fillIn();
    }

    @Override
    void showAddEditWindow(Common common) {
        new CurrencyAddEditDialog(controller, (Currency)common);
    }

    @Override
    Common getSelectedCommon() {
        return controller.getCurrencyTable().getCommon();
    }
}
