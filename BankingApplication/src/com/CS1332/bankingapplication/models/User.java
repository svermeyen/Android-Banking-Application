package com.CS1332.bankingapplication.models;

/**
 * User class.
 */
public class User {

    /**
     * Name of user.
     */
    private String name;
    /**
     * Password of user.
     */
    private String password;
    /**
     * Database id.
     */
    private long id;

    /**
     * User constructor.
     * 
     * @param s name of user.
     * @param p password of user.
     */
    public User(String s, String p) {
        name = s;
        password = p;
    }

    /**
     * User constructor.
     */
    public User() {
        name = null;
        password = null;
    }

    @Override
    public String toString() {
        return name + " " + password;
    }

    /**
     * Get password of user.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get name of user.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of user.
     * 
     * @param name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set password of user.
     * 
     * @param password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get database id.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Set database id.
     * 
     * @param insertid to set
     */
    public void setId(long insertid) {
        this.id = insertid;
    }

    /**
     * Check password of user.
     * 
     * @param password to check
     * @return true if passwords match
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Change password of user.
     * 
     * @param password to be changed to
     */
    public void changePassword(String password) {
        this.password = password;
    }

}
