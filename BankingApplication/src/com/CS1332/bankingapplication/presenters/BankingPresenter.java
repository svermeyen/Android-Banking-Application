package com.CS1332.bankingapplication.presenters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.CS1332.bankingapplication.models.AbstractTransaction;
import com.CS1332.bankingapplication.models.Account;
import com.CS1332.bankingapplication.models.BankingModel;
import com.CS1332.bankingapplication.models.Deposit;
import com.CS1332.bankingapplication.models.Model;
import com.CS1332.bankingapplication.models.User;
import com.CS1332.bankingapplication.models.Withdrawal;
import com.CS1332.bankingapplication.views.BankingView;
import com.CS1332.bankingapplication.views.ChangePasswordView;
import com.CS1332.bankingapplication.views.ClickListener;
import com.CS1332.bankingapplication.views.ReportView;
import com.CS1332.bankingapplication.views.TransactionView;
import com.CS1332.bankingapplication.views.UserView;

/**
 * Presenter class.
 */
public class BankingPresenter implements ClickListener {
    /**
     * Presenter.
     */
    private static BankingPresenter presenter;
    /**
     * Login and register views.
     */
    private BankingView bankView;
    /**
     * Transaction view.
     */
    private TransactionView transactionView;
    /**
     * Change password view.
     */
    private ChangePasswordView changePasswordView;
    /**
     * User view.
     */
    private UserView userView;
    /**
     * Report view.
     */
    private ReportView reportView;
    /**
     * Model.
     */
    private Model model;
    /**
     * User name of logged in user.
     */
    public static String username;
    /**
     * Registering or not.
     */
    private boolean isRegistering;
    /**
     * Depositing or not.
     */
    private boolean isDepositing;
    /**
     * Withdrawn yet or not.
     */
    private boolean withdrawInitialized = false;
    /**
     * Deposited yet or not.
     */
    private boolean depositInitialized = false;
    /**
     * Date formatter.
     */
    SimpleDateFormat dateFormat;
    /**
     * Account of user.
     */
    Account account;

    /**
     * Presenter constructor.
     * 
     * @param model to set
     */
    public BankingPresenter(BankingModel model) {
        this.model = model;
        List<AbstractTransaction> deposits = model.findAllDeposits();
        List<AbstractTransaction> withdrawals = model.findAllWithdrawals();
        if (!deposits.isEmpty()) {
            depositInitialized = true;
        }
        if (!withdrawals.isEmpty()) {
            withdrawInitialized = true;
        }
    }

    @Override
    public void onLoginClick() {
        String username = bankView.getUsername();
        String password = bankView.getPassword();
        if (isRegistering && (username.length() > 0) && (password.length() > 0) && (!model.isUser(username))) {
            model.createUser(username, password);
            bankView.transition(true);
        } else if (!isRegistering && (username.length() > 0) && (password.length() > 0)) {
            boolean isUser = model.isUser(username, password);
            if (isUser) {
                BankingPresenter.username = username;
                bankView.transition(isUser);
            } else {
                bankView.setPrompt("Login failed");
            }
        } else {
            bankView.setPrompt("Account information invalid. Please register again.");
        }
    }

    @Override
    public void onCreateAccountClick() {

        String name = userView.getName();
        String display = userView.getDisplay();
        Double balance = userView.getBalance();
        Double mir = userView.getMir();

        if (name.trim().length() > 0 && display.trim().length() > 0 && balance != null && mir != null) {
            model.addToAccount(BankingPresenter.username, name, display, balance, mir);
            userView.setPrompt("");
        } else {
            userView.setPrompt("More account information required. Please fill in all data fields.");
        }

    }

    @Override
    public void onChangePasswordClick() {
        String oldP = changePasswordView.getOldPassword();
        String newP = changePasswordView.getNewPassword();
        String confirmNewP = changePasswordView.confirmNewPassword();

        if (!model.isUser(username, oldP)) {
            changePasswordView.setPrompt("Incorrect old password! Please try again.");
        } else if ((newP.equals(confirmNewP))) {
            model.updateUser(new User(username, newP));
            model.initialize();
            changePasswordView.setPrompt("Success!");
        } else {
            changePasswordView.setPrompt("New passwords do not match! Please try again");
        }
    }

    @Override
    public void onCreateTransactionClick() {
        String reason = transactionView.getReason();
        Double amount = transactionView.getAmount();
        Integer date = transactionView.getDate();

        if (reason.length() > 0 && amount != null && isDepositing) {
            depositInitialized = true;
            Deposit deposit = new Deposit(reason, amount, this.account);
            this.account = deposit.modifyAccount(this.account);
            model.updateAccount(this.account);
            model.addToDeposit(deposit);
            transactionView.setPrompt("");
        } else if (reason.length() > 0 && amount != null && !isDepositing) {
            withdrawInitialized = true;
            Withdrawal withdrawal = new Withdrawal(reason, amount, this.account);
            this.account = withdrawal.modifyAccount(this.account);
            model.updateAccount(this.account);
            model.addToWithdrawal(withdrawal);
            transactionView.setPrompt("");
        } else {
            transactionView.setPrompt("Please fill in all data fields.");
        }
    }

