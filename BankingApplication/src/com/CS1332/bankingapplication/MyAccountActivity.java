package com.CS1332.bankingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.CS1332.bankingapplication.presenters.BankingPresenter;
import com.CS1332.bankingapplication.views.ClickListener;

/**
 * Account list screen.
 */
public class MyAccountActivity extends Activity {

    /**
     * Presenter.
     */
    ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        listener = BankingPresenter.getInstance();
    }

    /**
     * Change password button.
     * 
     * @param v view
     */
    public void onChangePasswordClick(View v) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
