package zendo.playground.dpatterns.interpreter;


/**
 * @author bogdan.mocanu
 */
public class RepetitionExpression extends RegularExpression {

    private RegularExpression expression;

    // ------------------------------------------------------------------------------------------------------

    public void interpret() {
    }

    // ------------------------------------------------------------------------------------------------------

    public RegularExpression getExpression() {
        return expression;
    }

    public void setExpression( RegularExpression expression ) {
        this.expression = expression;
    }

}
