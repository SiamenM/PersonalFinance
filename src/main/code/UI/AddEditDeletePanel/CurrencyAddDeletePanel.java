package UI.AddEditDeletePanel;

import UI.AddEditWindow.CurrencyAddEditDialog;
import UI.Controller;
import mainClasses.Common;
import mainClasses.Currency;

public class CurrencyAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;

    public CurrencyAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void showAddEditWindow(Common common) {
        Currency currency = (Currency) common;
        new CurrencyAddEditDialog(controller, currency);
    }
}
