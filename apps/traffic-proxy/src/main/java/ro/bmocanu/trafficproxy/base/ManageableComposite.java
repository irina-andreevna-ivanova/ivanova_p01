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

package ro.bmocanu.trafficproxy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link Manageable} entity that also provides composite features to clients. Multiple
 * <code>Manageable</code> entities can be mapped as workers for a main entity.
 * 
 * @author mocanu
 */
public class ManageableComposite implements Manageable {

    /**
     * The name of this manageable entity.
     */
    protected String name;

    /**
     * The list of workers spawned by this entity.
     */
    protected List<Manageable> workers;

    /**
     * The lock that shields the worker list during the time of worker insertion/removal.
     */
    private ReentrantLock workersLock;

    // -------------------------------------------------------------------------------------------------

    /**
     * 
     */
    public ManageableComposite() {
        workers = new ArrayList<Manageable>();
        workersLock = new ReentrantLock();
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStart() {
        // no code here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStop() {
        // shut down all the workers
        workersLock.lock();
        try {
            for ( Manageable worker : workers ) {
                worker.managedStop();
            }
        } finally {
            workersLock.unlock();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntityName( String name ) {
        this.name = name;
    }

    public void addManageable( Manageable worker ) {
        workersLock.lock();
        try {
            workers.add( worker );
        } finally {
            workersLock.unlock();
        }
    }

}
