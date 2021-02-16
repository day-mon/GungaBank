package sample.core.objects;

import sample.util.ArrayList;

import java.io.Serializable;
import java.util.Objects;


public class BankAccount implements Serializable {

    private User owner;
    private AccountTypes accountType;
    private ArrayList<Transaction> transactions;

    public User getOwner() {
        return owner;
    }



    public void setOwner(User owner) {
        this.owner = owner;
    }

    public AccountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypes accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(owner, that.owner) &&
                accountType == that.accountType &&
                Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, accountType, transactions);
    }

    enum AccountTypes {
        CHECKING,
        SAVINGS
    }
}
