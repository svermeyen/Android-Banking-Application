package com.CS1332.bankingapplication.models;

/**
 * Transaction class.
 */
public abstract class AbstractTransaction {

    /**
     * Database id.
     */
    protected long id;
    /**
     * User associated with transaction.
     */
    protected String user; 
    /**
     * Name of account associated with transaction.
     */
    protected String name;
    /**
     * Reason for transaction.
     */
    protected String reason;
    /**
     * Amount of transaction.
     */
    protected Double amount;

    /**
     * Time of transaction.
     */
    protected long timeOfTransaction;
    /**
     * Time user enters for transaction to take effect.
     */
    protected long timeOfUser;

    /**
     * Transaction constructor.
     * 
     * @param reason for transaction
     * @param amount of transaction
     * @param account associated with transaction
     */
    protected AbstractTransaction(String reason, Double amount, Account account) {
        this.reason = reason;
        this.amount = amount;
        user = account.getUser();
        name = account.getName();
        timeOfTransaction = System.currentTimeMillis();
        timeOfUser = 1; // Place holder
    }

    /**
     * Transaction constructor.
     */
    public AbstractTransaction() {
        reason = null;
        amount = 0.0;
    }

    /**
     * Get time of user.
     * 
     * @return time
     */
    public long getTimeOfUser() {
        return timeOfUser;
    }

    /**
     * Set time of user.
     * 
     * @param timeOfUser to set
     */
    public void setTimeOfUser(long timeOfUser) {
        this.timeOfUser = timeOfUser;
    }

    /**
     * Get user associated with transaction.
     * 
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Get name of account associated with transaction.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get time of transaction.
     * 
     * @return time
     */
    public long getTimeOfTransaction() {
        return timeOfTransaction;
    }

    /**
     * Set user associated with transaction.
     * 
     * @param user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Set name of account associated with transaction.
     * 
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set time of transaction.
     * 
     * @param timeOfTransaction to set
     */
    public void setTimeOfTransaction(long timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    /**
     * Get database id.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Set database id.
     * 
     * @param id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get reason for transaction.
     * 
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set reason for transaction.
     * 
     * @param reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Get amount of transaction.
     * 
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Set amount of transaction.
     * 
     * @param amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Modify balance of account.
     * 
     * @param account to be modified
     * @return modified account
     */
    public abstract Account modifyAccount(Account account);

    @Override
    public String toString() {
        return reason + " " + amount;
    }
}
