package DAO;

import Classes.Project;

public class ProjectWithImplication {
    private Project project;
    private String implication;

    public ProjectWithImplication(Project project, String implication) {
        this.project = project;
        this.implication = implication;
    }
    public String getName(){
        return  this.project.getName();
    }
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    @Override
    public String toString() {
        return "ProjectWithImplication{" +
                "project=" + project +
                ", implication='" + implication + '\'' +
                '}';
    }
}