package DAO;

import Classes.EmployeeProjectAssignment;
import Classes.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import Classes.Employee;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAO implements GenericDAO<Employee, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeDAO() {
        // Créer un EntityManager en utilisant les propriétés définies dans le fichier de persistance
        entityManager = Persistence.createEntityManagerFactory("myPU").createEntityManager();
    }

    @Override
    public void create(Employee entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Employee getById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAll() {
        return entityManager.createQuery(
                        "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.skills", Employee.class)
                .getResultList();
    }

    @Override
    public void update(Employee entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Employee entity) {
        entityManager.getTransaction().begin();

        // Supprimer les enregistrements dans EMPLOYEE_PROJECT_ASSIGNMENT liés à l'employé
        Query deleteQuery = entityManager.createQuery("DELETE FROM EmployeeProjectAssignment epa WHERE epa.employee = :employee");
        deleteQuery.setParameter("employee", entity);
        deleteQuery.executeUpdate();
        // Supprimer l'employé
        entityManager.remove(entity);

        entityManager.getTransaction().commit();
    }
    public List<Project> getProjectsByEmployeeId(long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee != null) {
            return employee.getProjectAssignments()
                    .stream()
                    .map(EmployeeProjectAssignment::getProject)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    public List<ProjectWithImplication> getProjectsWithImplicationByEmployee(Employee employee) {
        Query query = entityManager.createQuery(
                "SELECT NEW DAO.ProjectWithImplication(epa.project,epa.implecation) " +
                        "FROM EmployeeProjectAssignment epa " +
                        "WHERE epa.employee = :employee"
        );
        query.setParameter("employee", employee);
        return query.getResultList();
    }
}