package settings;

import java.util.HashMap;

final public class Text {

    private static final HashMap <String, String> data = new HashMap<String, String>();

    public static void init(){
        data.put("PROGRAM_NAME", "Домашняя бухгалтерия");
        data.put("MENU_FILE", "Файл");

    }
}
