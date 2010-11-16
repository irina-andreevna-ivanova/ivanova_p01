package zendo.playground.sse.serialization;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: 11/13/10
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestJavaSerialization {

    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
        AppleJson apple = new AppleJson();
        apple.type = "Arkansas Black";
        apple.branchNo = 5;
        apple.pickerName = "James Conworth";
        apple.pickingDate = new SimpleDateFormat("dd.MM.yyyy").parse("10.06.2010");
        System.out.println("First apple=" + apple);

        System.out.println("-----------------------------------------------------------------------------");
        byte[] appleContent = serialize(apple);
        System.out.println("Content=" + friendlyBytes(appleContent));
        System.out.println("-----------------------------------------------------------------------------");

        AppleJson newApple = deserialize(appleContent);
        System.out.println("Second apple=" + newApple);


        MyClass2 mc = new MyClass2();
        System.out.println( ">>>" + new String(serializex(mc)));
    }


    private static class MyClass {
        private String name = "Cat";
    }

    private static class MyClass2 extends MyClass implements Serializable{

    }

    private static <T extends Serializable> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } finally {
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(baos);
        }

        return baos.toByteArray();
    }

    private static <T extends Serializable> T deserialize(byte[] content) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            bais = new ByteArrayInputStream(content);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();

        } finally {
            IOUtils.closeQuietly(bais);
            IOUtils.closeQuietly(ois);
        }
    }


    private static String friendlyBytes(byte[] content) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < content.length; index++) {
            if (content[index] >= 32) {
                builder.append((char) content[index]);
            } else {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    private static byte[] serializex(Object object) throws IOException {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } finally {
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(baos);
        }

        return baos.toByteArray();
    }


}
