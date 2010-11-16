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
public class AppleExt implements Externalizable {

    public String type;
    public Date pickingDate;
    public String pickerName;
    public int branchNo;

    public AppleExt() {
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

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        type = (String) in.readObject();
        pickingDate = (Date) in.readObject();
        branchNo = in.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( type );
        out.writeObject(pickingDate);
        out.writeInt(branchNo);
    }

}
