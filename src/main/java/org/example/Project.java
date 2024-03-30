package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double budget;

    @OneToMany(mappedBy = "project")
    private List<EmployeeProjectAssignment> employeeAssignments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<EmployeeProjectAssignment> getEmployeeAssignments() {
        return employeeAssignments;
    }

    public void setEmployeeAssignments(List<EmployeeProjectAssignment> employeeAssignments) {
        this.employeeAssignments = employeeAssignments;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", employeeAssignments=" + employeeAssignments +
                '}';
    }
}

