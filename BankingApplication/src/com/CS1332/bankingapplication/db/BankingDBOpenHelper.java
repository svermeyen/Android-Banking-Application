package com.CS1332.bankingapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database setup class.
 * 
 * @author Scott Vermeyen
 */

public class BankingDBOpenHelper extends SQLiteOpenHelper {

    /**
     * Instance class.
     */
    private static BankingDBOpenHelper instance = null;
    
    /**
     * Context necessary to initialize.
     */
    
    private static Context mCtx;
    /**
     * Database name.
     */
    private static final String DATABASE_NAME = "bank.db";
    
    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 21;

    /**
     * User table name.
     */
    public static final String TABLE_BANK = "User";
    
    /**
     * User table column.
     */
    public static final String COLUMN_ID = "bankId";
    
    /**
     * User table column.
     */
    public static final String COLUMN_TITLE = "name";
    
    /**
     * User table column.
     */
    public static final String COLUMN_DESC = "password";
    
    /**
     * User table column.
     */
    public static final String COLUMN_INFO = "account";

    /**
     * Account table name.
     */
    public static final String TABLE_ACCOUNT = "Account";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_ID = "accountId";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_USER = "user";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_NAME = "name";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_USERNAME = "username";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_INFO = "account";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_BALANCE = "balance";
    
    /**
     * Account table column.
     */
    public static final String ACCOUNT_RATE = "rate";
    
    /**
     * Deposit table name.
     */
    public static final String TABLE_DEPOSIT = "Deposit";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_ID = "depositId";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_USER = "user";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_NAME = "name";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_REASON = "reason";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_AMOUNT = "amount";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_TIME1 = "Time1";
    
    /**
     * Deposit table column.
     */
    public static final String DEPOSIT_TIME2 = "Time2";
    

    /**
     * Withdrawal table name.
     */
    public static final String TABLE_WITHDRAWAL = "Withdrawal";
    
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_ID = "withdrawalId";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_USER = "user";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_NAME = "name";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_REASON = "reason";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_AMOUNT = "amount";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_TIME1 = "Time1";
    
    /**
     * Withdrawal table column.
     */
    public static final String WITHDRAWAL_TIME2 = "Time2";

    /**
     * User table creation string.
     */
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_BANK + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESC + " TEXT " + ")";

    /**
     * Account table creation string.
     */
    private static final String TABLE_ACCOUNT_CREATE = "CREATE TABLE " + TABLE_ACCOUNT + " (" + ACCOUNT_ID + " INTEGER PRIMARY KEY, " + ACCOUNT_USER + " TEXT, " + ACCOUNT_NAME + " TEXT, " + ACCOUNT_USERNAME + " TEXT, " + ACCOUNT_BALANCE + " NUMERIC, " + ACCOUNT_RATE + " NUMERIC " + ")";

    /**
     * Deposit table creation string.
     */
    private static final String TABLE_DEPOSIT_CREATE = "CREATE TABLE " + TABLE_DEPOSIT + " (" + DEPOSIT_ID + " INTEGER PRIMARY KEY, " + DEPOSIT_USER + " TEXT, " + DEPOSIT_NAME + " TEXT, " + DEPOSIT_REASON + " TEXT, " + DEPOSIT_AMOUNT + " NUMERIC, " + DEPOSIT_TIME1 + " NUMERIC, " + DEPOSIT_TIME2 + " NUMERIC " + ")";

    /**
     * Withdrawal table creation string.
     */
    private static final String TABLE_WITHDRAWAL_CREATE = "CREATE TABLE " + TABLE_WITHDRAWAL + " (" + WITHDRAWAL_ID + " INTEGER PRIMARY KEY, " + WITHDRAWAL_USER + " TEXT, " + WITHDRAWAL_NAME + " TEXT, " + WITHDRAWAL_REASON + " TEXT, " + WITHDRAWAL_AMOUNT + " NUMERIC, " + WITHDRAWAL_TIME1 + " NUMERIC, " + WITHDRAWAL_TIME2 + " NUMERIC " + ")";

    /**
     * Constructor to initialize database.
     * 
     * @param context to initialize database.
     */
    private BankingDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = context;
    }

    /**
     * Singleton method that returns instance or creates it.
     * 
     * @param ctx to initiate database
     * @return instance of class
     */
    public static BankingDBOpenHelper getInstance(Context ctx) {
        if (instance == null) {

            instance = new BankingDBOpenHelper(ctx.getApplicationContext());

        }
        return instance;
    }

    /**
     * Singleton method that can instantiate database or return existing one.
     * 
     * @return instance of class
     */
    public static BankingDBOpenHelper getInstance() {
        return getInstance(mCtx);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_ACCOUNT_CREATE);
        db.execSQL(TABLE_DEPOSIT_CREATE);
        db.execSQL(TABLE_WITHDRAWAL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPOSIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WITHDRAWAL);

        onCreate(db);
    }

}
