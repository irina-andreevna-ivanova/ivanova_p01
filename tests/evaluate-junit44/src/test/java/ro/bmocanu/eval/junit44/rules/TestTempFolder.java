package ro.bmocanu.eval.junit44.rules;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestTempFolder {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    @SuppressWarnings( "unused" )
    public void testUsingTempFolder() throws IOException {
        File createdFile = folder.newFile( "myfile.txt" );
        File createdFolder = folder.newFolder( "subfolder" );
        // ...
    }

}
