package sample.core;

import sample.util.ArrayList;

import java.io.Serializable;
import java.lang.reflect.Array;


public class BankAccount implements Serializable {

    private User owner;
    private AccountTypes accountType;
    private ArrayList<Transaction> transactions;


    enum AccountTypes {
        CHECKING,
        SAVINGS
    }
}
