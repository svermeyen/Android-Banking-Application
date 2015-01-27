package com.CS1332.bankingapplication.views;

import java.text.ParseException;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.CS1332.bankingapplication.models.AbstractTransaction;
import com.CS1332.bankingapplication.models.Account;

/**
 * Interface for presenter class.
 */

public interface ClickListener {

    /**
    * Login user.
    */
    void onLoginClick();

    /**
    * Create account.
    */
    void onCreateAccountClick();

    /**
    * Create transaction.
    */
    void onCreateTransactionClick();

  
    /**
     * Create report.
     * 
     * @throws ParseException on date parse
     */
    void onCreateReportClick() throws ParseException;

    /**
     * Set UserView to communicate.
     * 
     * @param v UserView to set
     */
    void setUserView(UserView v);

    /**
     * Set BankingView to communicate.
     * 
     * @param bankView BankingView to set
     */
    void setBankView(BankingView bankView);

    /**
     * Set TransactionView to communicate.
     * 
     * @param transactionView TransactionView to set
     */
    void setTransactionView(TransactionView transactionView);

    /**
     * Set ReportView to communicate.
     * 
     * @param reportView ReportView to set
     */
    void setReportView(ReportView reportView);

    /**
     * Set if user is registering.
     * 
     * @param isRegistering true if registering
     */
    void setisRegistering(boolean isRegistering);

    /**
     * Set if user is making a deposit.
     * 
     * @param isDepositing true if depositing
     */
    void setisDepositing(boolean isDepositing);

    /**
     * Checks if a deposit has been made.
     * 
     * @return true if deposit has been made
     */
    boolean isDepositInitialized();

    /**
     * Checks if a withdrawal has been made.
     * 
     * @return true if withdrawal has been made
     */
    boolean isWithdrawInitialized();

    /**
     * Get account list adapter for interactive list.
     * 
     * @param cxt to initialize adapter
     * @return adapter to display list
     */
    ArrayAdapter<Account> getAccountAdapter(Context cxt);

    /**
     * Get deposit list adapter for interactive list.
     * 
     * @param cxt to initialize adapter
     * @return adapter to display list
     */
    ArrayAdapter<AbstractTransaction> getDepositAdapter(Context cxt);

    /**
     * Get withdrawal list adapter for interactive list.
     * 
     * @param cxt to initialize adapter
     * @return adapter to display list
     */
    ArrayAdapter<AbstractTransaction> getWithdrawalAdapter(Context cxt);

    /**
     * Set the account based on the user selection in interactive list.
     * 
     * @param position in list
     */
    void setAccount(int position);

    /**
     * SetChangePasswordView to communicate.
     * 
     * @param changePasswordActivity to set
     */
    void setChangePasswordView(ChangePasswordView changePasswordActivity);

    /**
     * Change password.
     */
    void onChangePasswordClick();
}
