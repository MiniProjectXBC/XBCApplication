
package xbc.miniproject.com.xbcapplication.model.role;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRole {

    @SerializedName("dataList")
    @Expose
    private List<DataListRole> dataList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DataListRole> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListRole> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
