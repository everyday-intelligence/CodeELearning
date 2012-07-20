/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domainControllers;

import codeelearning.domain.Theme;
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
public class ThemeJpaController implements Serializable {

    public ThemeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Theme theme) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(theme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Theme theme) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            theme = em.merge(theme);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = theme.getId();
                if (findTheme(id) == null) {
                    throw new NonexistentEntityException("The theme with id " + id + " no longer exists.");
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
            Theme theme;
            try {
                theme = em.getReference(Theme.class, id);
                theme.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The theme with id " + id + " no longer exists.", enfe);
            }
            em.remove(theme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Theme> findThemeEntities() {
        return findThemeEntities(true, -1, -1);
    }

    public List<Theme> findThemeEntities(int maxResults, int firstResult) {
        return findThemeEntities(false, maxResults, firstResult);
    }

    private List<Theme> findThemeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Theme.class));
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

    public Theme findTheme(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Theme.class, id);
        } finally {
            em.close();
        }
    }

    public int getThemeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Theme> rt = cq.from(Theme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
