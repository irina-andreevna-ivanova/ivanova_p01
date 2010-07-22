/**
 * 
 */
package ro.bmocanu.zendo.material.simple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ro.bmocanu.zendo.material.MaterialFactory;

/**
 * 
 *
 * @author mocanu
 */
public class SimpleMaterialFactory extends MaterialFactory {

    public static Account createAccountRandom() {
        Account acc = new Account();
        acc.setId( nextConsecutiveInt() );
        acc.setAccountNo( randomSID() );
        acc.setBalance( randomNr( 100, 50000 ) );
        acc.setLastUpdate( randomDate() );
        return acc;
    }

    public static Member createMemberRandom() {
        Member mb = new Member();
        mb.setId( nextConsecutiveInt() );
        mb.setFirstName( randomName() );
        mb.setLastName( randomName() );
        mb.setAge( randomNr( 20, 70 ) );

        Set<Account> accounts = new HashSet<Account>( Arrays.asList( createAccountRandom(), createAccountRandom() ) );
        mb.setAccounts( accounts );

        return mb;
    }

    public static Club createClubRandom() {
        Club club = new Club();
        club.setId( nextConsecutiveInt() );
        club.setAddress( randomAddress() );
        club.setName( "Club " + randomName() );

        Set<Member> members = new HashSet<Member>( Arrays.asList( createMemberRandom(), createMemberRandom(), createMemberRandom() ) );
        club.setMembers( members );

        return club;
    }

}
