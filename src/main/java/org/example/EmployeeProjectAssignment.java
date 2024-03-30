package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_project_assignment")
public class EmployeeProjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String implecation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getImplecation() {
        return implecation;
    }

    public void setImplecation(String implecation) {
        this.implecation = implecation;
    }
}
