
package xbc.miniproject.com.xbcapplication.model.technology;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTechnology {

    @SerializedName("dataListTechnology")
    @Expose
    private List<DataListTechnology> dataListTechnology = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DataListTechnology> getDataListTechnology() {
        return dataListTechnology;
    }

    public void setDataListTechnology(List<DataListTechnology> dataListTechnology) {
        this.dataListTechnology = dataListTechnology;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
