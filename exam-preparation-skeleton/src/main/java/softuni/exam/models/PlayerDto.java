package softuni.exam.models;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class PlayerDto {
    
    public PlayerDto() {
    }

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer number;

    @Expose
    private String position;

    @Expose
    private BigDecimal salary;

    @Expose
    private PictureJsonDto picture;

    @Expose
    private TeamJsonDto team;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PictureJsonDto getPicture() {
        return picture;
    }

    public void setPicture(PictureJsonDto picture) {
        this.picture = picture;
    }

    public TeamJsonDto getTeam() {
        return team;
    }

    public void setTeam(TeamJsonDto team) {
        this.team = team;
    }
   
}
