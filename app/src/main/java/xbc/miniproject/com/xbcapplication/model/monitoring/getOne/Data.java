
package xbc.miniproject.com.xbcapplication.model.monitoring.getOne;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("biodata")
    @Expose
    private Biodata biodata;
    @SerializedName("idleDate")
    @Expose
    private String idleDate;
    @SerializedName("lastProject")
    @Expose
    private String lastProject;
    @SerializedName("idleReason")
    @Expose
    private String idleReason;
    @SerializedName("placementDate")
    @Expose
    private Object placementDate;
    @SerializedName("placementAt")
    @Expose
    private Object placementAt;
    @SerializedName("notes")
    @Expose
    private Object notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public String getIdleDate() {
        return idleDate;
    }

    public void setIdleDate(String idleDate) {
        this.idleDate = idleDate;
    }

    public String getLastProject() {
        return lastProject;
    }

    public void setLastProject(String lastProject) {
        this.lastProject = lastProject;
    }

    public String getIdleReason() {
        return idleReason;
    }

    public void setIdleReason(String idleReason) {
        this.idleReason = idleReason;
    }

    public Object getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Object placementDate) {
        this.placementDate = placementDate;
    }

    public Object getPlacementAt() {
        return placementAt;
    }

    public void setPlacementAt(Object placementAt) {
        this.placementAt = placementAt;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

}
