import Classes.Employee;
import Classes.Post;
import Classes.Project;
import DAO.EmployeeDAO;
import DAO.ProjectWithImplication;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;

import java.util.Arrays;
import java.util.List;

@ManagedBean
@RequestScoped
public class EmployeeBean {

    private String name;
    private String email;
    private String skillsAsString;
    private Post post;
    private List<Employee> employees ;
    private List<ProjectWithImplication> projects;
    // Liste des postes disponibles
    private List<Post> postList;
    private String alerte ;
    private String suppression ;

    public EmployeeBean() {
        // Initialise la liste des postes disponibles
        postList = Arrays.asList(Post.values());
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employees=employeeDAO.getAll();
    }

    public void addEmployee() {
        // Convertir la chaîne de compétences en une liste
        List<String> skills = Arrays.asList(skillsAsString.split(","));

        // Créer un nouvel objet Employee avec les valeurs saisies
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setSkills(skills);
        employee.setPost(post);

        // Enregistrer l'employé dans la base de données (logique de persistance)

        // Exemple de logique de persistance fictive
        // Remplacez cette partie avec votre propre logique de persistance
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.create(employee);
        alerte="Employé ajouté avec success";
        // Réinitialisation de l'alerte après 2 secondes (2000 millisecondes)

        // Afficher un message de succès
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employé ajouté avec succès", null));

        // Réinitialiser les valeurs saisies
        name = null;
        email = null;
        skillsAsString = null;
        post = null;
    }

    // Getters et setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkillsAsString() {
        return skillsAsString;
    }

    public void setSkillsAsString(String skillsAsString) {
        this.skillsAsString = skillsAsString;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<ProjectWithImplication> getProjects(Employee e) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employees=employeeDAO.getAll();
        projects=employeeDAO.getProjectsWithImplicationByEmployee(e);
        return projects;
    }

    public void setProjects(List<ProjectWithImplication> projects) {
        this.projects = projects;
    }

    public String getProjectName(Project p){
        return p.getName();
    }
    public void deleteEmployee(Long employeeId) {
        EmployeeDAO employeeDAO= new EmployeeDAO();
        Employee employee = employeeDAO.getById(employeeId);
        if (employee != null) {
            // Supprimer les relations avec les tables Skills et EmployeeProjectAssignment
            employee.setSkills(null);
            employee.setProjectAssignments(null);

            // Supprimer l'employé
            employeeDAO.delete(employee);
            suppression = "Employé supprimé avec success" ;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employé supprimé avec success", null));
            employees=employeeDAO.getAll();
        }
    }

    public String getSuppression() {
        return suppression;
    }

    public void setSuppression(String suppression) {
        this.suppression = suppression;
    }
}
