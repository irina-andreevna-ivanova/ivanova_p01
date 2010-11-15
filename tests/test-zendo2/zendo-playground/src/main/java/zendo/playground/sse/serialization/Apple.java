package zendo.playground.sse.serialization;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: 11/13/10
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Apple implements Serializable {

//    private static final ObjectStreamField[] serialPersistentFields = {
//            new ObjectStreamField( "type", String.class ),
//            new ObjectStreamField( "pickingDate", Date.class ),
//            new ObjectStreamField( "branchNo", int.class )
//    };

    public String type;
    public Date pickingDate;
    public String pickerName;
    public int branchNo;

    public Apple() {
        System.out.println( ">> Apple Constructor" );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).
                append("type", type).
                append("pickerName", pickerName).
                append("pickingDate", pickingDate).
                append("branchNo", branchNo).
                toString();
    }

    private void readObject( ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.println(">> Apple readObject");
        type = (String) input.readObject();
        pickingDate = (Date) input.readObject();
        branchNo = input.readInt();
    }

    private void writeObject( ObjectOutputStream output ) throws IOException {
        System.out.println( ">> Apple writeObject" );
        output.writeObject(type);
        output.writeObject(pickingDate);
        output.writeInt(branchNo);
    }

}
