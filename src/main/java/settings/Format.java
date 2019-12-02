package settings;

import model.Currency;
import model.Filter;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

    public static String amount(double amount) {
        return String.format(Settings.FORMAT_AMOUNT, amount);
    }

    public static String amount(double amount, Currency currency) {
        return amount(amount) + " " + currency.getCode();
    }

    public static String rate(double rate) {
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) {
        return rate(rate) + " " + currency.getCode();
    }

    public static String date(Date date) {
        return dataFormat(date, Settings.FORMAT_DATE);
    }

    public static String dateMonth(Date date) {
        return dataFormat(date, Settings.FORMAT_DATE_MONTH);
    }

    public static String dateYear(Date date) {
        return dataFormat(date, Settings.FORMAT_DATE_YEAR);
    }

    private static String dataFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, new MainDateFormatSymbols());
        return simpleDateFormat.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException {
        amount = amount.replaceAll(",", ".");
        return Double.parseDouble(amount);
    }

    public static String yesNo(boolean yes) {
        return yes ? Text.get("YES") : Text.get("NO");
    }

    public static String getTitleFilter(Filter filter) {
        Date time = filter.getTo();
        switch (filter.getStep()) {
            case Filter.STEP_DAY:
                return date(time);
            case Filter.STEP_MONTH:
                return dateMonth(time);
            case Filter.STEP_YEAR:
                return dateYear(time);
        }
        return "";
    }

    private static class MainDateFormatSymbols extends DateFormatSymbols {
        @Override
        public String[] getMonths() {
        return Text.getMonth();
        }
    }
}
