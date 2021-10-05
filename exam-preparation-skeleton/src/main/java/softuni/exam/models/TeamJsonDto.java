package softuni.exam.models;

import com.google.gson.annotations.Expose;

public class TeamJsonDto {
    
    public TeamJsonDto() {
    }

    @Expose
    private String name;

    @Expose
    private PictureJsonDto picture;

    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureJsonDto getPicture() {
        return picture;
    }

    public void setPicture(PictureJsonDto picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
