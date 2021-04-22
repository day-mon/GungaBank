package sample.core.objects.bank;

import sample.util.structures.ArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;


public class BankAccount implements Serializable
{

    private final String ACC_NUM_PREFIX = "814";

    private User owner;
    private AccountTypes accountType;
    private ArrayList<Transaction> transactions;
    private BigDecimal balance;
    private final long accountNumber;

    public BankAccount(User user, AccountTypes type)
    {
        owner = user;
        accountType = type;
        accountNumber = generateAccNum();
        transactions = new ArrayList<>();
        transactions.add(new Transaction(new BigDecimal(1000), accountNumber, owner.getDateOfCreation(), Transaction.TransactionType.CREDIT));
        balance = new BigDecimal("1000");
    }

    private long generateAccNum()
    {
        Random r = new Random();

        StringBuilder num = new StringBuilder(ACC_NUM_PREFIX);
        for (int i = 0; i < 10; i++)
        {
            num.append(r.nextInt(10));
        }
        return Long.parseLong(num.toString());
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public User getOwner()
    {
        return owner;
    }

    public void addToBalance(BigDecimal addBal)
    {
        balance = balance.add(addBal);
    }

    public void removeToBalance(BigDecimal removBal)
    {
        balance = balance.subtract(removBal);
    }

    public void setBalance(String value)
    {
        balance = new BigDecimal(value);
    }

    public double getBalance()
    {
        return balance.doubleValue();
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public AccountTypes getAccountType()
    {
        return accountType;
    }

    public void setAccountType(AccountTypes accountType)
    {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        BankAccount that = (BankAccount) o;
        return Objects.equals(owner, that.owner) &&
                accountType == that.accountType &&
                Objects.equals(transactions, that.transactions);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(owner, accountType, transactions);
    }

    public enum AccountTypes
    {
        CHECKING,
        SAVINGS
    }
}
