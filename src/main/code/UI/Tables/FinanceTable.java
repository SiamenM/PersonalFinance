package UI.Tables;


import javafx.scene.control.TableView;

abstract public class FinanceTable<S> extends TableView {

    abstract public FinanceTable<S> initTable();
    abstract public void fillIn();
}
