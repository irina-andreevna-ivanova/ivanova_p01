package zendo.playground.sd;

//@Aspect
//@ManagedResource( description = "PRS monitoring jms consumer", objectName = "nvpresence:name=monitor,type=jmsConsumer" )
public class MBeanExport {
/*
    private static final Log log = LogFactory.getLog( JmsConsumerMonitoringAspect.class );

    private long sumTime = 0;
    private long numRequests = 0;
    private long startTime = System.currentTimeMillis();

    @Around( "execution(public * com.netviewer.nvserver.presence.business.PresenceRequestHandler.handle*(..))" )
    public Object addMonitorConsumer( ProceedingJoinPoint joinPoint ) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result=joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        if ( log.isDebugEnabled() )
            log.debug( "[" + joinPoint.getSignature().getName() + "][" + startTime + "][" + endTime + "]["
                    + (endTime - startTime) + "]" );

        sumTime += (endTime - startTime);
        numRequests++;
        return result;
    }

    @ManagedAttribute( description = "average time the consumers needs to do their work (cut after 2 digits after decimal point)" )
    public double getAverageTime() {
        return (numRequests > 0) ? 100 * sumTime / numRequests / 100.0D : -1.0D;
    }

    @ManagedAttribute( description = "the sum of time the consumers needs to do their work" )
    public long getSumTime() {
        return sumTime;
    }

    @ManagedAttribute( description = "number of requests the consumers had to handle" )
    public long getNumRequests() {
        return numRequests;
    }

    @ManagedOperation( description="reset the collected values" )
    public void reset() {
        sumTime = 0;
        numRequests = 0;
        startTime = System.currentTimeMillis();
    }

    @ManagedOperation( description="number of requests per minute" )
    public double getNumRequestsPerMinute() {
        return 60000 * numRequests / (System.currentTimeMillis() - startTime) ;
    }

 */
}
