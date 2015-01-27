package com.CS1332.bankingapplication.views;

/**
 * Interface for CreateTransactionActivity.
 */
public interface TransactionView {
    
    /**
     * Get reason for transaction.
     * 
     * @return reason as String
     */
    String getReason();

    /**
     * Get amount of transaction.
     * 
     * @return amount as Double
     */
    Double getAmount();

    /**
     * Get date of transaction.
     * 
     * @return date as Integer
     */
    Integer getDate();

    /**
     * Set ClickListener.
     * 
     * @param listener to be set
     */
    void addSearchRequestNotifyCallback(ClickListener listener);

    /**
     * Set prompt on screen.
     * 
     * @param string to be set
     */
    void setPrompt(String string);
}
