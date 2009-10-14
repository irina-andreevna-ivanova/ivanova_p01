/* Copyright Bogdan Mocanu, 2008
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

package ro.bmocanu.zendo.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PackageHierarchyPackageLoader implements PropertiesLoader {
    private static Log log = LogFactory.getLog( PackageHierarchyPackageLoader.class );

    public void loadProperties( Properties properties, TestDescriptor descriptor ) {
        log.debug( "Loading properties for test " + descriptor.getClassName() );
        loadPropertiesFromDirectNameFile( properties, descriptor );
        loadPropertiesFromPackageHierarchy( properties, descriptor );
    }

    private void loadPropertiesFromDirectNameFile( Properties properties, TestDescriptor descriptor ) {
        String directPropFileName = descriptor.getClassName().replace( '.', '/' ) + ".properties";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream( directPropFileName );
        if ( inputStream != null ) {
            log.info( "Loading properties from " + directPropFileName );
            try {
                properties.load( inputStream );

            } catch ( FileNotFoundException e ) {
                log.error( "Cannot find properties file " + directPropFileName, e );
            } catch ( IOException e ) {
                log.error( "Cannot load properties file " + directPropFileName, e );
            }
        }
    }

    private void loadPropertiesFromPackageHierarchy( Properties properties, TestDescriptor descriptor ) {
        String packageName = descriptor.getPackageName();
        StringTokenizer tokenizer = new StringTokenizer( packageName, "." );
        String currentDirectory = "";

        while ( true ) {
            String fileName = currentDirectory + "package.properties";
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream( fileName );
            if ( inputStream != null ) {
                log.info( "Loading properties from " + fileName );
                try {
                    properties.load( inputStream );
                } catch ( IOException e ) {
                    log.error( "Cannot load properties file " + fileName, e );
                }
            }

            if ( tokenizer.hasMoreTokens() ) {
                currentDirectory += tokenizer.nextToken() + "/";
            } else {
                break;
            }
        }

    }

}
