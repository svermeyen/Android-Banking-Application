package com.CS1332.bankingapplication.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.CS1332.bankingapplication.models.Account;
import com.CS1332.bankingapplication.models.Deposit;
import com.CS1332.bankingapplication.models.AbstractTransaction;
import com.CS1332.bankingapplication.models.User;
import com.CS1332.bankingapplication.models.Withdrawal;

/**
 * Database controller class.
 */
public class BankDataSource {


    /**
     * String tag.
     */
    public static final String LOGTAG = "DB";

    /**
     * Database helper.
     */
    SQLiteOpenHelper dbhelper;
    
    /**
     * Database.
     */
    SQLiteDatabase database;
    
    /**
     * Map of all users.
     */
    Map<String, User> users;

    /**
     * User table setup.
     */
    private static final String[] ALLCOLUMNS = {BankingDBOpenHelper.COLUMN_ID, BankingDBOpenHelper.COLUMN_TITLE, BankingDBOpenHelper.COLUMN_DESC};

    /**
     * Account table setup.
     */
    private static final String[] ACCOUNTCOLUMNS = {BankingDBOpenHelper.ACCOUNT_ID, BankingDBOpenHelper.ACCOUNT_USER, BankingDBOpenHelper.ACCOUNT_NAME, BankingDBOpenHelper.ACCOUNT_USERNAME, BankingDBOpenHelper.ACCOUNT_BALANCE, BankingDBOpenHelper.ACCOUNT_RATE};

    /**
     * Deposit table setup.
     */
    private static final String[] DEPOSITCOLUMNS = {BankingDBOpenHelper.DEPOSIT_ID, BankingDBOpenHelper.DEPOSIT_USER, BankingDBOpenHelper.DEPOSIT_NAME, BankingDBOpenHelper.DEPOSIT_REASON, BankingDBOpenHelper.DEPOSIT_AMOUNT, BankingDBOpenHelper.DEPOSIT_TIME1, BankingDBOpenHelper.DEPOSIT_TIME2};

    /**
     * Withdrawal table setup.
     */
    private static final String[] WITHDRAWALCOLUMNS = {BankingDBOpenHelper.WITHDRAWAL_ID, BankingDBOpenHelper.WITHDRAWAL_USER, BankingDBOpenHelper.WITHDRAWAL_NAME, BankingDBOpenHelper.WITHDRAWAL_REASON, BankingDBOpenHelper.WITHDRAWAL_AMOUNT, BankingDBOpenHelper.WITHDRAWAL_TIME1, BankingDBOpenHelper.WITHDRAWAL_TIME2};

    /**
     * Constructor initializing database handler.
     * 
     * @param context to initialize database.
     */
    public BankDataSource(Context context) {
        dbhelper = BankingDBOpenHelper.getInstance(context);
    }

    /**
     * Constructor to return database handler that has already been initialized.
     */
    public BankDataSource() {
        dbhelper = BankingDBOpenHelper.getInstance();
    }

    /**
     * Open database.
     */
    public void open() {
        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    /**
     * Close database.
     */
    public void close() {
        Log.i(LOGTAG, "Database closed");
        dbhelper.close();
    }

    /**
     * Retrieve all users from database.
     * 
     * @return Map of users
     */
    public Map<String, User> findAll() {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_BANK, ALLCOLUMNS, null, null, null, null, null);

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        users = cursorToMap(cursor);
        return users;

    }

    /**
     * Retrieve all deposits from database.
     * 
     * @return List of deposits.
     */
    public List<AbstractTransaction> findAllDeposits() {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_DEPOSIT, DEPOSITCOLUMNS, null, null, null, null, null);

