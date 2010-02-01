
/**
 * Class responsible with managing the player's response, and sending it to the game engine.
 * 
 * @author mocanu
 */
public class ResponseManager {
    
    private static final String RESPONSE_FORMAT = "%.2f %.2f %.2f %.2f %.6f\n";
    
    public RealPoint bumperAccelerations[] = { new RealPoint(), new RealPoint() };
    
    public double sledDirectionDelta = Const.SLED_TURN_LIMIT;
    
    public double lastSledDirectionDelta = 0;
    
    public void sendResponse() {
        lastSledDirectionDelta = sledDirectionDelta;
        
        Object responseArgs[] = new Object[] { bumperAccelerations[ 0 ].x, bumperAccelerations[ 0 ].y, bumperAccelerations[ 1 ].x, bumperAccelerations[ 1 ].y, sledDirectionDelta };
        System.out.printf( RESPONSE_FORMAT, responseArgs );
    }

}
