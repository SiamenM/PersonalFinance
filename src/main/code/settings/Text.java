package settings;

import java.util.HashMap;

final public class Text {

    private static final HashMap<String, String> data = new HashMap<>();

    public static String get(String key) {
        if (data.containsKey(key)) {
            return data.get(key);
        }
        System.out.println("This key don't exit"); // for debugging
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
        if ("ru".equals(Settings.getProgramLanguage())) {
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
            data.put("MENU_HELP_ABOUT", "О программе");
            data.put("LABEL_WITH_RESPECT", "С уважением Семён Маскаленчик");
            data.put("LABEL_NAME_PROGRAM", "Домашняя бухгалтерия, версия 1.0");
            data.put("LABEL_DESCRIPTION_PROGRAM", "Эта программа поможет Вам отслеживать доходы и расходы");
            data.put("MENU_VIEW_OVERVIEW", "Обзор");
            data.put("LABEL_LAST_TRANSACTIONS", "Последние транзакции");
            data.put("ACCOUNTS", "Счета");
            data.put("ARTICLES", "Статьи");
            data.put("TRANSACTIONS", "Транзакции");
            data.put("TRANSFERS", "Переводы");
            data.put("CURRENCIES", "Валюты");
            data.put("STATISTICS", "Статистика");
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
            data.put("MENU_PROPERTIES_LANGUAGE", "Язык");
            data.put("LANGUAGE", "Язык");
            data.put("RUSSIAN", "Русский");
            data.put("ENGLISH", "Англиский");
        } else {
            data.put("PROGRAM_NAME", "Personal finance");
            data.put("JANUARY", "January");
            data.put("FEBRUARY", "February");
            data.put("MARCH", "March");
            data.put("APRIL", "April");
            data.put("MAY", "May");
            data.put("JUNE", "June");
            data.put("JULY", "July");
            data.put("AUGUST", "August");
            data.put("SEPTEMBER", "September");
            data.put("OCTOBER", "October");
            data.put("NOVEMBER", "November");
            data.put("DECEMBER", "December");
            data.put("ERROR", "Error!");
            data.put("ERROR_TITLE_EMPTY", "Title is empty!");
            data.put("ERROR_IS_EXITS", "!");
            data.put("ERROR_DATE_FORMAT", "Incorrect date format!");
            data.put("ERROR_CODE_EMPTY", "Currency code not entered");
            data.put("ERROR_CURRENCY_EMPTY", "Currency didn't selected!");
            data.put("ERROR_ARTICLE_EMPTY", "Article didn't selected!");
            data.put("ERROR_ACCOUNT_EMPTY", "Account didn't selected!");
            data.put("ERROR_RATE_INCORRECT", "Incorrect rate!");
            data.put("ERROR_AMOUNT_FORMAT", "Incorrect amount format!");
            data.put("ERROR_NO_BASE_CURRENCY", "No base currency!");
            data.put("ERROR_UPDATE_CURRENCIES", "Error updating currency rates!");
            data.put("ERROR_NULL_ROW", "Row not selected");
            data.put("YES", "Yes");
            data.put("NO", "No");
            data.put("MENU_FILE", "File");
            data.put("MENU_VIEW", "View");
            data.put("MENU_PROPERTIES", "Settings");
            data.put("MENU_HELP", "Help");
            data.put("MENU_FILE_NEW", "New");
            data.put("MENU_FILE_UPDATE_CURRENCIES", "Update rate of currencies");
            data.put("MENU_FILE_EXIT", "Exit");
            data.put("BALANCE_CURRENCY", "Currency balance");
            data.put("FINISH_BALANCE", "Finish balance");
            data.put("TITLE", "Title");
            data.put("CURRENCY", "Currency");
            data.put("START_BALANCE", "Start balance");
            data.put("DATE", "Date");
            data.put("ACCOUNT", "Account");
            data.put("ARTICLE", "Article");
            data.put("AMOUNT", "Amount");
            data.put("NOTICE", "Notice");
            data.put("SOURCE", "Source");
            data.put("TARGET", "Target");
            data.put("MARKED_OFF", "Marked off");
            data.put("ACCEPTED", "Accepted");
            data.put("CODE", "Code");
            data.put("COURSE", "Course");
            data.put("ON", "On");
            data.put("BASE", "Base");
            data.put("MENU_HELP_ABOUT", "About program");
            data.put("LABEL_WITH_RESPECT", "With respect Siamen Maskalenchyk");
            data.put("LABEL_NAME_PROGRAM", "Personal Finance, version 1.0");
            data.put("LABEL_DESCRIPTION_PROGRAM", "This program helps to control your finance");
            data.put("MENU_VIEW_OVERVIEW", "Overview");
            data.put("LABEL_LAST_TRANSACTIONS", "Last transactions");
            data.put("ACCOUNTS", "Accounts");
            data.put("ARTICLES", "Articles");
            data.put("TRANSACTIONS", "Transactions");
            data.put("TRANSFERS", "Transfers");
            data.put("CURRENCIES", "Currencies");
            data.put("STATISTICS", "Statistics");
            data.put("INCOMES_BY_ARTICLES", "Incomes by articles");
            data.put("EXPENSES_BY_ARTICLES", "Expenses by articles");
            data.put("CHART_INCOMES", "Income");
            data.put("CHART_DECREASE", "Decrease");
            data.put("CHART_NO_DATA", "No data for this period!");
            data.put("CONFIRM_EXIT_TEXT", "Are you sure you want to go out? Unsaved data will be deleted.");
            data.put("CONFIRM_EXIT_TITLE", "Exit confirmation");
            data.put("CONFIRM_DELETE_TITLE", "Delete confirmation");
            data.put("CONFIRM_DELETE_TEXT", "Are you sure you want to delete selected data?");
            data.put("OK", "ОК");
            data.put("CANCEL", "Cancel");
            data.put("EXIT", "Exit");
            data.put("SAVE_AND_EXIT", "Save and exit");
            data.put("SAVE", "Save");
            data.put("OPEN", "Open");
            data.put("EDIT", "Edit");
            data.put("DELETE", "Delete");
            data.put("ADD", "Add");
            data.put("MENU_PROPERTIES_LANGUAGE", "Language");
            data.put("LANGUAGE", "Language");
            data.put("RUSSIAN", "Russian");
            data.put("ENGLISH", "English");
        }
    }
}

