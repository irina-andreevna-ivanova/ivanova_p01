package zendo.playground.dpatterns.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bogdan.mocanu
 */
public class SequenceExpression extends RegularExpression {

    private List<RegularExpression> expressions = new ArrayList<RegularExpression>();

    // ------------------------------------------------------------------------------------------------------

    public void interpret() {
    }

    // ------------------------------------------------------------------------------------------------------

    public List<RegularExpression> getExpressions() {
        return expressions;
    }
}
