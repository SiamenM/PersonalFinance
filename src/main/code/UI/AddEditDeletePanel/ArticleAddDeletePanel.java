package UI.AddEditDeletePanel;

import UI.AddEditWindow.ArticleAddEditDialog;
import UI.Controller;
import financeException.ModelException;
import mainClasses.Article;
import mainClasses.Common;
import saveLoad.SaveData;

public class ArticleAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;

    public ArticleAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void deleteRow(Common common) throws ModelException {
        SaveData.getInstance().remove(common);
        controller.getArticleTable().fillIn();
    }

    @Override
    void showAddEditWindow(Common common) {
        new ArticleAddEditDialog(controller,(Article)common);
    }

    @Override
    Common getSelectedCommon() {
        return controller.getArticleTable().getCommon();
    }
}
