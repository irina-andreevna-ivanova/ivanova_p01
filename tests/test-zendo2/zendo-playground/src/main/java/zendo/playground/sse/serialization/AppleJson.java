package zendo.playground.sse.serialization;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: 11/13/10
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppleJson implements Externalizable {

    public String type;
    public Date pickingDate;
    public String pickerName;
    public int branchNo;

    public AppleJson() {
        System.out.println(">> Apple Constructor");
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            int contentLength = in.readInt();
            byte[] content = new byte[ contentLength ];
            in.read(content, 0, contentLength);

            JSONObject object = new JSONObject(new String(content));
            type = object.getString("type");
            pickerName = object.getString("pickerName");
            pickingDate = dateFormat.parse(object.getString("pickingDate"));
            branchNo = object.getInt("branchNo");

        } catch (JSONException e) {
            throw new IOException(e);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            JSONObject object = new JSONObject();
            object.put("type", type);
            object.put("pickingDate", dateFormat.format(pickingDate));
            object.put("pickerName", pickerName);
            object.put("branchNo", branchNo);

            byte[] content = object.toString().getBytes();
            out.writeInt(content.length);
            out.write(content);
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

}
