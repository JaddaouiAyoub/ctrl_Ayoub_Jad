package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import Classes.Employee;
import Classes.EmployeeProjectAssignment;
import Classes.Project;

import java.util.List;

public class EmployeeProjectAssignmentDAO implements GenericDAO<EmployeeProjectAssignment, Long> {
    private final EntityManager entityManager;

    public EmployeeProjectAssignmentDAO() {
        // Créer un EntityManager en utilisant les propriétés définies dans le fichier de persistance
        entityManager = Persistence.createEntityManagerFactory("myPU").createEntityManager();
    }

    @Override
    public void create(EmployeeProjectAssignment entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public EmployeeProjectAssignment getById(Long id) {
        return entityManager.find(EmployeeProjectAssignment.class, id);
    }

    @Override
    public List<EmployeeProjectAssignment> getAll() {
        return entityManager.createQuery("SELECT epa FROM EmployeeProjectAssignment epa", EmployeeProjectAssignment.class).getResultList();
    }

    @Override
    public void update(EmployeeProjectAssignment entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(EmployeeProjectAssignment entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
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

