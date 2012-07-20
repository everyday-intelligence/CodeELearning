/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domainControllers;

import codeelearning.domain.Choice;
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
public class ChoiceJpaController implements Serializable {

    public ChoiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Choice choice) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(choice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Choice choice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            choice = em.merge(choice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = choice.getId();
                if (findChoice(id) == null) {
                    throw new NonexistentEntityException("The choice with id " + id + " no longer exists.");
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
            Choice choice;
            try {
                choice = em.getReference(Choice.class, id);
                choice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The choice with id " + id + " no longer exists.", enfe);
            }
            em.remove(choice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Choice> findChoiceEntities() {
        return findChoiceEntities(true, -1, -1);
    }

    public List<Choice> findChoiceEntities(int maxResults, int firstResult) {
        return findChoiceEntities(false, maxResults, firstResult);
    }

    private List<Choice> findChoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Choice.class));
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

    public Choice findChoice(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Choice.class, id);
        } finally {
            em.close();
        }
    }

    public int getChoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Choice> rt = cq.from(Choice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
