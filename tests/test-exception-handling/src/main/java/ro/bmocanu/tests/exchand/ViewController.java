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

package ro.bmocanu.tests.exchand;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

import ro.bmocanu.tests.exchand.exceptions.ServiceException;
import ro.bmocanu.tests.exchand.utils.ViewUtils;

/**
 * @author mocanu
 */
public class ViewController {
    private static final Logger LOG = Logger.getLogger( ViewController.class );

    private UserService userService;

    public String onCreateUserClick() {
        String firstName = null; // get the first name
        String lastName = null; // get the last name
        String email = null; // get the email
        String password = null; // get the password

        try {
            userService.createUser( firstName, lastName, email, password );
            return ViewUtils.forwardTo( "userCreated" );

        } catch ( ServiceException exception ) {
            LOG.error( MessageFormat.format( "Cannot create user with firstName={0}, lastName={1}, email={2}, password={3}", firstName, lastName, email, password ), exception );
            ViewUtils.showErrorMessage( "Error creating the user! Please try again.\n If this error appears again, please contact an administrator" );
            return ViewUtils.forwardTo( "createUser" );
        }
    }

    public String authenticateUser() {
        String email = null; // get the email
        String password = null; // get the password

        if ( userService.checkUserCredentials( email, password ) ) {
            return ViewUtils.forwardTo( "userAuthenticated" );
        } else {
            return ViewUtils.forwardTo( "wrongCredentials" );
        }
    }

}
