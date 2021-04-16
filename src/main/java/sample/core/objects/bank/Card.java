package sample.core.objects.bank;

import net.andreinc.mockneat.MockNeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.core.other.GungaObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class Card implements Serializable
{

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private User cardHolder;
    private String cardNumber;
    private String apr;
    private String pin;
    private String cid;
    private LocalDateTime expirationDate;
    private LocalDateTime dateIssued;
    private CardType cardType;
    private BigDecimal limit;
    private BigDecimal totalLimitForUse;
    private BigDecimal balance;
    private boolean isDisabled;

    @GungaObject
    public Card(User cardHolder, String apr, String pin, CardType cardType, BigDecimal limit, BigDecimal balance)
    {
        this.cardHolder = cardHolder;
        this.apr = apr;
        this.pin = pin;
        this.cardType = cardType;
        this.limit = limit;
        this.balance = balance;
        this.totalLimitForUse = limit;
        this.cid = generateRandomCID();
        this.cardNumber = getCardNameMoified();
        this.expirationDate = LocalDateTime.now().plusSeconds(157788000);
        this.dateIssued = LocalDateTime.now();
        this.isDisabled = false;
    }

    public User getCardHolder()
    {
        return cardHolder;
    }

    public void setCardHolder(User cardHolder)
    {
        this.cardHolder = cardHolder;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getApr()
    {
        return apr;
    }

    public void setApr(String apr)
    {
        this.apr = apr;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public String getCID()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public LocalDateTime getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getDateIssued()
    {
        return dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued)
    {
        this.dateIssued = dateIssued;
    }

    public CardType getCardType()
    {
        return cardType;
    }

    public void setCardType(CardType cardType)
    {
        this.cardType = cardType;
    }

    public BigDecimal getLimit()
    {
        return limit;
    }

    public void setLimit(BigDecimal limit)
    {
        this.limit = limit;
    }

    public BigDecimal getBalance()
    {
        return totalLimitForUse.subtract(limit);
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public BigDecimal getTotalLimitForUse()
    {
        return totalLimitForUse;
    }

    public void setDisabled(boolean disabled)
    {
        this.isDisabled = disabled;
    }

    private String generateRandomCID()
    {
        BankAccount userBankAccount = cardHolder.getBankAccounts().get(0);
        int accountNumberLength = Long.toString(userBankAccount.getAccountNumber()).length();
        StringBuilder CID = new StringBuilder();

        for (int i = 0; i < 3; i++)
        {
            CID.append(Long.toString(userBankAccount.getAccountNumber()).charAt(new Random().nextInt(accountNumberLength)));
        }
        return CID.toString();
    }

    private String getCardNameMoified()
    {
        StringBuilder s = new StringBuilder(
                MockNeat.threadLocal()
                        .creditCards()
                        .masterCard()
                        .get()
        );

        for (int i = 4; i < 19; i += 5)
        {
            s.insert(i, " ");
        }
        return s.toString().trim();
    }

    public void generateNewCard()
    {
        this.cid = generateRandomCID();
        this.cardNumber = getCardNameMoified();
        this.expirationDate = LocalDateTime.now().plusSeconds(157788000);
        this.dateIssued = LocalDateTime.now();
    }


    public enum CardType
    {
        BRONZE("Bronze", 250, 500),
        SILVER("Silver", 500, 1000),
        GOLD("Gold", 1000, 10000),
        PLATINUM("Platinum", 10000, 50000),
        GUNGA("Gunga", -1, -1);

        private int lowerLimit;
        private int upperLimit;
        private String cardName;

        CardType(String cardName, int lowerLimit, int upperLimit)
        {
            this.cardName = cardName;
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
        }

        public String getLowerLimit()
        {
            return String.valueOf(lowerLimit);
        }

        public String getUpperLimit()
        {
            return String.valueOf(upperLimit);
        }

        public String getCardName()
        {
            return cardName;
        }
    }
}
