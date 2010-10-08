package zendo.playground.instantiations.windowbuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;


/**
 *
 *
 * @author bogdan.mocanu
 */
public class MainWindow extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblSomeNewLabel;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater( new Runnable() {

            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible( true );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the frame.
     */
    public MainWindow() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds( 100, 100, 477, 347 );
        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        setContentPane( contentPane );

        JLabel label = new JLabel("New label");

        textField = new JTextField();
        textField.setColumns(10);

        lblSomeNewLabel = new JLabel("Some new label");

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblSomeNewLabel)
                        .addComponent(label))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(textField, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addGap(5))
                        .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSomeNewLabel)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(259, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
