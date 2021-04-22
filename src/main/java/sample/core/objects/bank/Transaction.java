package sample.core.objects.bank;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Serializable
{


    /**
     * Sender and Receiver
     */
    private User sender;
    private BigDecimal amount;
    private long accountNumber;
    private Date date;
    private TransactionType transactionType;

    public Transaction(BigDecimal amount, long accountNumber, Date date, TransactionType transactionType)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public Date getDate()
    {
        return date;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public TransactionType getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType)
    {
        this.transactionType = transactionType;
    }


    public void setAccountNumber(long accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }


    public enum TransactionType
    {
        TRANSFER("TRANSFER", Color.RED),
        WITHDRAW("WITHDRAW", Color.RED),
        DEPOSIT("DEPOSIT", Color.GREEN),
        CREDIT("CREDIT", Color.green);

        private final String transactionType;
        private final Color transactionColor;

        TransactionType(String transactionType, Color transactionColor)
        {
            this.transactionColor = transactionColor;
            this.transactionType = transactionType;
        }

        public Color getTransactionColor()
        {
            return transactionColor;
        }

        public String getTransactionType()
        {
            return transactionType;
        }
    }
}
