package zendo.playground.mbeans;
public interface ServerConfigurationMBean {
    public int getCacheSize();
    public void setCacheSize( int cacheSize );

    public String getDatabaseConnectionURL();
    public void setDatabaseConnectionURL( String url ); 

    public void startRegistrar();
    public void stopRegistrar();
}
