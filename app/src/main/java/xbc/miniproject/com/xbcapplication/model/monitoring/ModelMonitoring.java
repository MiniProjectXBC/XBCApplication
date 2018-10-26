
package xbc.miniproject.com.xbcapplication.model.monitoring;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMonitoring {

    @SerializedName("data")
    @Expose
    private MonitoringData data;
    @SerializedName("dataList")
    @Expose
    private List<MonitoringDataList> monitoringDataList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public void setData(MonitoringData data) {
        this.data = data;
    }

    public List<MonitoringDataList> getMonitoringDataList() {
        return monitoringDataList;
    }

    public void setMonitoringDataList(List<MonitoringDataList> monitoringDataList) {
        this.monitoringDataList = monitoringDataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
