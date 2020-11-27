package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(name = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
        List<Post> resultList = query.getResultList();

        return resultList;
    }

    public Post getLatestPost() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Post.class, 3);
    }

    public Post createPost(Post newPost) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newPost);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }

        return newPost;
    }

    public Post getPost(Integer postId) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Post.class, postId);
    }

    public void updatePost(Post updatedPost) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(updatedPost);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Post deletedPost = entityManager.find(Post.class, postId);
            entityManager.remove(deletedPost);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public void deletePost(String postTitle) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE title =:title",Post.class);
            query.setParameter("title",postTitle);
            Post post = query.getSingleResult();
            em.remove(post);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }
}
