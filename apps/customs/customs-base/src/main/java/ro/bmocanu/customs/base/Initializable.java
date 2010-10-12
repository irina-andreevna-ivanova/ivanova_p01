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
 * Interface for classes that can be initialized. Defines the {@link #init()} method, therefore
 * establishing a standard name for this method, and removing the appereance of names such as
 * <i>initialize()</i>, <i>initialise()</i> or any other form.
 *
 * @author bogdan.mocanu
 */
public interface Initializable {

    /**
     * Initializes the current class, bringing it in a state where the usage of the services
     * provided by it can safely commence.
     */
    void init();

}
