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

package ro.bmocanu.tests.exchand.utils;

import java.sql.SQLException;

import ro.bmocanu.tests.exchand.exceptions.DataAccessException;

/**
 * Dummy class, to simulate
 * 
 * @author mocanu
 */
public class JdbcUtils {

    public static void executeStatementWithExceptionConversion( String stat ) throws DataAccessException {
        try {
            throw new SQLException( "Some XFiles reason" );
        } catch ( SQLException exception ) {
            throw new DataAccessException( exception );
        }
    }

    public static <Type> Type executeQueryWithExceptionConversion( String stat ) throws DataAccessException {
        try {
            throw new SQLException( "Some XFiles reason" );
        } catch ( SQLException exception ) {
            throw new DataAccessException( exception );
        }
    }

}
