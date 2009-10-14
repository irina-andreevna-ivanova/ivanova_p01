/**
 * 
 */
package zendo.playground.quizzes;

/**
 * 
 *
 * @author mocanu
 */
public class TestQ14_2 {
    
    public static void main( String[] args ) {
        GoldDigger digger = new GoldDigger();
        //System.out.println( digger.getGoldRetriever().getTheGold() );
    }

}

class GoldDigger {
    
    private interface GoldRetriever {
        public String getTheGold();
    }
    
    private class GoldRetrieverImpl implements GoldRetriever {
        public String getTheGold() {
            return "*GOLD*";
        }
    }
    
    public final GoldRetriever getGoldRetriever() {
        GoldRetriever gr = new GoldRetrieverImpl();
        return gr;
    }
    
}
