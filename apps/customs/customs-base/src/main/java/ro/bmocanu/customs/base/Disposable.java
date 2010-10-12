/*-
 * Copyright Bogdan Mocanu, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.bmocanu.customs.base;

/**
 * Interface for classes that can be <i>disposed</i>. Defines the {@link #dispose()} method,
 * therefore establishing a standard name for this method, and removing the appereance of any other
 * names with similar meaning.
 * <p>
 * The meaning of this interface is very similar to {@link Destroy}. Choose the one that fits better
 * the implementation concept.
 *
 * @author bogdan.mocanu
 * @see Destroy
 */
public interface Disposable {

    /**
     * Allows the class implementing this method to release any resources acquired during the
     * execution, and prepare for a complete shutdown.
     */
    void dispose();

}
