/* Java2ME Mobile Tetris
 * 
 * AppDataPersister
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 29.09.2006
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

/**
 * This class is responsible for loading and saving the game data. The class maintains a
 * list of objects implementing the {@link IDataPersistable} interface. When the
 * <code>loadData</code> or the <code>saveData()</code> method is invoked, the class
 * calls, for each of the registered objects the <code>loadData()</code> or
 * <code>saveData()</code> respectively.
 */
public class AppDataPersister {

    /**
     * The single instance of this singleton class.
     */
    private static AppDataPersister singleInstance;

    /**
     * The list of objects that should be persisted.
     */
    private Vector persistentObjects;

    /**
     * The ID of the single record used for storing data.
     */
    private int dataRecordId;

    /**
     * This flag shows whether data was found when the app started.
     */
    private boolean dataRecordFound;

    /**
     * Creates a new instance of <code>AppDataPersister</code>. Since this constructor
     * is private, the only way a client can get an instance of this class is through the
     * use of the <code>getInstance()</code> static method.
     */
    private AppDataPersister() {
        super();
        dataRecordId = 0;
        dataRecordFound = false;
        persistentObjects = new Vector();
    }

    /**
     * Returns the single instance of this class. If this is the first time when this
     * method is called, it creates a new instance of this class, and then it returns it
     * to the caller.
     * 
     * @return the single instance of this class.
     */
    public static AppDataPersister getInstance() {
        if ( null == singleInstance ) {
            singleInstance = new AppDataPersister();
            singleInstance.init();
        }

        return singleInstance;
    }

    /**
     * Adds a new object to the internal list of objects which need to be persisted.
     * 
     * @param object
     *            The new object to add to the list.
     */
    public void addPersistentObject( IDataPersistable object ) {
        persistentObjects.addElement( object );
    }

    /**
     * Initializes the persister. It first checks for RecordStore existence, and if it
     * cannot find one, it creates it. Then it searches for the first Record. If none is
     * found, a dummy one is created. <code>dataRecordFound</code> is true if and only
     * if the RecordStore is found and at least one record is true.
     */
    public void init() {
        dataRecordFound = false;

        try {
            RecordStore rStore = RecordStore.openRecordStore( MetrisConstants.GAME_APP_RECORDSTORE_NAME, true );
            if ( rStore.getNumRecords() > 0 ) {
                RecordEnumeration rEnumeration = rStore.enumerateRecords( null, null, false );
                if ( rEnumeration.hasNextElement() ) {
                    dataRecordId = rEnumeration.nextRecordId();
                    dataRecordFound = true;
                } else {
                    // it is unlikely we will get here, but handle it anyway
                    dataRecordFound = false;
                }
            }

            if ( !dataRecordFound ) {
                // then we must add a new record, with some dummy content
                byte[] dummyRecord = { 1 };
                dataRecordId = rStore.addRecord( dummyRecord, 0, dummyRecord.length );
            }

            rStore.closeRecordStore();
        } catch ( Exception exception ) {
        }
    }

    /**
     * Calls each persistent object to load its data from the RecordStore.
     */
    public void loadData() {
        try {
            RecordStore rStore = RecordStore.openRecordStore( MetrisConstants.GAME_APP_RECORDSTORE_NAME, false );
            byte[] dataRecord = rStore.getRecord( dataRecordId );

            DataInputStream diStream = new DataInputStream( new ByteArrayInputStream( dataRecord ) );

            for ( int index = 0; index < persistentObjects.size(); index++ ) {
                ( (IDataPersistable) persistentObjects.elementAt( index ) ).loadData( diStream );
                if ( diStream.available() <= 0 ) {
                    break;
                }
            }

            rStore.closeRecordStore();
        } catch ( Exception e ) {
        }
    }

    /**
     * Calls each object to save its data in the record store.
     */
    public void saveData() {
        try {
            RecordStore rStore = RecordStore.openRecordStore( MetrisConstants.GAME_APP_RECORDSTORE_NAME, false );

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream doStream = new DataOutputStream( baos );

            for ( int index = 0; index < persistentObjects.size(); index++ ) {
                ( (IDataPersistable) persistentObjects.elementAt( index ) ).saveData( doStream );
            }

            byte[] newRecordData = baos.toByteArray();

            rStore.setRecord( dataRecordId, newRecordData, 0, newRecordData.length );
            rStore.closeRecordStore();

            doStream.close();
        } catch ( Exception exception ) {
        }
    }

    /**
     * Gets the value of the dataRecordFound attribute.
     * 
     * @return the dataRecordFound
     */
    public boolean isDataRecordFound() {
        return dataRecordFound;
    }

    /**
     * Gets the value of the dataRecordId attribute.
     * 
     * @return the dataRecordId
     */
    public int getDataRecordId() {
        return dataRecordId;
    }
}
