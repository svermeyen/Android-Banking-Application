package com.CS1332.bankingapplication;

import java.text.ParseException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.CS1332.bankingapplication.presenters.BankingPresenter;
import com.CS1332.bankingapplication.views.ClickListener;
import com.CS1332.bankingapplication.views.ReportView;

/**
 * Report creation screen.
 */
public class CreateReportActivity extends FragmentActivity implements ReportView {

    /**
     * Presenter.
     */
    ClickListener listener;
    /**
     * Start date of report.
     */
    static EditText startDate;
    /**
     * End date of report.
     */
    static EditText endDate;
    /**
     * Title of report.
     */
    TextView title;
    /**
     * Report text.
     */
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        listener = BankingPresenter.getInstance();

        title = (TextView) findViewById(R.id.report_title);
        text = (TextView) findViewById(R.id.report_text);
        startDate = (EditText) findViewById(R.id.editText1);
        endDate = (EditText) findViewById(R.id.editText2);

    }

    /**
     * Create report button.
     * 
     * @param v view
     */
    public void onCreateReportClick(View v) {
        listener.setReportView(this);
        try {
            listener.onCreateReportClick();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return;

    }

    @Override
    public void addSearchRequestNotifyCallback(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setTitle(String string) {
        title.setText(string);
        return;
    }

    @Override
    public void setText(String string) {
        text.setText(string);
        return;
    }

    @Override
    public String getStartDate() {
        return startDate.getText().toString();
    }

    @Override
    public String getEndDate() {
        return endDate.getText().toString();
    }

    /**
     * Get start date from user.
     * 
     * @param v view
     */
    public void selectStartDate(View v) {
        DialogFragment newFragment = new StartDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /**
     * Get end date from user.
     * 
     * @param v view
     */
    public void selectEndDate(View v) {
        DialogFragment newFragment = new EndDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /**
     * Set start date in input field.
     * 
     * @param year of date
     * @param month of date
     * @param day of date
     */
    public static void populateStartDate(int year, int month, int day) {
        startDate.setText(month + "/" + day + "/" + year);
    }

    /**
     * Set end date in input field.
     * 
     * @param year of date
     * @param month of date
     * @param day of date
     */
    public static void populateEndDate(int year, int month, int day) {
        endDate.setText(month + "/" + day + "/" + year);
    }

    /**
     * Inner class for start date picker.
     */
    public static class StartDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            populateStartDate(year, month + 1, day);
        }

    }

    /**
     * Inner class for end date picker.
     */
    public static class EndDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            populateEndDate(year, month + 1, day);
        }

    }
}
