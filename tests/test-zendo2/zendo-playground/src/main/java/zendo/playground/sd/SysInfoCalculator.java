package zendo.playground.sd;

import java.lang.management.ManagementFactory;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SysInfoCalculator {

    private static final Log log = LogFactory.getLog(SysInfoCalculator.class);
    private MBeanServerConnection clientConnector = ManagementFactory.getPlatformMBeanServer();
    private int sequenceNumber = 0;
    private final int STOREDVALUES = 3;
    private long loadIndex = -1;
    private MeasuredValues<Double> systemLoadContainer = new MeasuredValues<Double>("systemLoad");
    private MeasuredValues<Integer> numThreadsContainer = new MeasuredValues<Integer>("numThreads");
    private MeasuredValues<Long> usedSwapSpaceSizeContainer = new MeasuredValues<Long>("freeSwapSpaceSize");
    private MeasuredValues<Long> usedheapMemoryContainer = new MeasuredValues<Long>("heapMemoryUsed");
    private MeasuredValues<Double> cpuUsageContainer = new MeasuredValues<Double>("cpuUsage");
    // Levelborders
    private double numThreadsPercentAlarm = 95;
    private double numThreadsPercentWarning = 90;
    private double usedSwapSpaceSizePercentAlarm = 85;
    private double usedSwapSpaceSizePercentWarning = 70;
    private double usedHeapSizePercentAlarm = 90;
    private double usedHeapSizePercentWarning = 80;
    private double cpuUsagePercentAlarm = 90;
    private double cpuUsagePercentWarning = 80;
    private double systemLoadWarning = 1.0;
    private double systemLoadAlarm = 1.5;
    private AlarmLevelType currentAlarmLevel = AlarmLevelType.UNKNOWN;
    private long levelLastUpdated = System.currentTimeMillis();
    private long repeatInterval = 1000 * 60 * 2;
    private long levelTimeout = repeatInterval * 3;

    public AlarmLevelType getAlarmLevel() {
        // level timed out?
        if (System.currentTimeMillis() > levelLastUpdated + levelTimeout) {
            currentAlarmLevel = AlarmLevelType.NORMAL;
            levelLastUpdated = System.currentTimeMillis();
        }
        return currentAlarmLevel;
    }

    /**
     * Periodically execution of the load checks
     */
    public void checkerThread() {

        long index = -1;
        long dummy = 0;
        int numChecks = 0;

        dummy = checkSwapSpace();
        if (dummy != -1) {
            index += dummy;
            numChecks++;
        }
        dummy = checkHeapMemory();
        if (dummy != -1) {
            index += dummy;
            numChecks++;
        }
        dummy = checkThreadPool();
        if (dummy != -1) {
            index += dummy;
            numChecks++;
        }
        dummy = checkSystemLoad();
        if (dummy != -1) {
            index += dummy;
            numChecks++;
        }
        dummy = checkCpuUsagePercentage();
        if (dummy != -1) {
            index += dummy;
            numChecks++;
        }

        // average index of the checks
        if (numChecks > 0) {
            index = (index / numChecks);
        }
        // if level is on ALARM increase the index as marker
        this.loadIndex = (currentAlarmLevel == AlarmLevelType.ALARM) ? index + 1000 : index;

        if (log.isDebugEnabled()) {
            log.debug(MessageFormat.format("Current level : {0}, Loadindex : {1}", currentAlarmLevel.name(), loadIndex));
        }
    }

    /**
     * Check of the availability of swap space. Verifying against warning and alarm border
     * (percentage use of swap space)
     * 
     * @return the load index
     */
    public long checkSwapSpace() {

        long index = -1;
        try {
            long freeSwapSpaceSize = (Long) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "FreeSwapSpaceSize");
            long totalSwapSpaceSize = (Long) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "TotalSwapSpaceSize");

            if (freeSwapSpaceSize > 0 || totalSwapSpaceSize > 0) {
                log.debug(MessageFormat.format("total swap space : {0}; free swap space : {1}", totalSwapSpaceSize, freeSwapSpaceSize));
            } else if (log.isDebugEnabled()) {
                log.warn(MessageFormat.format("Could not get all necessary attributes : total swap space : {0}; free swap space : {1}", totalSwapSpaceSize, freeSwapSpaceSize));
            }

            double avg = usedSwapSpaceSizeContainer.getAverage(totalSwapSpaceSize - freeSwapSpaceSize);
            index = setLevel("usedSwapSpaceSize", avg, totalSwapSpaceSize * usedSwapSpaceSizePercentWarning / 100.0, totalSwapSpaceSize
                    * usedSwapSpaceSizePercentAlarm / 100.0);

            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("avg swap space (percentage): {0}", avg * 100));
            }

        } catch (Exception e) {
            log.info("Error checking the swap space on tomcat side!", e);
        }
        return index;
    }

    /**
     * @return
     */
    public long checkHeapMemory() {

        long index = -1;
        try {
            CompositeData heap = (CompositeData) clientConnector.getAttribute(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
            Long maxHeapSize = (Long) heap.get("max");
            Long usedHeapSize = (Long) heap.get("used");
            if (maxHeapSize > 0 && usedHeapSize > 0) {
                Double avg = usedheapMemoryContainer.getAverage(usedHeapSize);
                index = setLevel("usedHeapSize", avg, maxHeapSize * usedHeapSizePercentWarning / 100.0, maxHeapSize
                        * usedHeapSizePercentAlarm / 100.0);
                if (log.isDebugEnabled()) {
                    log.debug(MessageFormat.format("avg used heap (percentage): {}", avg * 100));
                }
            }

        } catch (Exception e) {
            log.info("Error checking the heap usage!", e);
        }
        return index;
    }

    /**
     * Check of the usage of the thread pool. Verifying against warning and alarm border
     * (percentage use of available threads)
     * 
     * @return the calculated load index where lower is better
     */
    public long checkThreadPool() {

        long index = -1;
        try {
            Integer maxThreads = (Integer) clientConnector.getAttribute(new ObjectName("Catalina:type=Executor,name=tomcatThreadPool"), "maxThreads");
            Integer numThreads = (Integer) clientConnector.getAttribute(new ObjectName("java.lang:type=Threading"), "ThreadCount");

            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("max threads : {}", maxThreads));
            }

            Double avg = numThreadsContainer.getAverage(numThreads);
            index = setLevel("numThreads", avg, maxThreads * numThreadsPercentWarning / 100.0, maxThreads
                    * numThreadsPercentAlarm / 100.0);

            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("num threads : {}; average : {}", numThreads, avg));
            }
        } catch (Exception e) {
            log.info("Error checking the tread pool on tomcat side!", e);
        }
        return index;
    }

    /**
     * Check of the system load depending on number of processors. Verfifying against warning and
     * alarm border. REMEMBER : this value is not provided on every operating system
     * 
     * @return load index where lower is better
     */
    public long checkSystemLoad() {

        long index = -1;
        try {
            Double systemLoadAverage = (Double) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "SystemLoadAverage");
            int availableProcessors = (Integer) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "AvailableProcessors");
            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("System load average : {}; available Processors : {}", systemLoadAverage, availableProcessors));
            }
            if (systemLoadAverage != null && systemLoadAverage >= 0) {
                double avg = systemLoadContainer.getAverage(systemLoadAverage);
                index = setLevel("systemLoad", avg, availableProcessors * systemLoadWarning, availableProcessors
                        * systemLoadAlarm);
            }
        } catch (Exception e) {
            log.info("Error checking the system load!", e);
        }
        return index;
    }

    /**
     * Check of the cpu usage. Verfifying against warning and alarm border.
     * 
     * @return a load index where lower is better
     */
    public long checkCpuUsagePercentage() {

        long index = -1;

        long timestampBefore = 0;
        long timestampAfter = 0;
        double sumPercentage = 0.0;

        try {
            int availableProcessors = (Integer) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "AvailableProcessors");

            for (int i = 0; i < STOREDVALUES; i++) {
                timestampBefore = System.nanoTime();
                long processCpuTimeBefore = (Long) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "ProcessCpuTime");
                if (log.isDebugEnabled()) {
                    log.debug(MessageFormat.format("----> timestamp before : {}; process cpu time before : {}", timestampBefore, processCpuTimeBefore));
                }
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                long processCpuTimeAfter = (Long) clientConnector.getAttribute(new ObjectName("java.lang:type=OperatingSystem"), "ProcessCpuTime");
                timestampAfter = System.nanoTime();
                if (log.isDebugEnabled()) {
                    log.debug(MessageFormat.format("----> timestamp after : {}; process cpu time after : {}", timestampAfter, processCpuTimeAfter));
                }
                sumPercentage += ((processCpuTimeAfter - processCpuTimeBefore) * 100.0) / (timestampAfter - timestampBefore);
                if (log.isDebugEnabled()) {
                    log.debug(MessageFormat.format("====> sum percentage : {}", sumPercentage));
                }
            }
            if (log.isDebugEnabled()) {
                log.debug(MessageFormat.format("CPU usage percentage : {}", sumPercentage / STOREDVALUES / availableProcessors));
            }
            double avg = cpuUsageContainer.getAverage(sumPercentage / STOREDVALUES / availableProcessors);
            index = setLevel("cpuUsagePercentage", avg, cpuUsagePercentWarning, cpuUsagePercentAlarm);
        } catch (Exception e) {
            log.info("Error checking the cpu usage!", e);
        }
        return index;
    }

    /**
     * Set warn level. If new current calculated level is less or equal then the valid current
     * level, the current value won't be changed.
     * 
     * @param type type of check
     * @param avg the average of the last STOREDVALUES detected values
     * @param warnLevelBorder the warning border
     * @param alarmLevelBorder the alarm border
     * @return the load level calculated from the given data
     */
    public long setLevel(String type, double avg, double warnLevelBorder, double alarmLevelBorder) {

        AlarmLevelType level = AlarmLevelType.NORMAL;
        // get AlarmLevel
        if (avg >= alarmLevelBorder) {
            level = AlarmLevelType.ALARM;
            log.info("ALARM-" + type);
        } else if (avg >= warnLevelBorder) {
            level = AlarmLevelType.WARNING;
            log.info("WARNING-" + type);
        }
        if (System.currentTimeMillis() <= levelLastUpdated + levelTimeout) {
            if (level.getValue() >= currentAlarmLevel.getValue()) {
                updateAlarmLevel(level);
            }
        } else {
            updateAlarmLevel(level);
        }
        Double index = avg * 100 / warnLevelBorder;

        if (log.isDebugEnabled()) {
            log.debug(MessageFormat.format("Calculated level : {}, simple index : {}", level.name(), index));
        }
        return level.getValue() * index.longValue();
    }

    private void updateAlarmLevel(AlarmLevelType level) {
        currentAlarmLevel = level;
        levelLastUpdated = System.currentTimeMillis();
    }

    /**
     * container for the last x items of the measured values
     */
    private class MeasuredValues<T extends Number> {

        @SuppressWarnings("unused")
        private String valueName;
        private List<T> values = new ArrayList<T>();
        private int indexOldestValue = 0;

        public MeasuredValues(String valueName) {

            this.setValueName(valueName);
        }

        public double getAverage(T newValue) {

            double result = 0.0D;

            // special case starting phase without
            if (values.size() < STOREDVALUES) {
                values.add(newValue);
            } else {
                values.set(indexOldestValue, newValue);
                indexOldestValue = (indexOldestValue + 1) % STOREDVALUES;
            }
            for (T value : values) {
                result += value.doubleValue();
            }
            return (values.size() > 0) ? result / values.size() : 0.0;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }
    }

    public void setClientConnector(MBeanServerConnection clientConnector) {
        this.clientConnector = clientConnector;
    }

    public void setNumThreadsPercentAlarm(double numThreadsAlarm) {
        this.numThreadsPercentAlarm = numThreadsAlarm;
    }

    public void setNumThreadsPercentWarning(double numThreadsWarning) {
        this.numThreadsPercentWarning = numThreadsWarning;
    }

    public void setUsedSwapSpaceSizePercentAlarm(double usedSwapSpaceSizePercentAlarm) {
        this.usedSwapSpaceSizePercentAlarm = usedSwapSpaceSizePercentAlarm;
    }

    public void setUsedSwapSpaceSizePercentWarning(double usedSwapSpaceSizePercentWarning) {
        this.usedSwapSpaceSizePercentWarning = usedSwapSpaceSizePercentWarning;
    }

    public void setCpuUsagePercentAlarm(double cpuUsageAlarm) {
        this.cpuUsagePercentAlarm = cpuUsageAlarm;
    }

    public void setCpuUsagePercentWarning(double cpuUsageWarning) {
        this.cpuUsagePercentWarning = cpuUsageWarning;
    }

    public void setSystemLoadWarning(double systemLoadWarning) {
        this.systemLoadWarning = systemLoadWarning;
    }

    public void setSystemLoadAlarm(double systemLoadAlarm) {
        this.systemLoadAlarm = systemLoadAlarm;
    }

    public void setUsedHeapSizePercentAlarm(double usedHeapSizePercentAlarm) {
        this.usedHeapSizePercentAlarm = usedHeapSizePercentAlarm;
    }

    public void setUsedHeapSizePercentWarning(double usedHeapSizePercentWarning) {
        this.usedHeapSizePercentWarning = usedHeapSizePercentWarning;
    }

    /*
     * (non-Javadoc)
     * @see com.netviewer.nvserver.presence.business.LoadIndicator#getLoadIndex()
     */
    public long getLoadIndex() {
        return this.loadIndex;
    }

    /*
     * (non-Javadoc)
     * @see com.netviewer.nvserver.presence.business.load.LoadIndicator#getAlarmLevelName()
     */
    public String getAlarmLevelName() {
        return this.currentAlarmLevel.name();
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
        this.levelTimeout = 3 * repeatInterval;
    }

    public List<Double> getSystemLoadValues() {
        return systemLoadContainer.values;
    }

    public List<Integer> getNumThreadsValues() {
        return numThreadsContainer.values;
    }

    public List<Long> getUsedSwapSpaceSizeValues() {
        return usedSwapSpaceSizeContainer.values;
    }

    public List<Long> getUsedheapMemoryValues() {
        return usedheapMemoryContainer.values;
    }

    public List<Double> getCpuUsageValues() {
        return cpuUsageContainer.values;
    }

    public double getNumThreadsPercentAlarm() {
        return numThreadsPercentAlarm;
    }

    public double getNumThreadsPercentWarning() {
        return numThreadsPercentWarning;
    }

    public double getUsedSwapSpaceSizePercentAlarm() {
        return usedSwapSpaceSizePercentAlarm;
    }

    public double getUsedSwapSpaceSizePercentWarning() {
        return usedSwapSpaceSizePercentWarning;
    }

    public double getUsedHeapSizePercentAlarm() {
        return usedHeapSizePercentAlarm;
    }

    public double getUsedHeapSizePercentWarning() {
        return usedHeapSizePercentWarning;
    }

    public double getCpuUsagePercentAlarm() {
        return cpuUsagePercentAlarm;
    }

    public double getCpuUsagePercentWarning() {
        return cpuUsagePercentWarning;
    }

    public double getSystemLoadWarning() {
        return systemLoadWarning;
    }

    public double getSystemLoadAlarm() {
        return systemLoadAlarm;
    }

    public static void main(String[] args) {
        SysInfoCalculator calc = new SysInfoCalculator();
        System.out.println( calc.checkCpuUsagePercentage() );
    }
}
