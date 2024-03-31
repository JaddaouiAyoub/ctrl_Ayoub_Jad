import Classes.Employee;
import Classes.EmployeeProjectAssignment;
import Classes.Project;
import DAO.EmployeeDAO;
import DAO.EmployeeProjectAssignmentDAO;
import DAO.ProjectDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AffectationBean implements Serializable {
    private String alerte;
    private EmployeeDAO employeeDAO=new EmployeeDAO();
    private ProjectDAO projectDAO= new ProjectDAO();
    private List<Employee> employeeList;
    private List<Project> projectList;
    private List<String> percentageList;
    private Long selectedEmployeeId;
    private Long selectedProjectId;
    private String implication;

    public AffectationBean() {
        // Initialise les listes d'employés, de projets et de pourcentages
        employeeList = employeeDAO.getAll();

        projectList = projectDAO.getAll();

        percentageList = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10) {
            percentageList.add(i + "%");
        }
    }

    public void affecterProjet() {
        EmployeeProjectAssignmentDAO employeeProjectAssignmentDAO = new EmployeeProjectAssignmentDAO();
        EmployeeProjectAssignment assignment = new EmployeeProjectAssignment();
        Employee e = employeeDAO.getById(selectedEmployeeId);
        assignment.setEmployee(e);
        Project p = projectDAO.getById(selectedProjectId);
        assignment.setProject(p);
        assignment.setImplecation(implication);
        employeeProjectAssignmentDAO.create(assignment);
        // Affichage de l'alerte
        alerte = "Le projet a été affecté avec succès.";
        System.out.println(selectedEmployeeId);
        System.out.println(selectedProjectId);
    }

    // Getters et setters

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<String> getPercentageList() {
        return percentageList;
    }

    public void setPercentageList(List<String> percentageList) {
        this.percentageList = percentageList;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public Long getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(Long selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

}
