package financeException;


public class ModelException extends Exception {

    public static final int TITLE_EMPTY = 1;
    public static final int IS_EXITS = 2;
    public static final int DATE_FORMAT = 3;
    public static final int CODE_EMPTY = 4;
    public static final int CURRENCY_EMPTY = 5;
    public static final int ARTICLE_EMPTY = 6;
    public static final int ACCOUNT_EMPTY = 7;
    public static final int RATE_INCORRECT = 8;
    public static final int AMOUNT_FORMAT = 9;
    public static final int NO_BASE_CURRENCY = 10;
    public static final int COMMON_NOT_FOUND = 11;
    public static final int ERROR_UPDATE_CURRENCIES = 12;

    private final int code;

    public ModelException(int code) {
        this.code = code;
    }

    public String getMessage() {
        switch (code) {
            case TITLE_EMPTY:
                return settings.Text.get("ERROR_TITLE_EMPTY");
            case IS_EXITS:
                return settings.Text.get("ERROR_IS_EXITS");
            case DATE_FORMAT:
                return settings.Text.get("ERROR_DATE_FORMAT");
            case CODE_EMPTY:
                return settings.Text.get("ERROR_CODE_EMPTY");
            case CURRENCY_EMPTY:
                return settings.Text.get("ERROR_CURRENCY_EMPTY");
            case ARTICLE_EMPTY:
                return settings.Text.get("ERROR_ARTICLE_EMPTY");
            case ACCOUNT_EMPTY:
                return settings.Text.get("ERROR_ACCOUNT_EMPTY");
            case RATE_INCORRECT:
                return settings.Text.get("ERROR_RATE_INCORRECT");
            case AMOUNT_FORMAT:
                return settings.Text.get("ERROR_AMOUNT_FORMAT");
            case NO_BASE_CURRENCY:
                return settings.Text.get("ERROR_NO_BASE_CURRENCY");
            case COMMON_NOT_FOUND:
                return settings.Text.get("COMMON_NOT_FOUND");
            case ERROR_UPDATE_CURRENCIES:
                return settings.Text.get("ERROR_UPDATE_CURRENCIES");
        }
        return "";
    }
}
