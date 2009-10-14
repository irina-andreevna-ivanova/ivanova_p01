package zendo.playground.dpatterns.interpreter;

/**
 * @author bogdan.mocanu
 */
public class AlternationExpression extends RegularExpression {

    private RegularExpression expression1;

    private RegularExpression expression2;

    // ------------------------------------------------------------------------------------------------------

    public void interpret() {
                
    }

    // ------------------------------------------------------------------------------------------------------

    public RegularExpression getExpression1() {
        return expression1;
    }

    public void setExpression1( RegularExpression expression1 ) {
        this.expression1 = expression1;
    }

    public RegularExpression getExpression2() {
        return expression2;
    }

    public void setExpression2( RegularExpression expression2 ) {
        this.expression2 = expression2;
    }
}