        List<AbstractTransaction> list = cursorToDepositList(cursor);
        return list;
    }

    /**
     * Retrieve all withdrawals from database.
     * 
     * @return List of withdrawals.
     */
    public List<AbstractTransaction> findAllWithdrawals() {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_WITHDRAWAL, WITHDRAWALCOLUMNS, null, null, null, null, null);

        List<AbstractTransaction> list = cursorToWithdrawalList(cursor);
        return list;
    }

    /**
     * Retrieve specific account from database.
     * 
     * @param selectionArgs to filter database by
     * @param orderBy to order database by
     * @return List of accounts
     */
    public List<Account> findAccount(String[] selectionArgs, String orderBy) {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_ACCOUNT, ACCOUNTCOLUMNS, "user=?", selectionArgs, null, null, orderBy);

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        List<Account> accounts = cursorToAccountList(cursor);
        return accounts;

    }

    /**
     * Retrieve specific deposit from database.
     * 
     * @param selectionArgs to filter database by
     * @param orderBy to order database by
     * @return List of deposits
     */
    public List<AbstractTransaction> findDeposits(String[] selectionArgs, String orderBy) {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_DEPOSIT, DEPOSITCOLUMNS, "user=? AND name=?", selectionArgs, null, null, orderBy);
        List<AbstractTransaction> transactions = cursorToDepositList(cursor);
        return transactions;
    }

    /**
     * Retrieve specific withdrawal from database.
     * 
     * @param selectionArgs to filter database by
     * @param orderBy to order database by
     * @return List of withdrawals
     */
    public List<AbstractTransaction> findWithdrawals(String[] selectionArgs, String orderBy) {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_WITHDRAWAL, WITHDRAWALCOLUMNS, "user=? AND name=?", selectionArgs, null, null, orderBy);
        List<AbstractTransaction> transactions = cursorToWithdrawalList(cursor);
        return transactions;
    }

    /**
     * Retrieve withdrawals within certain time span for spending report.
     * 
     * @param selectionArgs to filter database by
     * @param orderBy to order database by
     * @return List of withdrawals for spending report
     */
    public List<AbstractTransaction> findWithdrawalsForSpendingReport(String[] selectionArgs, String orderBy) {
        Cursor cursor = database.query(BankingDBOpenHelper.TABLE_WITHDRAWAL, WITHDRAWALCOLUMNS, "user=? AND Time1 BETWEEN ? AND ?", selectionArgs, null, null, orderBy);
        List<AbstractTransaction> transactions = cursorToWithdrawalList(cursor);
        return transactions;
    }

    /**
     * Create map of users from database.
     * 
     * @param cursor to iterate through database
     * @return Map of users
     */
    private Map<String, User> cursorToMap(Cursor cursor) {
        users = new HashMap<String, User>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.COLUMN_TITLE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.COLUMN_DESC)));
                users.put(user.getName(), user);
            }
        }
        return users;
    }

    /**
     * Create list of accounts from database.
     * 
     * @param cursor to iterate through database
     * @return List of accounts
     */
    private List<Account> cursorToAccountList(Cursor cursor) {
        List<Account> accounts = new ArrayList<Account>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Account account = new Account();
                account.setId(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_ID)));
                account.setUser(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_USER)));
                account.setName(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_NAME)));
                account.setUsername(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_USERNAME)));
                account.setBalance(cursor.getDouble(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_BALANCE)));
                account.setRate(cursor.getDouble(cursor.getColumnIndex(BankingDBOpenHelper.ACCOUNT_RATE)));
                accounts.add(account);
            }
        }
        return accounts;
    }

    /**
     * Create list of deposits from database.
     * 
     * @param cursor to iterate through database
     * @return List of deposits
     */
    private List<AbstractTransaction> cursorToDepositList(Cursor cursor) {
        List<AbstractTransaction> transactions = new ArrayList<AbstractTransaction>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Deposit transaction = new Deposit();
                transaction.setId(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_ID)));
                transaction.setUser(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_USER)));
                transaction.setName(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_NAME)));
                transaction.setReason(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_REASON)));
                transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_AMOUNT)));
                transaction.setTimeOfTransaction(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_TIME1)));
                transaction.setTimeOfUser(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.DEPOSIT_TIME2)));
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    /**
     * Create list of withdrawals from database.
     * 
     * @param cursor to iterate through database
     * @return List of withdrawals
     */
    private List<AbstractTransaction> cursorToWithdrawalList(Cursor cursor) {
        List<AbstractTransaction> transactions = new ArrayList<AbstractTransaction>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Withdrawal transaction = new Withdrawal();
                transaction.setId(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_ID)));
                transaction.setUser(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_USER)));
                transaction.setName(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_NAME)));
                transaction.setReason(cursor.getString(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_REASON)));
                transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_AMOUNT)));
                transaction.setTimeOfTransaction(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_TIME1)));
                transaction.setTimeOfUser(cursor.getLong(cursor.getColumnIndex(BankingDBOpenHelper.WITHDRAWAL_TIME2)));
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    /**
     * Add user to database.
     * 
     * @param user to be added
     * @return User added
     */
    public User addToUser(User user) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.COLUMN_TITLE, user.getName());
        values.put(BankingDBOpenHelper.COLUMN_DESC, user.getPassword());
        long insertid = database.insert(BankingDBOpenHelper.TABLE_BANK, null, values);
        user.setId(insertid);
        return user;
    }

    /**
     * Add account to database.
     * 
     * @param account to be added
     * @return true if account added successfully
     */
    public boolean addToAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.ACCOUNT_USER, account.getUser());
        values.put(BankingDBOpenHelper.ACCOUNT_NAME, account.getName());
        values.put(BankingDBOpenHelper.ACCOUNT_USERNAME, account.getUsername());
        values.put(BankingDBOpenHelper.ACCOUNT_BALANCE, account.getBalance());
        values.put(BankingDBOpenHelper.ACCOUNT_RATE, account.getRate());

        long result = database.insert(BankingDBOpenHelper.TABLE_ACCOUNT, null, values);
        return (result != -1);
    }

    /**
     * Add deposit to database.
     * 
     * @param deposit to be added
     * @return true if deposit added successfully
     */
    public boolean addToDeposit(Deposit deposit) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.DEPOSIT_USER, deposit.getUser());
        values.put(BankingDBOpenHelper.DEPOSIT_NAME, deposit.getName());
        values.put(BankingDBOpenHelper.DEPOSIT_REASON, deposit.getReason());
        values.put(BankingDBOpenHelper.DEPOSIT_AMOUNT, deposit.getAmount());
        values.put(BankingDBOpenHelper.DEPOSIT_TIME1, deposit.getTimeOfTransaction());
        values.put(BankingDBOpenHelper.DEPOSIT_TIME2, deposit.getTimeOfUser());

        long result = database.insert(BankingDBOpenHelper.TABLE_DEPOSIT, null, values);
        return (result != -1);
    }

    /**
     * Add withdrawal to database.
     * 
     * @param withdrawal to be added.
     * @return true if withdrawal added successfully
     */
    public boolean addToWithdrawal(Withdrawal withdrawal) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.WITHDRAWAL_USER, withdrawal.getUser());
        values.put(BankingDBOpenHelper.WITHDRAWAL_NAME, withdrawal.getName());
        values.put(BankingDBOpenHelper.WITHDRAWAL_REASON, withdrawal.getReason());
        values.put(BankingDBOpenHelper.WITHDRAWAL_AMOUNT, withdrawal.getAmount());
        values.put(BankingDBOpenHelper.WITHDRAWAL_TIME1, withdrawal.getTimeOfTransaction());
        values.put(BankingDBOpenHelper.WITHDRAWAL_TIME2, withdrawal.getTimeOfUser());

        long result = database.insert(BankingDBOpenHelper.TABLE_WITHDRAWAL, null, values);
        return (result != -1);
    }

    /**
     * Update account in database.
     * 
     * @param account to be updated
     * @return true if account updated successfully
     */
    public boolean updateAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.ACCOUNT_USER, account.getUser());
        values.put(BankingDBOpenHelper.ACCOUNT_NAME, account.getName());
        values.put(BankingDBOpenHelper.ACCOUNT_USERNAME, account.getUsername());
        values.put(BankingDBOpenHelper.ACCOUNT_BALANCE, account.getBalance());
        values.put(BankingDBOpenHelper.ACCOUNT_RATE, account.getRate());

        String[] args = new String[] {account.getUser(), account.getName(), account.getUsername()};
        int result = database.update(BankingDBOpenHelper.TABLE_ACCOUNT, values, "user=? AND name=? AND username=?", args);
        return (result != -1);
    }

    /**
     * Update user in database.
     * 
     * @param user to be updated
     * @return true if user updated successfully
     */
    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(BankingDBOpenHelper.COLUMN_TITLE, user.getName());
        values.put(BankingDBOpenHelper.COLUMN_DESC, user.getPassword());

        String[] args = new String[] {user.getName()};
        int result = database.update(BankingDBOpenHelper.TABLE_BANK, values, "name=?", args);
        return (result != -1);
    }
}
