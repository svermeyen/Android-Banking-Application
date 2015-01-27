package com.CS1332.bankingapplication.views;

/**
 * Interface for CreateAccountActivity.
 */
public interface UserView {
    
    /**
     * Get name of account from input field.
     * 
     * @return name
     */
    String getName();

    /**
     * Get display name of account from input field.
     * 
     * @return display name
     */
    String getDisplay();

    /**
     * Get balance of account from input field.
     * 
     * @return balance
     */
    Double getBalance();

    /**
     * Get monthly interest rate of account from input field.
     * 
     * @return monthly interest rate
     */
    Double getMir();

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
