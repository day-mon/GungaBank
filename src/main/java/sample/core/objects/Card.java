package sample.core.objects;

import java.math.BigDecimal;

public class Card {

    private User cardHolder;
    private long number;
    private int cvc;
    private String expDate;
    private CardType cardType;
    private BigDecimal Balance;

    public Card(User cardHolder) {

    }

    public Card(User cardHolder, long num, int cvc, String exp, CardType type, BigDecimal Balance) {
        this.cardHolder = cardHolder;
        this.number = num;
        this.cvc = cvc;
        this.expDate = exp;
        this.cardType = type;
        this.Balance = Balance;
    }

    public User getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(User cardHolder) {
        this.cardHolder = cardHolder;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getBalance(){
        return this.Balance;
    }
    public void setBalance(BigDecimal lBalance){
        this.Balance = Balance;
    }

    enum CardType {
        Debit, Credit, Rewards
    }
}
