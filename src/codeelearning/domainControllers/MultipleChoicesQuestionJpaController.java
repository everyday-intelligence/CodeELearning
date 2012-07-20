/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domainControllers;

import codeelearning.domain.MultipleChoicesQuestion;
import codeelearning.domainControllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ramzi
 */
public class MultipleChoicesQuestionJpaController implements Serializable {

    public MultipleChoicesQuestionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MultipleChoicesQuestion multipleChoicesQuestion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(multipleChoicesQuestion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MultipleChoicesQuestion multipleChoicesQuestion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            multipleChoicesQuestion = em.merge(multipleChoicesQuestion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = multipleChoicesQuestion.getId();
                if (findMultipleChoicesQuestion(id) == null) {
                    throw new NonexistentEntityException("The multipleChoicesQuestion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MultipleChoicesQuestion multipleChoicesQuestion;
            try {
                multipleChoicesQuestion = em.getReference(MultipleChoicesQuestion.class, id);
                multipleChoicesQuestion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The multipleChoicesQuestion with id " + id + " no longer exists.", enfe);
            }
            em.remove(multipleChoicesQuestion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MultipleChoicesQuestion> findMultipleChoicesQuestionEntities() {
        return findMultipleChoicesQuestionEntities(true, -1, -1);
    }

    public List<MultipleChoicesQuestion> findMultipleChoicesQuestionEntities(int maxResults, int firstResult) {
        return findMultipleChoicesQuestionEntities(false, maxResults, firstResult);
    }

    private List<MultipleChoicesQuestion> findMultipleChoicesQuestionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MultipleChoicesQuestion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MultipleChoicesQuestion findMultipleChoicesQuestion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MultipleChoicesQuestion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMultipleChoicesQuestionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MultipleChoicesQuestion> rt = cq.from(MultipleChoicesQuestion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
