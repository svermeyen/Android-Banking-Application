package com.CS1332.bankingapplication.models;

/**
 * Deposit class.
 */
public class Deposit extends AbstractTransaction {

    /**
     * Deposit constructor.
     * 
     * @param reason for deposit
     * @param amount of deposit
     * @param account associated with deposit
     */
    public Deposit(String reason, Double amount, Account account) {
        super(reason, amount, account);
    }

    /**
     * Deposit constructor.
     */
    public Deposit() {
        super();
    }

    @Override
    public Account modifyAccount(Account account) {
        account.setBalance(account.getBalance() + this.amount);
        return account;
    }

}
