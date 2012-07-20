/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domainControllers;

import codeelearning.domain.QuestionFigure;
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
public class QuestionFigureJpaController implements Serializable {

    public QuestionFigureJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QuestionFigure questionFigure) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(questionFigure);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QuestionFigure questionFigure) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            questionFigure = em.merge(questionFigure);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = questionFigure.getId();
                if (findQuestionFigure(id) == null) {
                    throw new NonexistentEntityException("The questionFigure with id " + id + " no longer exists.");
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
            QuestionFigure questionFigure;
            try {
                questionFigure = em.getReference(QuestionFigure.class, id);
                questionFigure.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questionFigure with id " + id + " no longer exists.", enfe);
            }
            em.remove(questionFigure);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QuestionFigure> findQuestionFigureEntities() {
        return findQuestionFigureEntities(true, -1, -1);
    }

    public List<QuestionFigure> findQuestionFigureEntities(int maxResults, int firstResult) {
        return findQuestionFigureEntities(false, maxResults, firstResult);
    }

    private List<QuestionFigure> findQuestionFigureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QuestionFigure.class));
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

    public QuestionFigure findQuestionFigure(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QuestionFigure.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestionFigureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QuestionFigure> rt = cq.from(QuestionFigure.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
