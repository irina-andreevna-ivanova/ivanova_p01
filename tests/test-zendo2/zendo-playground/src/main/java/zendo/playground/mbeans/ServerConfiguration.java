package zendo.playground.mbeans;

public class ServerConfiguration implements ServerConfigurationMBean {
    private int cacheSize = 0;
    private String databaseConnectionURL = "jdbc:microsoft:sqlserver://localhost:1433;databaseName=TestDB;SelectMethod=CURSOR";

    public int getCacheSize() {
        return cacheSize;
    }

    public String getDatabaseConnectionURL() {
        return databaseConnectionURL;
    }

    public void setCacheSize( int cacheSize ) {
        this.cacheSize = cacheSize;
    }

    public void setDatabaseConnectionURL( String url ) {
        this.databaseConnectionURL = url;
    }

    public void startRegistrar() {
        // start the registrar
    }

    public void stopRegistrar() {
        // stop the registrar
    }
}
