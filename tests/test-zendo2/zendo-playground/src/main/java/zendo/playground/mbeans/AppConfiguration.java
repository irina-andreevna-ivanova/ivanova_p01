package zendo.playground.mbeans;

public class AppConfiguration implements AppConfigurationMBean {

    private int cacheSize;

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize( int newValue ) {
        cacheSize = newValue;
    }

    public void doSomething() {
        System.out.println( "Doing something really important!" );
    }

}
