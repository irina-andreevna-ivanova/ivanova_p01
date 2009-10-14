/* Copyright Bogdan Mocanu, 2008
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package zendo.playground.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.webflow.executor.FlowExecutor;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;
import ro.bmocanu.zendo.annotations.Capability;
import ro.bmocanu.zendo.capabilities.CapabilityType;

/**
 * Testing custom non-web execution of webflows.
 *
 * @author mocanu
 */
public class TestCustomWebFlow extends AbstractZendoTest {
    
    @Capability( CapabilityType.SPRING )
    private ApplicationContext appContext;

    @Override
    public void test() {
        FlowExecutor executor = (FlowExecutor) appContext.getBean( "flowExecutor" );
        executor.launchExecution( "testFlow", null, null );
    }
    
    public static void main( String[] args ) {
        ZendoCore.testThis();
    }

}
