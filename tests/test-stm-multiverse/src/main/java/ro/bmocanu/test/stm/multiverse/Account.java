package ro.bmocanu.test.stm.multiverse;

import java.util.Date;

import org.multiverse.annotations.TransactionalObject;

/**
 * Test account implementation for Multiverse test.
 *
 * @author bogdan.mocanu
 */
@TransactionalObject
public class Account {

    private int balance;

    private Date lastUpdate;

    /**
     *
     */
    public Account( int startBalance ) {
        setBalance( startBalance );
    }

    /**
     * Returns the balance
     *
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance to the given value.
     *
     * @param balance the balance to set
     */
    public void setBalance( int balance ) {
        this.lastUpdate = new Date();
        if ( balance < 0 ) {
            throw new IllegalArgumentException( "Bang!! Balance can't be negative" );
        }
        this.balance = balance;
    }

    public void changeBalance( int delta ) {
        setBalance( balance + delta );
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Account [balance=" + balance + ", lastUpdate=" + lastUpdate + "]";
    }

}
