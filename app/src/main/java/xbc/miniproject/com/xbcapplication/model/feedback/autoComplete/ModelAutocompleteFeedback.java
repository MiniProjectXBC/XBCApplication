
package xbc.miniproject.com.xbcapplication.model.feedback.autoComplete;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import xbc.miniproject.com.xbcapplication.model.feedback.autoComplete.DataListAutocompleteFeedback;

public class ModelAutocompleteFeedback {

    @SerializedName("dataList")
    @Expose
    private List<DataListAutocompleteFeedback> dataList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DataListAutocompleteFeedback> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListAutocompleteFeedback> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
