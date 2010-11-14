package zendo.playground.sse.serialization;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: 11/13/10
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Apple implements Serializable{

    public String type;
    public Date pickingDate;
    public transient String pickerName;
    public int branchNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).
                append("type", type).
                append("pickerName", pickerName).
                append("pickingDate", pickingDate).
                append("branchNo", branchNo).
                toString();
    }
}
