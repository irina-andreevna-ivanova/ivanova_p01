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
import ro.bmocanu.tests.exchand.model.BaseDTO;

public interface Repository<ObjectType extends BaseDTO> {

    /**
     * Inserts the given DTO in the persistent repository.
     * 
     * @param object
     *            the DTO to insert
     * @throws DataAccessException
     *             if an exception of any sort occurs during the repo operation
     */
    void insert( ObjectType object ) throws DataAccessException;

    /**
     * Updates the corresponding record in the persistent repository with the data from the given
     * DTO.
     * 
     * @param object
     *            the DTO that contains the data to place in the repository
     * @throws DataAccessException
     *             if an exception of any sort occurs during the repo operation
     */
    void update( ObjectType object ) throws DataAccessException;

    /**
     * Returns from the repository the DTO with the given PK combination (the PK can also be a
     * simple PK, in which case only one parameter should be given).
     * 
     * @param pks
     *            the PK of the item to retrieve (can be one parameter for simple PK, or a list of
     *            params for the combined PKs)
     * @return the item with the specified PK, or <code>null</code> if no such item could be found
     * @throws DataAccessException
     *             if an exception of any sort occurs during the repo operation
     */
    ObjectType findByPK( Object... pks ) throws DataAccessException;

    /**
     * Deletes the corresponding record from the persistent repository.
     * 
     * @param pks
     *            the simple or composed PK that identifies the item to remove from the repository
     * @throws DataAccessException
     *             if an exception of any sort occurs during the repo operation
     */
    void deleteByPK( Object... pks ) throws DataAccessException;

}
