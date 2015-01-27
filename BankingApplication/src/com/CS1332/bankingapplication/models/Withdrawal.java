package com.CS1332.bankingapplication.models;

/**
 * Withdrawal class.
 */
public class Withdrawal extends AbstractTransaction {

    /**
     * Withdrawal constructor.
     * 
     * @param reason for withdrawal
     * @param amount of withdrawal
     * @param account associated with withdrawal
     */
    public Withdrawal(String reason, Double amount, Account account) {
        super(reason, amount, account);
    }

    /**
     * Withdrawal constructor.
     */
    public Withdrawal() {
        super();
    }

    @Override
    public Account modifyAccount(Account account) {
        account.setBalance(account.getBalance() - this.amount);
        return account;
    }

}
