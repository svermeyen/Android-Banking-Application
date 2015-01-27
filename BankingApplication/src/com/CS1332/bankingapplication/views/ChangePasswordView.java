package com.CS1332.bankingapplication.views;

/**
 * Interface for ChangePasswordActivity.
 */

public interface ChangePasswordView {

    /**
     * Get old password from input field.
     * 
     * @return old password
     */
    String getOldPassword();

    /**
     * Get new password from input field.
     * 
     * @return new password
     */
    String getNewPassword();

    /**
     * Get new password confirmation from input field.
     * 
     * @return new password confirmation
     */
    String confirmNewPassword();

    /**
     * Set presenter as ClickListener.
     * 
     * @param listener to set.
     */
    void addSearchRequestNotifyCallback(ClickListener listener);

    /**
     * Set prompt on screen.
     * 
     * @param string to set
     */
    void setPrompt(String string);
}
