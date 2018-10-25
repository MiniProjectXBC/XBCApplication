
package xbc.miniproject.com.xbcapplication.model.biodata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBiodata {

    @SerializedName("dataList")
    @Expose
    private List<BiodataList> dataList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<BiodataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<BiodataList> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
