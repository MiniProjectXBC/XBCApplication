
package xbc.miniproject.com.xbcapplication.model.feedback.postCreate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCreateFeedback {

    @SerializedName("test")
    @Expose
    private Test test;
    @SerializedName("feedback")
    @Expose
    private List<Feedback> feedback = null;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

}
