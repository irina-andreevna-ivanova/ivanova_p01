/**
 * 
 */
package ro.bmocanu.tests.junit48;

import org.junit.categories.CategoryBase;


/**
 * 
 *
 * @author mocanu
 */
public class MainCategories extends CategoryBase {
    
    public static final MainCategories_Database database = new MainCategories_Database();
    public static final MainCategories_Remote remote = new MainCategories_Remote();
    
    public static class MainCategories_Database extends CategoryBase {
        public static final MainCategories_Database_MySQL mysql = new MainCategories_Database_MySQL();
        public static final MainCategories_Database_Oracle oracle = new MainCategories_Database_Oracle();
        
        private static class MainCategories_Database_MySQL extends CategoryBase {
        }
        private static class MainCategories_Database_Oracle extends CategoryBase {
        }
    }

    public static class MainCategories_Remote extends CategoryBase {
        public static final MainCategories_Remote_JMS jms = new MainCategories_Remote_JMS();
        public static final MainCategories_Remote_LDAP ldap = new MainCategories_Remote_LDAP();
        
        private static class MainCategories_Remote_LDAP extends CategoryBase {
        }
        private static class MainCategories_Remote_JMS extends CategoryBase {
        }
    }
}

