
package xbc.miniproject.com.xbcapplication.model.biodata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastEducation")
    @Expose
    private Object lastEducation;
    @SerializedName("graduationYear")
    @Expose
    private Object graduationYear;
    @SerializedName("educationalLevel")
    @Expose
    private Object educationalLevel;
    @SerializedName("majors")
    @Expose
    private String majors;
    @SerializedName("gpa")
    @Expose
    private String gpa;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(Object lastEducation) {
        this.lastEducation = lastEducation;
    }

    public Object getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Object graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Object getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(Object educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
