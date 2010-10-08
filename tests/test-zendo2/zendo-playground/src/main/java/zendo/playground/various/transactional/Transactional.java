package zendo.playground.various.transactional;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

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
    public <T> T get( String name ) {
        return (T) mappedObjects.get( name );
    }

    public void rollback() {
        rollback = true;
    }

    // ------------------------------------------------------------------------------------------------------

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
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( object );
            oos.close();
            return baos.toByteArray();
        } catch ( Exception exception ) {
            exception.printStackTrace();
            return null;
        }
    }

    private Object unserializeObject( byte[] content ) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream( content );
            ObjectInputStream ois = new ObjectInputStream( bais );
            Object result = ois.readObject();
            ois.close();
            return result;
        } catch ( Exception exception ) {
            exception.printStackTrace();
            return null;
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

    // ------------------------------------------------------------------------------------------------------

    protected static abstract class Execution {

        private Transactional transactionalParent;

        protected abstract void execute() throws Exception;

        protected <T> T get( String name ) {
            return transactionalParent.get( name );
        }

        protected void rollback() {
            transactionalParent.rollback();
        }
    }

}
