package ro.bmocanu.tests.spring3desktop;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 26.08.2010
 * Time: 14:14:01
 * To change this template use File | Settings | File Templates.
 */
public class BeanNr1 {

    @Autowired
    private BeanNr2 nr2;

    public BeanNr2 getNr2() {
        return nr2;
    }
    
}
