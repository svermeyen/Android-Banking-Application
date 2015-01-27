package com.CS1332.bankingapplication.models;

import java.util.Collection;
import java.util.List;

import com.CS1332.bankingapplication.db.BankDataSource;

/**
 * Interface for model class.
 */
public interface Model {
    /**
     * Checks if username corresponds to a user.
     * 
     * @param username to check
     * @return true if user is in database
     */
    boolean isUser(final String username);

    /**
     * Checks if user name and password corresponds to a user.
     * 
     * @param username to check
     * @param password to check
     * @return true if user is in database
     */
    boolean isUser(final String username, final String password);

    /**
     * Open database.
     */
    void openDatasource();

    /**
     * Close database. 
     */
    void closeDatasource();

    /**
     * Initialize user map.
     */
    void initialize();

    /**
     * Add user to database.
     * 
     * @param username to create user with
     * @param password to create user with
     */
    void createUser(String username, String password);

    /**
     * Add account to database.
     * 
     * @param username to create account with
     * @param name to create account with
     * @param display to create account with
     * @param balance to create account with
     * @param mir to create account with
     */
    void addToAccount(String username, String name, String display, Double balance, Double mir);

    /**
     * Update account in database.
     * 
     * @param account to update
     */
    void updateAccount(Account account);

    /**
     * Add deposit to database.
     * 
     * @param deposit to add
     */
    void addToDeposit(Deposit deposit);

    /**
     * Add withdrawal to database.
     * 
     * @param withdrawal to add
     */
    void addToWithdrawal(Withdrawal withdrawal);

    /**
     * Find accounts.
     * 
     * @param args to filter database by
     * @param orderBy to order database by
     * @return List of accounts
     */
    List<Account> findAccount(String[] args, String orderBy);

    /**
     * Find all deposits.
     * 
     * @return List of deposits
     */
    List<AbstractTransaction> findAllDeposits();

    /**
     * Find all withdrawals.
     * 
     * @return List of withdrawals
     */
    List<AbstractTransaction> findAllWithdrawals();

    /**
     * Find specific deposits.
     * 
     * @param args to filter database by
     * @param orderBy to order database by
     * @return List of deposits
     */
    List<AbstractTransaction> findDeposits(String[] args, String orderBy);

    /**
     * Find specific withdrawals.
     * 
     * @param args to filter database by
     * @param orderBy to order database by
     * @return List of withdrawals
     */
    List<AbstractTransaction> findWithdrawals(String[] args, String orderBy);

    /**
     * Find withdrawals for spending report.
     * 
     * @param args to filter database by
     * @param orderBy to order database by
     * @return List of withdrawals
     */
    List<AbstractTransaction> findWithdrawalsForSpendingReport(String[] args, String orderBy);

    /**
     * Get datasource.
     * 
     * @return datasource.
     */
    BankDataSource getDatasource();

    /**
     * Get users.
     * 
     * @return users
     */
    Collection<User> getUsers();

    /**
     * Get spending report text.
     * 
     * @param args to filter database by
     * @return text
     */
    String getSpendingReportText(String[] args);

    /**
     * Update user in database.
     * 
     * @param user to be updated
     */
    void updateUser(User user);

    /**
     * Change password for user.
     * 
     * @param username of user
     * @param password of user
     */
    void changePassword(String username, String password);
}
