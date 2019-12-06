package model;

import financeException.ModelException;
import saveLoad.SaveData;

import java.util.List;
import java.util.Objects;

public class Account extends java.model.Common {

    private String title;
    private Currency currency;
    private double startAmount;
    private double amount;

    public Account() {
    }

    public Account(String title, Currency currency, double startAmount) throws ModelException {
        if (title.length() == 0) {
            throw new ModelException(ModelException.TITLE_EMPTY);
        }
        if (currency == null) {
            throw new ModelException(ModelException.CURRENCY_EMPTY);
        }
        this.title = title;
        this.currency = currency;
        this.startAmount = startAmount;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(title, account.title);
    }

    @Override
    public int hashCode() {
        return 97 * 7 * Objects.hashCode(this.title);
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    public void setAmountTransactionAndTransfers(List<Transaction> transactions, List<Transfer> transfers) {
        this.amount = startAmount;
        for (Transaction transaction : transactions) {
            if (transaction.getAccount().equals(this)) {
                this.amount += transaction.getAmount();
            }
        }
        for (Transfer transfer : transfers) {
            if (transfer.getFromAccount().equals(this)) {
                this.amount += transfer.getFromAmount();
            }
            if (transfer.getToAccount().equals(this)) {
                this.amount += transfer.getToAmount();
            }
        }
    }

    @Override
    public void postAdd(SaveData saveData) {
        setAmountTransactionAndTransfers(SaveData.getInstance().getTransactions(), SaveData.getInstance().getTransfers());
    }

    @Override
    public void postEdit(SaveData saveData) {
        for (Transaction t: saveData.getTransactions()){
            if (t.getAccount().equals(saveData.getOldCommon())){
                t.setAccount(this);
            }
        }
        for (Transfer t: saveData.getTransfers()){
            if (t.getFromAccount().equals(saveData.getOldCommon())){
                t.setFromAccount(this);
            }
            if (t.getToAccount().equals(saveData.getOldCommon())){
                t.setToAccount(this);
            }
        }
        setAmountTransactionAndTransfers(SaveData.getInstance().getTransactions(), SaveData.getInstance().getTransfers());
    }
}
