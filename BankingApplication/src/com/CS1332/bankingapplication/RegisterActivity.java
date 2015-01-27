package com.CS1332.bankingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.CS1332.bankingapplication.db.BankDataSource;
import com.CS1332.bankingapplication.presenters.BankingPresenter;
import com.CS1332.bankingapplication.views.BankingView;
import com.CS1332.bankingapplication.views.ClickListener;

/**
 * Register screen.
 */
public class RegisterActivity extends Activity implements BankingView {

    /**
     * Presenter.
     */
    private ClickListener listener;
    /**
     * Name of user.
     */
    EditText nameField;
    /**
     * Password of user.
     */
    EditText passwordField;
    /**
     * Screen prompt.
     */
    TextView prompt;
    /**
     * Database datasource.
     */
    BankDataSource datasource;
    /**
     * Registering or not.
     */
    private boolean isRegistering = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        listener = BankingPresenter.getInstance();
        listener.setBankView(this);

        nameField = (EditText) findViewById(R.id.editText1);
        passwordField = (EditText) findViewById(R.id.editText2);
        prompt = (TextView) findViewById(R.id.textView1);

    }

    /**
     * Register button.
     * 
     * @param v view
     */
    public void onRegisterClick(View v) {
        listener.setBankView(this);
        listener.setisRegistering(isRegistering);
        listener.onLoginClick();
    }

    @Override
    public void addSearchRequestNotifyCallback(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public String getUsername() {
        return nameField.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordField.getText().toString();
    }
    
    @Override
    public void transition(boolean isUser) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    
    @Override
    public void setPrompt(String msg) {
        prompt.setText(msg);
        return;
    }
}
