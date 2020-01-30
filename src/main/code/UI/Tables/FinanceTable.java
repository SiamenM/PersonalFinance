package UI.Tables;


import javafx.scene.control.TableView;

abstract class FinanceTable<S> extends TableView {

    abstract public FinanceTable<S> initTable();

}
