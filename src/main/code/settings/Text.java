package settings;

import java.util.HashMap;

final public class Text {

    private static final HashMap<String, String> data = new HashMap<String, String>();

    public static String get(String key) {
        if (data.containsKey(key)) {
            return data.get(key);
        }
        System.out.println("This key don't exits"); // for debugging
        return "";
    }

    public static String[] getMonth() {
        String[] months = new String[12];
        months[0] = data.get("JANUARY");
        months[1] = data.get("FEBRUARY");
        months[2] = data.get("MARCH");
        months[3] = data.get("APRIL");
        months[4] = data.get("MAY");
        months[5] = data.get("JUNE");
        months[6] = data.get("JULY");
        months[7] = data.get("AUGUST");
        months[8] = data.get("SEPTEMBER");
        months[9] = data.get("OCTOBER");
        months[10] = data.get("NOVEMBER");
        months[11] = data.get("DECEMBER");
        return months;
    }

    public static void init() {
        data.put("PROGRAM_NAME", "Домашняя бухгалтерия");
        data.put("MENU_FILE", "Файл");
        data.put("MENU_EDIT", "Справка");
        data.put("MENU_VIEW", "Вид");
        data.put("MENU_HELP", "Помощь");

        data.put("JANUARY", "Январь");
        data.put("FEBRUARY", "Февраль");
        data.put("MARCH", "Март");
        data.put("APRIL", "Апрель");
        data.put("MAY", "Май");
        data.put("JUNE", "Июнь");
        data.put("JULY", "Июль");
        data.put("AUGUST", "Август");
        data.put("SEPTEMBER", "Сентябрь");
        data.put("OCTOBER", "Октябрь");
        data.put("NOVEMBER", "Ноябрь");
        data.put("DECEMBER", "Декабрь");

        data.put("TITLE_EMPTY", "Название не введено!");
        data.put("IS_EXITS", "Такая запись уже существует!");
        data.put("DATE_FORMAT", "Некорректный формат даты!");
        data.put("CODE_EMPTY", "Не указан код!");
        data.put("CURRENCY_EMPTY", "Валюта не выбрана!");
        data.put("ARTICLE_EMPTY", "Статья не выбрана!");
        data.put("ACCOUNT_EMPTY", "Счёт не выбран!");
        data.put("RATE_INCORRECT", "Некорректное значение курса!");
        data.put("AMOUNT_FORMAT", "Некорректный формат суммы!");
        data.put("NO_BASE_CURRENCY", "Установите базовую валюту!");

        data.put("YES","ДА");
        data.put("NO","НЕТ");

    }

}

