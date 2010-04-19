/*- 
 * Copyright Bogdan Mocanu, 2010
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package ro.bmocanu.tests.exchand.model;

public class User extends BaseDTO {
    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

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
     * Returns the email
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email to the given value.
     * 
     * @param email
     *            the email to set
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Returns the password
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the given value.
     * 
     * @param password
     *            the password to set
     */
    public void setPassword( String password ) {
        this.password = password;
    }

}
