/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domainControllers;

import codeelearning.domain.SingleChoiceQuestion;
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
public class SingleChoiceQuestionJpaController implements Serializable {

    public SingleChoiceQuestionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SingleChoiceQuestion singleChoiceQuestion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(singleChoiceQuestion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SingleChoiceQuestion singleChoiceQuestion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            singleChoiceQuestion = em.merge(singleChoiceQuestion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = singleChoiceQuestion.getId();
                if (findSingleChoiceQuestion(id) == null) {
                    throw new NonexistentEntityException("The singleChoiceQuestion with id " + id + " no longer exists.");
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
            SingleChoiceQuestion singleChoiceQuestion;
            try {
                singleChoiceQuestion = em.getReference(SingleChoiceQuestion.class, id);
                singleChoiceQuestion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The singleChoiceQuestion with id " + id + " no longer exists.", enfe);
            }
            em.remove(singleChoiceQuestion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SingleChoiceQuestion> findSingleChoiceQuestionEntities() {
        return findSingleChoiceQuestionEntities(true, -1, -1);
    }

    public List<SingleChoiceQuestion> findSingleChoiceQuestionEntities(int maxResults, int firstResult) {
        return findSingleChoiceQuestionEntities(false, maxResults, firstResult);
    }

    private List<SingleChoiceQuestion> findSingleChoiceQuestionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SingleChoiceQuestion.class));
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

    public SingleChoiceQuestion findSingleChoiceQuestion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SingleChoiceQuestion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSingleChoiceQuestionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SingleChoiceQuestion> rt = cq.from(SingleChoiceQuestion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
