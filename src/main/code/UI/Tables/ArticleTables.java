package UI.Tables;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mainClasses.Account;
import mainClasses.Article;
import settings.Text;

import java.util.List;

public class ArticleTables extends FinanceTable {

    public ArticleTables(List<Article> articles) {
        ObservableList<Article> items = FXCollections.observableArrayList(articles);
        this.setItems(items);
        this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
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
}