    @Override
    public void onCreateReportClick() throws ParseException {
        String startDate = reportView.getStartDate();
        String endDate = reportView.getEndDate();
        startDate = startDate.trim();
        endDate = endDate.trim();

        if (startDate.length() > 0 && endDate.length() > 0) {
            dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss.SSS", Locale.ENGLISH);
            Date d1 = dateFormat.parse(startDate + " 00:00:00.000");
            Date d2 = dateFormat.parse(endDate + " 23:59:59.999");
            long startTimeInMillisSinceEpoch = d1.getTime();
            long endTimeInMillisSinceEpoch = d2.getTime();
            String[] args = new String[] {BankingPresenter.username, Long.toString(startTimeInMillisSinceEpoch), Long.toString(endTimeInMillisSinceEpoch)};
            reportView.setTitle("Spending Category Report for " + BankingPresenter.username + "\n" + "from " + startDate + " to " + endDate);
            String description = model.getSpendingReportText(args);
            reportView.setText(description);

        }

    }

    @Override
    public void setAccount(int position) {
        Account account = getAccountToModify(position);
        this.account = account;
    }

    @Override
    public boolean isWithdrawInitialized() {
        return withdrawInitialized;
    }

    @Override
    public boolean isDepositInitialized() {
        return depositInitialized;
    }

    /**
     * Checks if user is depositing.
     * 
     * @return true if depositing.
     */
    public boolean getisDepositing() {
        return isDepositing;
    }

    @Override
    public void setisDepositing(boolean isDepositing) {
        this.isDepositing = isDepositing;
    }

    /**
     * Singleton method for presenter.
     * 
     * @return instance of presenter.
     */
    public static BankingPresenter getInstance() {
        if (presenter == null) {
            presenter = new BankingPresenter(new BankingModel());
        }
        return presenter;
    }

    @Override
    public void setBankView(BankingView bankView) {
        this.bankView = bankView;
        // this.bankView.addSearchRequestNotifyCallback(BankingPresenter.getInstance());
    }

    @Override
    public void setUserView(UserView userView) {
        this.userView = userView;
        // this.userView.addSearchRequestNotifyCallback(BankingPresenter.getInstance());
    }

    @Override
    public void setTransactionView(TransactionView transactionView) {
        this.transactionView = transactionView;
        // this.transactionView.addSearchRequestNotifyCallback(BankingPresenter.getInstance());
    }

    @Override
    public void setChangePasswordView(ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
    }

    @Override
    public void setReportView(ReportView reportView) {
        this.reportView = reportView;
        // this.reportView.addSearchRequestNotifyCallback(BankingPresenter.getInstance());
    }

    @Override
    public void setisRegistering(boolean isRegistering) {
        this.isRegistering = isRegistering;
    }

    @Override
    public ArrayAdapter<Account> getAccountAdapter(Context cxt) {
        String[] args = new String[] {BankingPresenter.username};
        List<Account> accounts = model.findAccount(args, "balance ASC");
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(cxt, android.R.layout.simple_list_item_1, accounts);
        return adapter;
    }

    @Override
    public ArrayAdapter<AbstractTransaction> getDepositAdapter(Context cxt) {
        String[] args = new String[] {BankingPresenter.username, account.getName()};
        List<AbstractTransaction> transactions = model.findDeposits(args, "amount ASC");
        ArrayAdapter<AbstractTransaction> adapter = new ArrayAdapter<AbstractTransaction>(cxt, android.R.layout.simple_list_item_1, transactions);
        return adapter;
    }

    @Override
    public ArrayAdapter<AbstractTransaction> getWithdrawalAdapter(Context cxt) {
        String[] args = new String[] {BankingPresenter.username, account.getName()};
        List<AbstractTransaction> transactions = model.findWithdrawals(args, "amount DESC");
        ArrayAdapter<AbstractTransaction> adapter = new ArrayAdapter<AbstractTransaction>(cxt, android.R.layout.simple_list_item_1, transactions);
        return adapter;
    }

    
    /**
     * Get the spending report list.
     * 
     * @param cxt to initialize
     * @param args to filter database by
     * @return List of transactions.
     */
    public List<AbstractTransaction> getSpendingReportList(Context cxt, String[] args) {
        List<AbstractTransaction> transactions = model.findWithdrawalsForSpendingReport(args, "amount DESC");
        return transactions;
    }

    /**
     * Selects account from list to modify.
     * 
     * @param position in list
     * @return account
     */
    public Account getAccountToModify(int position) {
        String[] args = new String[] {BankingPresenter.username};
        List<Account> accounts = model.findAccount(args, "balance ASC");
        Account account = accounts.get(position);
        return account;
    }

}
