package com.CS1332.bankingapplication.models;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.CS1332.bankingapplication.db.BankDataSource;

/**
 * Model class.
 */
public class BankingModel implements Model {

    /**
     * Map of users.
     */
    private Map<String, User> users;
    /**
     * Database datasource.
     */
    private BankDataSource datasource;
    /**
     * Total for report.
     */
    private Double total;

    /**
     * Get total for report.
     * 
     * @return total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Constructor for BankingModel.
     */
    public BankingModel() {
        this.datasource = new BankDataSource();
        initialize();
    }

    @Override
    public boolean isUser(final String username) {
        User user = users.get(username);
        return user != null;

    }

    @Override
    public boolean isUser(final String username, final String password) {
        User user = users.get(username);
        return user != null && user.checkPassword(password);

    }
    
    @Override
    public void changePassword(final String username, final String password) {
        User user = users.get(username);
        user.changePassword(password);
        datasource.open();
        datasource.updateUser(user);
        datasource.close();
        return;
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public BankDataSource getDatasource() {
        return datasource;
    }
    
    @Override
    public void openDatasource() {
        datasource.open();
    }
    
    @Override
    public void closeDatasource() {
        datasource.close();
    }
    
    
    /**
     * Set datasource to model.
     * 
     * @param datasource to set
     */
    public void setDatasource(BankDataSource datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public void createUser(String username, String password) {
        datasource.open();
        datasource.addToUser(new User(username, password));
        datasource.close();
        initialize();
        return;
    }
    
    @Override
    public void addToAccount(String username, String name, String display, Double balance, Double mir) {
        datasource.open();
        datasource.addToAccount(new Account(username, name, display, balance, mir));
        datasource.close();
        return;
    }
    
    @Override
    public void updateUser(User user) {
        datasource.open();
        datasource.updateUser(user);
        datasource.close();
        return;
    }
    
    @Override
    public void updateAccount(Account account) {
        datasource.open();
        datasource.updateAccount(account);
        datasource.close();
        return;
    }
    
    @Override
    public void addToDeposit(Deposit deposit) {
        datasource.open();
        datasource.addToDeposit(deposit);
        datasource.close();
        return;
    }
    
    @Override
    public void addToWithdrawal(Withdrawal withdrawal) {
        datasource.open();
        datasource.addToWithdrawal(withdrawal);
        datasource.close();
        return;
    }
    
    @Override
    public List<Account> findAccount(String[] args, String orderBy) {
        datasource.open();
        List<Account> list = datasource.findAccount(args, orderBy);
        datasource.close();
        return list;
    }
    
    @Override
    public List<AbstractTransaction> findAllDeposits() {
        datasource.open();
        List<AbstractTransaction> list = datasource.findAllDeposits();
        datasource.close();
        return list;
    }
    
    @Override
    public List<AbstractTransaction> findAllWithdrawals() {
        datasource.open();
        List<AbstractTransaction> list = datasource.findAllWithdrawals();
        datasource.close();
        return list;
    }
    
    @Override
    public List<AbstractTransaction> findDeposits(String[] args, String orderBy) {
        datasource.open();
        List<AbstractTransaction> list = datasource.findDeposits(args, orderBy);
        datasource.close();
        return list;
    }
    
    @Override
    public List<AbstractTransaction> findWithdrawals(String[] args, String orderBy) {
        datasource.open();
        List<AbstractTransaction> list = datasource.findWithdrawals(args, orderBy);
        datasource.close();
        return list;
    }
    
    @Override
    public List<AbstractTransaction> findWithdrawalsForSpendingReport(String[] args, String orderBy) {
        datasource.open();
        List<AbstractTransaction> list = datasource.findWithdrawalsForSpendingReport(args, orderBy);
        datasource.close();
        return list;
    }
    
    @Override
    public String getSpendingReportText(String[] args) {
        datasource.open();
        List<AbstractTransaction> report = datasource.findWithdrawalsForSpendingReport(args, "Time1 ASC");
        datasource.close();
        return listToString(report);
    }
    
    
    /**
     * Convert list to string.
     * 
     * @param list to be converted
     * @return string
     */
    public String listToString(List<AbstractTransaction> list) {
        StringBuilder text = new StringBuilder();
        Double total = 0.0;
        Iterator<AbstractTransaction> myIterator = list.iterator();
        while (myIterator.hasNext()) {
            AbstractTransaction t = myIterator.next();
            text.append(t.toString() + "\n");
            total = total + t.getAmount();
        }
        text.append("Total " + total);
        return text.toString();

    }
    
    @Override
    public final void initialize() {
        this.datasource.open();
        users = this.datasource.findAll();
        if (users.isEmpty()) {
            this.datasource.addToUser(new User("admin", "pass123"));
            users = this.datasource.findAll();
        }
        this.datasource.close();

    }
}
