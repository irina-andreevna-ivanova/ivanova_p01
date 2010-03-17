/**
 * 
 */
package ro.bmocanu.trafficproxy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Constants;

/**
 * 
 * 
 * @author mocanu
 */
public abstract class ManageableThread implements Initializable, Disposable, Manageable, Runnable {
    private static final Logger LOG = Logger.getLogger( ManageableThread.class );

    private final Thread internalThread;
    private boolean threadShouldStop = false;
    private String name;
    
    private ReentrantLock workersLock;
    private List<Manageable> workers;
    
    // ------------------------------------------------------------------------------------------------------

    /**
     * 
     */
    public ManageableThread() {
        internalThread = new Thread( this );
        workers = new ArrayList<Manageable>();
        workersLock = new ReentrantLock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        // no code here; override this to provide your own implementation
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() throws Exception {
        // no code here; override this to provide your own implementation
    }

    /**
     * 
     */
    protected abstract void internalRun();
    
    // ------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        LOG.info( name + " is starting up" );
        threadShouldStop = false;

        try {
            LOG.info( name + " is initializing" );
            init();
        } catch ( Exception exception ) {
            LOG.error( name + " failed to initialize", exception );
            threadShouldStop = true;
        }
        
        while ( !threadShouldStop ) {
            internalRun();
            try {
                TimeUnit.MILLISECONDS.sleep( Constants.CORE_THREAD_IDLE_TIMEOUT_MLS );
            } catch ( InterruptedException exception ) {
                LOG.error( name + " failed during execution", exception );
                threadShouldStop = true;
            }
        }
        
        try {
            LOG.info( name + " is shutting down" );
            dispose();
        } catch ( Exception exception ) {
            LOG.error( name + " failed to shut down", exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStart() {
        LOG.debug( name + " is assisted in starting up" );
        internalThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStop() {
        LOG.debug( name + " is assisted in shutting down" );
        
        // 1. shut down all the workers
        workersLock.lock();
        try {
            for( Manageable worker : workers ) {
                worker.managedStop();
            }
        } finally {
            workersLock.unlock();
        }

        // 2. now shut down the parent too
        threadShouldStop = true;
        try {
            internalThread.join();
        } catch ( InterruptedException exception ) {
            LOG.error( name + " failed to shut down", exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntityName( String name ) {
        this.name = name;
    }
    
    public void startWorker( Manageable worker ) {
        if ( threadShouldStop ) {
            // thread is closing down, so cancel this
            return;
        }
        
        workersLock.lock();
        try {
            workers.add( worker );
        } finally {
            workersLock.unlock();
        }
        
        worker.managedStart();
    }
    
}
