package zendo.playground.sse.serialization.json;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 25.08.2010
 * Time: 18:53:40
 * To change this template use File | Settings | File Templates.
 */
public class TestJsonSerialization {

    public static void main(String[] args) {
        Department dept = new Department("C++", 144, "445655", 145);
        System.out.println("Dept before: " + dept);

        byte[] serializedForm = objectToByteArray(dept);
        System.out.println("Dept serialized: " + new String(serializedForm));

        if (serializedForm != null) {
            Department dept2 = objectFromByteArray(serializedForm);
            System.out.println("Dept after: " + dept);
        }
    }

    private static <ObjectType> ObjectType objectFromByteArray(byte[] array) {
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            return (ObjectType) ois.readObject();

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private static byte[] objectToByteArray(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return baos.toByteArray();
    }

}
