/**
 * 
 */
package ro.bmocanu.zendo.material.simple;

import java.util.Date;

/**
 * A member can have multiple accounts.
 * 
 * @author mocanu
 */
public class Account extends Base {

    private String accountNo;

    private long balance;

    private Date lastUpdate;

    /**
     * Returns the accountNo
     * 
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the accountNo to the given value.
     * 
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo( String accountNo ) {
        this.accountNo = accountNo;
    }

    /**
     * Returns the balance
     * 
     * @return the balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * Sets the balance to the given value.
     * 
     * @param balance
     *            the balance to set
     */
    public void setBalance( long balance ) {
        this.balance = balance;
    }

    /**
     * Returns the lastUpdate
     * 
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the lastUpdate to the given value.
     * 
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate( Date lastUpdate ) {
        this.lastUpdate = lastUpdate;
    }

}
