package com.CS1332.bankingapplication.views;

/**
 * Interface for CreateReportActivity.
 */
public interface ReportView {

    /**
     * Set title of report.
     * 
     * @param string to set as title
     */
    void setTitle(String string);

    /**
     * Set report details text.
     * 
     * @param string to set as text.
     */
    void setText(String string);

    /**
     * Get start date from user.
     * 
     * @return date as a string
     */
    String getStartDate();

    /**
     * Get end date from user.
     * 
     * @return date as a string
     */
    String getEndDate();

    /**
     * Set ClickListener.
     * 
     * @param listener to be set
     */
    void addSearchRequestNotifyCallback(ClickListener listener);
}
