package com.CS1332.bankingapplication.models;

/**
 * Account class.
 */
public class Account {

    /**
     * User associated with account.
     */
    private String user;

    /**
     * Name of account.
     */
    private String name;
    /**
     * Username of account.
     */
    private String username;
    /**
     * Database id.
     */
    private long id;
    /**
     * Balance of account.
     */
    private Double balance;
    /**
     * Monthly interest rate of account.
     */
    private Double rate;

    /**
     * Constructor for account.
     * 
     * @param user to set
     * @param name to set
     * @param username to set
     * @param balance to set
     * @param rate to set
     */
    public Account(String user, String name, String username, Double balance, Double rate) {
        this.user = user;
        this.name = name;
        this.username = username;
        this.balance = balance;
        this.rate = rate;
    }

    /**
     * Constructor for account.
     */
    public Account() {
        this(null, null, null, null, null);
    }

    /**
     * Get user associated with account.
     * 
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Set user associated with account.
     * 
     * @param user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Get name of account.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of account.
     * 
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user name of account.
     * 
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set user name of account.
     * 
     * @param username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Get balance of account.
     * 
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Set balance of account.
     * 
     * @param balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Get monthly interest rate.
     * 
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Set monthly interest rate.
     * 
     * @param rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return username + " " + balance + " " + rate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Account other = (Account) obj;
        if (balance == null) {
            if (other.balance != null) {
                return false;
            }
        } else if (!balance.equals(other.balance)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (rate == null) {
            if (other.rate != null) {
                return false;
            }
        } else if (!rate.equals(other.rate)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

}
