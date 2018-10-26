
package xbc.miniproject.com.xbcapplication.model.kelas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batch {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("technology")
    @Expose
    private Technology technology;
    @SerializedName("trainer")
    @Expose
    private Object trainer;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("periodFrom")
    @Expose
    private Object periodFrom;
    @SerializedName("periodTo")
    @Expose
    private Object periodTo;
    @SerializedName("room")
    @Expose
    private Object room;
    @SerializedName("bootcampType")
    @Expose
    private Object bootcampType;
    @SerializedName("notes")
    @Expose
    private Object notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public Object getTrainer() {
        return trainer;
    }

    public void setTrainer(Object trainer) {
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Object periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Object getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Object periodTo) {
        this.periodTo = periodTo;
    }

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public Object getBootcampType() {
        return bootcampType;
    }

    public void setBootcampType(Object bootcampType) {
        this.bootcampType = bootcampType;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

}
