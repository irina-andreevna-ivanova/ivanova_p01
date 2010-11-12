package zendo.playground.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 10.11.2010
 * Time: 16:14:47
 * To change this template use File | Settings | File Templates.
 */
public class TestWinSize extends JFrame implements Runnable {

    private JLabel sizeLabel;

    public TestWinSize() throws HeadlessException {
        setTitle("Window Size test");

        sizeLabel = new JLabel("0:0 - 0x0");
        this.getContentPane().add(sizeLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int x = (int) Math.round(TestWinSize.this.getLocation().getX());
                    int y = (int) Math.round(TestWinSize.this.getLocation().getY());
                    int width = TestWinSize.this.getWidth();
                    int height = TestWinSize.this.getHeight();
                    sizeLabel.setText(x + ":" + y + " - " + width + "x" + height);
                }
            });
        }
    }

    public static void main(String[] args) {
        TestWinSize tws = new TestWinSize();
        new Thread(tws).start();
    }
}