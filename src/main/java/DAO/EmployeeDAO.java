package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.Employee;

import java.util.List;

public class EmployeeDAO implements GenericDAO<Employee, Long> {
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
    public void delete(Employee entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}