package UI.Tables;


import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import mainClasses.Common;

abstract public class FinanceTable<S> extends TableView {

    abstract public FinanceTable<S> initTable();

    abstract public void fillIn();

    public Common getCommon() {
        Common common;
        try {
            TablePosition position = (TablePosition) this.getSelectionModel().getSelectedCells().get(0);
            int index = position.getRow();
            common = (Common) this.getItems().get(index);
            return common;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
