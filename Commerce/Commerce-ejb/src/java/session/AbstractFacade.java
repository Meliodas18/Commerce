/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author aymeric
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    
    public ArrayList<Long> getId(T entity, String[] parametres) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String requete = "Select id From " + entity.getClass().getSimpleName()+ " a Where ";
        for (String param : parametres){
            requete += "a." + param + "= :" + param + " and ";
        }
        requete = requete.substring(0, requete.length() - 4); // On enlève le dernier and en trop
        Query query = getEntityManager().createQuery(requete);        
        for (String param : parametres){
            Method methods = entity.getClass().getDeclaredMethod("get" + param.substring(0, 1).toUpperCase() + param.substring(1, param.length()), null);
            query.setParameter(param,methods.invoke(entity, null));
        }
        return (ArrayList<Long>) query.getResultList();
    }
    
    public ArrayList<Long> getIdForResearch(T entity, String[] parametres) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String requete = "Select id From " + entity.getClass().getSimpleName()+ " a Where ";
        for (String param : parametres){
            requete += "a." + param + " Like :" + param + " and ";
        }
        requete = requete.substring(0, requete.length() - 4); // On enlève le dernier and en trop
        Query query = getEntityManager().createQuery(requete);        
        for (String param : parametres){
            Method methods = entity.getClass().getDeclaredMethod("get" + param.substring(0, 1).toUpperCase() + param.substring(1, param.length()), null);
            query.setParameter(param,"%" + methods.invoke(entity, null) + "%");
        }
        return (ArrayList<Long>) query.getResultList();
    }

    public void remove(T entity){       
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
