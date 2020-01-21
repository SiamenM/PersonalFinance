package UI.AddEditWindow;

import financeException.ModelException;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Article;
import mainClasses.Common;

public class ArticleAddEditDialog extends AddEditWindow {

    public ArticleAddEditDialog(Stage stage) {
        super(stage);
    }

    @Override
    protected void init() {
        components.put("TITLE", new TextField());
        images.put("TITLE", Style.ICON_TITLE);

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
