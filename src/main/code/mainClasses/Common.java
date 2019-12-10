package mainClasses;


import saveLoad.SaveData;

abstract public class Common {

    public String getValueForComboBox(){
        return null;
    }

    public void postAdd (SaveData saveData){}
    public void postEdit(SaveData saveData) {}
    public void postRemove(SaveData saveData) {}
}
