package DAO;

import DAO.EmployeeDAO;
import DAO.EmployeeProjectAssignmentDAO;
import DAO.ProjectDAO;
import org.example.Employee;
import org.example.EmployeeProjectAssignment;
import org.example.Project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de EmployeeDAO, ProjectDAO et EmployeeProjectAssignmentDAO
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        EmployeeProjectAssignmentDAO employeeProjectAssignmentDAO = new EmployeeProjectAssignmentDAO();

        // Créer des employés
        Employee employee1 = new Employee();
        employee1.setName("John Doe112");
        employee1.setEmail("john@example11.com");

        List<String> skills = new ArrayList<>();
        skills.add("football");
        skills.add("voleyball");
        skills.add("basket");
        employee1.setSkills(skills);
//        Employee employee2 = new Employee();
//        employee2.setName("Jane Smith11");
//        employee2.setEmail("jane@example11.com");
//        employee2.setSkills(skills);
//        // Créer des projets
//        Project project1 = new Project();
//        project1.setName("Project a");
//        project1.setBudget(10000);
//
//        Project project2 = new Project();
//        project2.setName("Project b");
//        project2.setBudget(15000);
//
//        // Enregistrer les employés et les projets dans la base de données
        employeeDAO.create(employee1);
//        employeeDAO.create(employee2);
////
//        projectDAO.create(project1);
//        projectDAO.create(project2);
//        Employee employee = employeeDAO.getById(1L);
//
////        // Créer et enregistrer des affectations d'employés à des projets
//        EmployeeProjectAssignment assignment1 = new EmployeeProjectAssignment();
//        assignment1.setEmployee(employee1);
//        assignment1.setProject(project1);
//        assignment1.setImplecation("Lead Developer");
//        employeeProjectAssignmentDAO.create(assignment1);
////
//        EmployeeProjectAssignment assignment2 = new EmployeeProjectAssignment();
//        assignment2.setEmployee(employee2);
//        assignment2.setProject(project1);
//        assignment2.setImplecation("Tester");
//        employeeProjectAssignmentDAO.create(assignment2);
////
//        EmployeeProjectAssignment assignment3 = new EmployeeProjectAssignment();
//        assignment3.setEmployee(employee2);
//        assignment3.setProject(project2);
//        assignment3.setImplecation("Project Manager");
//        employeeProjectAssignmentDAO.create(assignment3);
//
        List<Employee> employees = employeeDAO.getAll();
        for(Employee employee:employees){
            System.out.println(employee);
        }
        Employee test = employeeDAO.getById(1L);
        List<String> skls = test.getSkills();
        for(String s : skls){
            System.out.println(s);
        }
        // Récupérer les affectations de la base de données
//       List<EmployeeProjectAssignment> assignments = employeeProjectAssignmentDAO.getAll();
//
//        // Afficher les employés avec leurs projets et détails d'affectation
//        for (EmployeeProjectAssignment assignment : assignments) {
//            System.out.println("Employee: " + assignment.getEmployee().getName());
//            System.out.println("Project: " + assignment.getProject().getName());
//            System.out.println("Assignment Details: " + assignment.getImplecation());
//            System.out.println();
//        }
    }
}
