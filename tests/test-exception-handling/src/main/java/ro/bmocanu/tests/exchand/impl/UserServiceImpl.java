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

package ro.bmocanu.tests.exchand.impl;

import ro.bmocanu.tests.exchand.UserRepository;
import ro.bmocanu.tests.exchand.UserService;
import ro.bmocanu.tests.exchand.exceptions.DataAccessException;
import ro.bmocanu.tests.exchand.exceptions.ServiceException;
import ro.bmocanu.tests.exchand.model.User;

/**
 * 
 *
 * @author mocanu
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUser( String firstName, String lastName, String email, String password ) throws ServiceException {
        User user = new User( firstName, lastName, email, password );
        userRepo.insert( user );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserCredentials( String email, String password ) {
        try {
            User checkedUser = userRepo.findByEmailPassword( email, password );
            return checkedUser != null;
        } catch ( DataAccessException exception ) {
            // log
            return false;
        }
    }

}
