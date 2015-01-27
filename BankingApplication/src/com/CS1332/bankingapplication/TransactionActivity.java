package com.CS1332.bankingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.CS1332.bankingapplication.presenters.BankingPresenter;
import com.CS1332.bankingapplication.views.ClickListener;

/**
 * Transaction type selection screen.
 */
public class TransactionActivity extends Activity {

    /**
     * Presenter.
     */
    ClickListener listener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        listener = BankingPresenter.getInstance();

    }

    /**
     * Deposit button.
     * 
     * @param v view
     */
    public void gotoCreateDepositActivity(View v) {
        Intent intent = new Intent(this, CreateTransactionActivity.class);
        listener.setisDepositing(true);
        startActivity(intent);
        finish();
        return;
    }

    /**
     * Withdraw button.
     * 
     * @param v view
     */
    public void gotoCreateWithdrawalActivity(View v) {
        Intent intent = new Intent(this, CreateTransactionActivity.class);
        listener.setisDepositing(false);
        startActivity(intent);
        finish();
        return;
    }

    /**
     * Deposit history button.
     * 
     * @param v view
     */
    public void gotoDepositHistory(View v) {
        if (listener.isDepositInitialized()) {
            Intent intent = new Intent(this, DepositHistoryActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Withdrawal history button.
     * 
     * @param v view
     */
    public void gotoWithdrawalHistory(View v) {
        if (listener.isWithdrawInitialized()) {
            Intent intent = new Intent(this, WithdrawalHistoryActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
