package zendo.playground.sse.serialization.json;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 25.08.2010
 * Time: 18:52:02
 * To change this template use File | Settings | File Templates.
 */
public class Department implements Serializable {

    private String name;
    private int buildingNo;
    private String accessCode;
    private int entryCode;

    /**
     * @param name
     * @param buildingNo
     * @param accessCode
     * @param entryCode
     */
    public Department(String name, int buildingNo, String accessCode, int entryCode) {
        this.name = name;
        this.buildingNo = buildingNo;
        this.accessCode = accessCode;
        this.entryCode = entryCode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", buildingNo=" + buildingNo +
                ", accessCode='" + accessCode + '\'' +
                ", entryCode=" + entryCode +
                '}';
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        // ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // oos.defaultWriteObject();

    }

}
