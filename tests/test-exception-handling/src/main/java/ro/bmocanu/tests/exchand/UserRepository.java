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

import ro.bmocanu.tests.exchand.exceptions.DataAccessException;
import ro.bmocanu.tests.exchand.model.User;

/**
 * @author mocanu
 */
public interface UserRepository extends Repository<User> {

    /**
     * Searches for the user with the given email and password. Returns <code>null</code> if no such
     * user could be found.
     * 
     * @param email
     * @param password
     * @return
     * @throws DataAccessException
     *             if an exception of any sort occurs during the repo operation
     */
    User findByEmailPassword( String email, String password ) throws DataAccessException;

}
