package softuni.exam.models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamDto implements Serializable {
    
    public TeamDto() {
    }

    @XmlElement (name = "team")
    private List<Team> teams;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "picture")
    private Picture picture;

    // @XmlTransient
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "TeamDto (name=" + name + ", picture=" + picture + ")";
    }

}
