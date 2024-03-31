package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import Classes.Project;

import java.util.List;

public class ProjectDAO implements GenericDAO<Project, Long> {
    private EntityManager entityManager;

    public ProjectDAO() {
        // Créer un EntityManager en utilisant les propriétés définies dans le fichier de persistance
        entityManager = Persistence.createEntityManagerFactory("myPU").createEntityManager();
    }

    @Override
    public void create(Project entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Project getById(Long id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> getAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Override
    public void update(Project entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Project entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}

