package com.CS1332.bankingapplication.views;

/**
 * Interface for login or register activity.
 * 
 */
public interface BankingView {
    /**
     * Get username from input field.
     * 
     * @return username
     */
    String getUsername();

    /**
     * Get password from input field.
     * 
     * @return password
     */
    String getPassword();

    /**
     * Set the presenter as the ClickListener.
     * 
     * @param listener to set
     */
    void addSearchRequestNotifyCallback(ClickListener listener);

    /**
     * Transition screens.
     * 
     * @param isUser if true then transition screen
     */
    void transition(boolean isUser);

    /**
     * Set prompt on screen.
     * 
     * @param string to set on screen
     */
    void setPrompt(String string);
}
