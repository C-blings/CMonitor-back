package models;


import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class Project {
    public Project(){
        this.projectName = "default";
    }
    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String projectName;
}
