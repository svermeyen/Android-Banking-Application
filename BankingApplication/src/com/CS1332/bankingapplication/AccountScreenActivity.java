package com.CS1332.bankingapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.CS1332.bankingapplication.presenters.BankingPresenter;
import com.CS1332.bankingapplication.views.ClickListener;

/**
 * Screen for accounts.
 */
public class AccountScreenActivity extends ListActivity {

    /**
     * Presenter.
     */
    ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        listener = BankingPresenter.getInstance();
    }

    @Override
    protected void onResume() {
        refreshDisplay();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.create_account:
                intent = new Intent(this, CreateAccountActivity.class);
                startActivity(intent);
                break;

            case R.id.build_report:
                intent = new Intent(this, CreateReportActivity.class);
                startActivity(intent);
                break;

            case R.id.my_account:
                intent = new Intent(this, MyAccountActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_screen, menu);
        return true;
    }

    /**
     * Refresh list adapter.
     */
    public void refreshDisplay() {
        setListAdapter(listener.getAccountAdapter(this));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.setAccount(position);
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }

}
