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

package ro.bmocanu.swing.components.dialogs;

/**
 * Small interface for objects that are called when a dialog is closed. This allows one controller
 * to pass in a handler that is invoked when another controller's dialog is closed. This allows for
 * particular actions to be taken, in a transparent manner for the controller invoked.
 * 
 * @author mocanu
 */
public interface DialogCallback {

    void handleDialogAction( DialogAction action, Object... args );

}
