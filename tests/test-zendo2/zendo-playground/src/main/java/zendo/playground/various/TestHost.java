/**
 *
 */
package zendo.playground.various;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 *
 * @author mocanu
 */
public class TestHost {

    public static void main(String args[]) {
        args = new String[] { "10.124.1.94" };
        for (String name: args) {
          try {
            InetAddress address[] =
                InetAddress.getAllByName(name);
            for (InetAddress each: address) {
              System.out.println("Name: " + each.getHostName());
              System.out.println("Addr: " +
                  each.getHostAddress());
              System.out.println("Canonical: " +
                  each.getCanonicalHostName());
            }
            System.out.println("----");
          } catch (UnknownHostException e) {
            System.err.println("Unable to lookup " + name);
          }
        }
      }

}
