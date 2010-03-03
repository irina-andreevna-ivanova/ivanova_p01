/**
 * 
 */
package ro.bmocanu.tests.junit48;

import org.junit.categories.CategorizedTest;
import org.junit.categories.CategoryBase;


/**
 * 
 *
 * @author mocanu
 */
public class TestSomething implements CategorizedTest {

    @Override
    public CategoryBase getCategory() {
        return MainCategories.database.mysql;
    }

}
