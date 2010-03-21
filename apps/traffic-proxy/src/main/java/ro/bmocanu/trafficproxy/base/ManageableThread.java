/**
 * 
 */
package ro.bmocanu.trafficproxy.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Constants;

/**
 * @author mocanu
 */
public abstract class ManageableThread extends ManageableComposite implements Initializable, Disposable, Manageable,
        Runnable {
    private static final Logger LOG = Logger.getLogger( ManageableThread.class );

    private final Thread internalThread;
    private boolean threadShouldStop = false;

    // ------------------------------------------------------------------------------------------------------

    /**
     * 
     */
    public ManageableThread() {
        internalThread = new Thread( this );
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
    protected abstract void internalRun() throws Exception;

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
            try {
                internalRun();
                TimeUnit.MILLISECONDS.sleep( Constants.CORE_THREAD_IDLE_TIMEOUT_MLS );
            } catch ( InterruptedException exception ) {
                LOG.error( name + " failed during execution", exception );
                threadShouldStop = true;
            } catch ( Exception exception ) {
                LOG.error( name + " failed. Execution will continue", exception );
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

        // allow the workers to stop
        super.managedStop();

        // now shut down the parent
        threadShouldStop = true;
        try {
            internalThread.join();
        } catch ( InterruptedException exception ) {
            LOG.error( name + " failed to shut down", exception );
        }
    }

    public void startWorker( Manageable worker ) {
        if ( threadShouldStop ) {
            // thread is closing down, so cancel this
            return;
        }

        addManageable( worker );
        worker.managedStart();
    }

}
