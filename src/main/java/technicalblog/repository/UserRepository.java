package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public void registerUser(User newUser) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(newUser);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public User checkUser(String username, String password) {
        try {
            EntityManager entityManager = emf.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
