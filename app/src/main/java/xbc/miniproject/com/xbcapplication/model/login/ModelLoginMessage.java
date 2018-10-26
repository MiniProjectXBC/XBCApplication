package xbc.miniproject.com.xbcapplication.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLoginMessage {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("generatedToken")
    @Expose
    private String generatedToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGeneratedToken() {
        return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
        this.generatedToken = generatedToken;
    }

}