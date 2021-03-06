package UI.AddEditWindow;

import UI.Controller;
import financeException.ModelException;
import javafx.scene.control.TextField;
import mainClasses.Article;
import mainClasses.Common;

public class ArticleAddEditDialog extends AddEditWindow {

    public ArticleAddEditDialog(Controller controller,Article article) {
        super(controller,article);
    }

    @Override
    protected void init() {
        components.put("TITLE", new TextField());
        images.put("TITLE", StyleAddEditDialog.ICON_TITLE);

    }

    @Override
    protected void setValues() {
        Article article = (Article) common;
        values.put("TITLE", article.getTitle());

    }

    @Override
    protected Common getCommonFromForm() throws ModelException {
        String title = ((TextField) components.get("TITLE")).getText();
        return new Article(title);
    }

}
