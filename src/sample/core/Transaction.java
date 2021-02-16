package sample.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Serializable {


    /**
     * Sender and Reciever
     */
    private User sender;
    private BigDecimal amount;
    private long accountNumber;
    private Date date;
    private String depositOrWithdraw;

    public Transaction(BigDecimal amount, long accountNumber, Date date, String depositOrWithdraw) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.date = date;
        this.depositOrWithdraw = depositOrWithdraw;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getDepositOrWithdraw() {
        return depositOrWithdraw;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDepositOrWithdraw(String depositOrWithdraw) {
        this.depositOrWithdraw = depositOrWithdraw;
    }
}
