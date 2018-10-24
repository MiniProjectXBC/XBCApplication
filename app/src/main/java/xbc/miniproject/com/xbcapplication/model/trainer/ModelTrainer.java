
package xbc.miniproject.com.xbcapplication.model.trainer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTrainer {

    @SerializedName("dataList")
    @Expose
    private List<DataListTrainer> dataList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DataListTrainer> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListTrainer> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
