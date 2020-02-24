package settings;

import java.util.HashMap;

final public class Text {

    private static final HashMap<String, String> data = new HashMap<>();

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

        data.put("ERROR", "Ошибка!");
        data.put("ERROR_TITLE_EMPTY", "Название не введено!");
        data.put("ERROR_IS_EXITS", "Такая запись уже существует!");
        data.put("ERROR_DATE_FORMAT", "Некорректный формат даты!");
        data.put("ERROR_CODE_EMPTY", "Не указан код!");
        data.put("ERROR_CURRENCY_EMPTY", "Валюта не выбрана!");
        data.put("ERROR_ARTICLE_EMPTY", "Статья на выбрана!");
        data.put("ERROR_ACCOUNT_EMPTY", "Счёт не выбран!");
        data.put("ERROR_RATE_INCORRECT", "Некорректное значение курса!");
        data.put("ERROR_AMOUNT_FORMAT", "Некорректный формат суммы!");
        data.put("ERROR_NO_BASE_CURRENCY", "Необходима базовая валюта!");
        data.put("ERROR_UPDATE_CURRENCIES", "Ошибка при обновлении курсов валют!");
        data.put("ERROR_NULL_ROW", "Строка не выбрана");

        data.put("YES", "ДА");
        data.put("NO", "НЕТ");

        data.put("MENU_FILE", "Файл");
        data.put("MENU_VIEW", "Вид");
        data.put("MENU_PROPERTIES", "Настройки");
        data.put("MENU_HELP", "Помощь");

        data.put("MENU_FILE_NEW", "Новый");
        data.put("MENU_FILE_OPEN", "Открыть");
        data.put("MENU_FILE_SAVE", "Сохранить");
        data.put("MENU_FILE_UPDATE_CURRENCIES", "Обновить курс валют");
        data.put("MENU_FILE_EXIT", "Выход");
        data.put("BALANCE_CURRENCY", "Баланс по валютам");
        data.put("FINISH_BALANCE", "Итоговый баланс");

        data.put("TITLE", "Наименование");
        data.put("CURRENCY", "Валюта");
        data.put("START_BALANCE", "Начальный баланс");
        data.put("DATE", "Дата");
        data.put("ACCOUNT", "Счёт");
        data.put("ARTICLE", "Статья");
        data.put("AMOUNT", "Сумма");
        data.put("NOTICE", "Примечание");
        data.put("SOURCE", "Источник");
        data.put("TARGET", "Куда");
        data.put("MARKED_OFF", "Списано");
        data.put("ACCEPTED", "Зачислено");
        data.put("CODE", "Код");
        data.put("COURSE", "Курс");
        data.put("ON", "Активная");
        data.put("BASE", "Базовая");

        data.put("MENU_PROPERTIES_LANGUAGE", "Статистика");

        data.put("MENU_HELP_ABOUT", "О программе");
        data.put("LABEL_WITH_RESPECT", "С уважением Семён Маскаленчик");
        data.put("LABEL_NAME_PROGRAM", "Домашняя бухгалтерия, версия 1.0");
        data.put("LABEL_DESCRIPTION_PROGRAM", "Эта программа поможет Вам отслеживать доходы и расходы");

        data.put("MENU_VIEW_OVERVIEW", "Обзор");
        data.put("LABEL_LAST_TRANSACTIONS", "Последние транзакции");
        data.put("MENU_VIEW_ACCOUNTS", "Счета");
        data.put("LABEL_ACCOUNTS", "Счета");
        data.put("BUTTON_ADD", "Добавить");
        data.put("BUTTON_EDIT", "Изменить");
        data.put("BUTTON_DELETE", "Удалить");
        data.put("MENU_VIEW_ARTICLES", "Статьи");
        data.put("LABEL_ARTICLES", "Статьи");
        data.put("MENU_VIEW_TRANSACTIONS", "Транзакции");
        data.put("LABEL_TRANSACTIONS", "Транзакции");
        data.put("MENU_VIEW_TRANSFERS", "Переводы");
        data.put("LABEL_TRANSFERS", "Переводы");
        data.put("MENU_VIEW_CURRENCIES", "Валюты");
        data.put("LABEL_CURRENCIES", "Валюты");
        data.put("MENU_VIEW_STATISTICS", "Статистика");
        data.put("LABEL_STATISTICS", "Статистика");
        data.put("INCOMES_BY_ARTICLES", "Доходы по статьям");
        data.put("EXPENSES_BY_ARTICLES", "Расходы по статьям");

        data.put("CHART_INCOMES", "Доходы");
        data.put("CHART_DECREASE", "Расходы");
        data.put("CHART_NO_DATA", "Для данного периода данные отсутствуют!");

        data.put("CONFIRM_EXIT_TEXT", "Вы уверены, что хотите выйти? Несохраннёные данные будут удалены.");
        data.put("CONFIRM_EXIT_TITLE", "Подтверждение выхода");
        data.put("CONFIRM_DELETE_TITLE", "Подтверждение удаления");
        data.put("CONFIRM_DELETE_TEXT", "Вы уверены, что хотите удалить, выбранные данные?");
        data.put("OK", "ОК");
        data.put("EXIT", "Выйти");
        data.put("SAVE_AND_EXIT", "Сохранить и выйти");
        data.put("CANCEL", "Отмена");
        data.put("SAVE", "Сохранить");
        data.put("OPEN", "Открыть");
        data.put("EDIT", "Изменить");
        data.put("DELETE", "Удалить");
        data.put("ADD", "Добавить");
    }

}

