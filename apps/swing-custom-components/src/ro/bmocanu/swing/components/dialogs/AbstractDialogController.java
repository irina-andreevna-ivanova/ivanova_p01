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

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

/**
 * Base class for the dialog controllers.
 *
 * @author mocanu
 */
public abstract class AbstractDialogController<DialogType extends JDialog, DataBeanType> {

    protected DialogType dialog;
    protected DialogCallback callback;
    protected DialogRequest dialogRequest;
    protected DataBeanType data;

    public void showDialog( DataBeanType dataBean, DialogRequest dialogRequest, Frame parent, DialogCallback callback ) {
        this.dialogRequest = dialogRequest;
        this.callback = callback;
        this.data = dataBean;

        dialog = createInstance( parent );
        dialog.setLocationRelativeTo( null );
        dialog.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    handleOkClick();
                    e.consume();
                }
                if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    handleCancelClick();
                    e.consume();
                }
            }
        } );

        moveDataInDialog();
        dialog.setVisible( true );
    }

    public void showDialog( DialogRequest dialogRequest, Frame parent ) {
        showDialog( null, dialogRequest, parent, null );
    }

    protected abstract DialogType createInstance( Frame parent );

    protected void moveDataInDialog() {
        // no code here
    }

    protected void moveDataInBean() {
        // no code here
    }

    protected boolean validateDialogFields() {
        // no code here
        return true;
    }

    public void handleOkClick() {
        if ( !validateDialogFields() ) {
            return;
        }

        moveDataInBean();
        disposeDialog();

        // perhaps some other args should be sent here??
        if ( callback != null ) {
            callback.handleDialogAction( DialogAction.OK, data );
        }
    }

    public void handleCancelClick() {
        disposeDialog();
        if ( callback != null ) {
            callback.handleDialogAction( DialogAction.CANCEL, data );
        }
    }

    // -------------------------------------------------------------------------------------------------

    private void disposeDialog() {
        dialog.setVisible( false );
        dialog.dispose();
        dialog = null;
    }

}
