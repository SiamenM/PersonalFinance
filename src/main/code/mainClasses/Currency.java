package mainClasses;

import financeException.ModelException;
import saveLoad.SaveData;

import java.util.Objects;

public class Currency extends Common {

    private String title;
    private String code;
    private double rate;
    private boolean on;
    private boolean base;

    public Currency() {
    }

    public Currency(String title, String code, double rate, boolean on, boolean base) throws ModelException {
        if (title.length() == 0) {
            throw new ModelException(ModelException.TITLE_EMPTY);
        }
        if (code.length() == 0) {
            throw new ModelException(ModelException.CODE_EMPTY);
        }
        if (rate <= 0) {
            throw new ModelException(ModelException.RATE_INCORRECT);
        }
        this.title = title;
        this.code = code;
        this.rate = rate;
        this.base = base;
        if (this.base) {
            this.on = true;
        } else {
            this.on = on;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        if (code != null) {
            return code;
        } else {
            return "BLR";
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return 97 * 7 * Objects.hashCode(this.code);
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    public double getRateByCurrency(Currency currency) {
        return rate / currency.rate;
    }

    @Override
    public void postAdd(SaveData saveData) {
        clearBase(saveData);
    }

    @Override
    public void postEdit(SaveData saveData) {
        clearBase(saveData);
        for (Account account : saveData.getAccounts()) {
            if (account.getCurrency().equals(saveData.getOldCommon())) {
                account.setCurrency(this);
            }
            for (Transaction transaction : saveData.getTransactions()) {
                if (transaction.getAccount().getCurrency().equals(saveData.getOldCommon())) {
                    transaction.getAccount().setCurrency(this);
                }
            }
            for (Transfer transfer : saveData.getTransfers()) {
                if (transfer.getFromAccount().getCurrency().equals(saveData.getOldCommon())) {
                    transfer.getFromAccount().setCurrency(this);
                }
                if (transfer.getToAccount().getCurrency().equals(saveData.getOldCommon())) {
                    transfer.getToAccount().setCurrency(this);
                }
            }
        }
    }

    private void clearBase(SaveData saveData) {
        if (base) {
            rate = 1;
            Currency old = (Currency) saveData.getOldCommon();
            for (Currency c : saveData.getCurrencies()) {
                if (!this.equals(c)) {
                    c.setBase(false);
                    if (old != null) {
                        c.setRate(c.rate / old.rate);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Currency{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                ", on=" + on +
                ", base=" + base +
                '}';
    }
}
