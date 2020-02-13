package UI.AddEditDeletePanel;

import UI.AddEditWindow.ArticleAddEditDialog;
import UI.Controller;
import mainClasses.Article;
import mainClasses.Common;

public class ArticleAddDeletePanel extends AddEditDeletePanel {
    private Controller controller;

    public ArticleAddDeletePanel(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    void showAddEditWindow(Common common) {
        Article article = (Article) common;
        new ArticleAddEditDialog(controller,article);
    }
}
