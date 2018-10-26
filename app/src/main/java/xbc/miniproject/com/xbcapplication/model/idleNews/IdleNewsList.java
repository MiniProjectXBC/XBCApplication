
package xbc.miniproject.com.xbcapplication.model.idleNews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdleNewsList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("publish")
    @Expose
    private Object publish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getPublish() {
        return publish;
    }

    public void setPublish(Object publish) {
        this.publish = publish;
    }

}
