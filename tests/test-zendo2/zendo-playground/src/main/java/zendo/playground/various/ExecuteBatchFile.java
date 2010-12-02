package zendo.playground.various;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 11/30/10
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExecuteBatchFile {

    // [10:14:19 AM] Alexandru Ardelean: C:\G4-Standard Server\PhP;C:\G4-Standard Server\PhP;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem;%JAVA_HOME%\bin;%M2_HOME%\bin;c:\Program Files\TortoiseSVN\bin;c:\G4-Standard Server\MySQL\bin;

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "G:\\test22\\test23\\script.bat");
        pb.directory( new File( "G:\\test22\\test23" ) );
        Process p = pb.start();
        InputStream is = p.getInputStream();
        IOUtils.copy(is, System.out);
        IOUtils.closeQuietly(is);
    }

    public static void main3(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("cmd.exe /C G:/script.bat", null);
        InputStream is = p.getInputStream();
        IOUtils.copy(is, System.out);
        IOUtils.closeQuietly(is);
    }

    public static void main4(String[] args) throws IOException, InterruptedException {
        String path = "\"G:\\test22\\test23\\script.bat\"";
        Process process = Runtime.getRuntime().exec("cmd.exe /C " + path);
        InputStream stdin = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdin, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        int exitVal = process.waitFor();
        System.out.println("batch file executed successfully, with exitCode: " + exitVal);
        IOUtils.closeQuietly(br);
    }

}
