/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * IDataPersistable
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 30.09.2006
 *
 * Version : 1.0.0
 *
 * Copyright (C) Bogdan Mocanu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
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
package ro.bmocanu.mobile.apps.metris;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This interface defines the general contract for objects which need persistence.
 */
public interface IDataPersistable {

    void loadData( DataInputStream stream ) throws IOException;

    void saveData( DataOutputStream stream ) throws IOException;

}
