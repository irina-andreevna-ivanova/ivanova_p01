/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zendo.playground.sd;

/**
 *
 * @author Bogdan
 */
public enum AlarmLevelType {

    UNKNOWN(0),

    NORMAL(1),

    WARNING(2),

    ALARM(3);

    private AlarmLevelType(int level) {
        this.level = level;
    }

    int level;

    public int getValue() {
        return level;
    }

}
