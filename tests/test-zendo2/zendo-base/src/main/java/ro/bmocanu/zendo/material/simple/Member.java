/**
 * 
 */
package ro.bmocanu.zendo.material.simple;

import java.util.Set;

/**
 * A club has multiple members, a member has multiple accounts.
 * 
 * @author mocanu
 */
public class Member extends Base {

    public String firstName;

    public String lastName;

    public int age;

    public Set<Account> accounts;

    /**
     * Returns the firstName
     * 
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName to the given value.
     * 
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    /**
     * Returns the lastName
     * 
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName to the given value.
     * 
     * @param lastName
     *            the lastName to set
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Returns the age
     * 
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age to the given value.
     * 
     * @param age
     *            the age to set
     */
    public void setAge( int age ) {
        this.age = age;
    }

    /**
     * Returns the accounts
     * 
     * @return the accounts
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * Sets the accounts to the given value.
     * 
     * @param accounts
     *            the accounts to set
     */
    public void setAccounts( Set<Account> accounts ) {
        this.accounts = accounts;
    }

}
