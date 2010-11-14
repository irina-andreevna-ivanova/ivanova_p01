package zendo.playground.various.transactional;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

/**
 * Provides transactional support for code blocks.
 *
 * @author bogdan.mocanu
 */
public class Transactional {

    private Map<String, Object> mappedObjects = new HashMap<String, Object>();
    private Map<String, byte[]> serializedObjects = new HashMap<String, byte[]>();
    private boolean rollback = false;

    public void put( String name, Object object ) {
        mappedObjects.put( name, object );
        serializedObjects.put( name, serializeObject( object ) );
    }

    @SuppressWarnings( "unchecked" )
    public <T extends Object> T get( String name ) {
        return (T) mappedObjects.get( name );
    }

    public void rollback() {
        rollback = true;
    }

    // --------------------------------------------------------------------------

    public void execute( Execution exec ) {
        try {
            exec.transactionalParent = this;
            exec.execute();
        } catch ( Exception exception ) {
            exception.printStackTrace();
            rollback = true;
        }
        if ( rollback ) {
            rollbackData();
        }
    }

    private byte[] serializeObject( Object object ) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream( baos );
            oos.writeObject( object );
            return baos.toByteArray();
        } catch ( Exception exception ) {
            exception.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly( oos );
        }
    }

    private Object unserializeObject( byte[] content ) {
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream( content );
            ois = new ObjectInputStream( bais );
            Object result = ois.readObject();
            return result;
        } catch ( Exception exception ) {
            exception.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly( ois );
        }
    }

    private void rollbackData() {
        Map<String, Object> originalMappedObjects = new HashMap<String, Object>();
        for ( Entry<String, Object> mappedEntry : mappedObjects.entrySet() ) {
            String key = mappedEntry.getKey();
            Object originalObject = unserializeObject( serializedObjects.get( key ) );
            originalMappedObjects.put( key, originalObject );
        }
        mappedObjects = originalMappedObjects;
    }

    // --------------------------------------------------------------------------

    protected static abstract class Execution {
        private Transactional transactionalParent;

        protected abstract void execute() throws Exception;

        protected <T> T get( String name ) {
            return (T)transactionalParent.get( name );
        }

        protected void rollback() {
            transactionalParent.rollback();
        }
    }
}
