package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Article;
import saveLoad.SaveData;
import settings.Text;

public class ArticleTables extends FinanceTable {

    public ArticleTables() {
        this.fillIn();
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        this.initTable();
    }

    @Override
    public FinanceTable initTable() {
        TableColumn<Article, String> columnArticle = new TableColumn<>(Text.get("ARTICLE"));
        columnArticle.setCellValueFactory(
                Article -> {
                    SimpleObjectProperty<String> propertyArticle = new SimpleObjectProperty<>();
                    propertyArticle.setValue(Article.getValue().getTitle());
                    return propertyArticle;
                });
        this.getColumns().addAll(columnArticle);
        return this;
    }

    @Override
    public void fillIn() {
        ObservableList<Article> articles = FXCollections.observableArrayList(SaveData.getInstance().getArticles());
        this.setItems(articles);
    }
}
