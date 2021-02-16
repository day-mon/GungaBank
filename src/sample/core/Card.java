package sample.core;

public class Card {

    private User cardHolder;
    private long number;
    private int cvc;
    private String expDate;
    private CardType cardType;

    public Card(User cardHolder) {

    }

    public Card(User _cardHolder, long _num, int _cvc, String _exp, CardType _type) {
        cardHolder = _cardHolder;
        number = _num;
        cvc = _cvc;
        expDate = _exp;
        cardType = _type;
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

    enum CardType {
        Debit, Credit, Rewards
    }
}
