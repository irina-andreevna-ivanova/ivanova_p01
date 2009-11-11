/*- 
 * Copyright Bogdan Mocanu, 2009
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

package ro.bmocanu.tests.ws.model;

import java.util.Date;

/**
 * Sample model object for testing the web services.
 * 
 * @author mocanu
 */
public class Product {

    private long id;

    private String name;

    private String description;

    private Date received;

    /**
     * 
     */
    public Product() {
    }

    /**
     * @param id
     * @param name
     * @param description
     * @param received
     */
    public Product(long id, String name, String description, Date received) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.received = received;
    }

    /**
     * Returns the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the given value.
     * 
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Returns the description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description to the given value.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * Returns the received
     * 
     * @return the received
     */
    public Date getReceived() {
        return received;
    }

    /**
     * Sets the received to the given value.
     * 
     * @param received
     *            the received to set
     */
    public void setReceived( Date received ) {
        this.received = received;
    }

    /**
     * Returns the id
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id to the given value.
     * 
     * @param id
     *            the id to set
     */
    public void setId( long id ) {
        this.id = id;
    }

}
