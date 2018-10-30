
package xbc.miniproject.com.xbcapplication.model.assignment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelAssignment {

    @SerializedName("assignmentList")
    @Expose
    private List<AssignmentList> assignmentList = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<AssignmentList> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<AssignmentList> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
