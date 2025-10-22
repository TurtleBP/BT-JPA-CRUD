package none.dao.impl;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import none.configs.JPAConfig;
import java.util.List;

public abstract class AbstractEntityDao<T> {
  private final Class<T> cls;
  protected AbstractEntityDao(Class<T> c){ this.cls=c; }

  public void insert(T e){
    EntityManager em=JPAConfig.getEntityManager();
    EntityTransaction tx=em.getTransaction();
    try{ tx.begin(); em.persist(e); tx.commit(); } catch(Exception ex){ if(tx.isActive())tx.rollback(); throw ex; }
    finally{ em.close(); }
  }
  public void update(T e){
    EntityManager em=JPAConfig.getEntityManager();
    EntityTransaction tx=em.getTransaction();
    try{ tx.begin(); em.merge(e); tx.commit(); } catch(Exception ex){ if(tx.isActive())tx.rollback(); throw ex; }
    finally{ em.close(); }
  }
  public void delete(Object id){
    EntityManager em=JPAConfig.getEntityManager();
    EntityTransaction tx=em.getTransaction();
    try{ tx.begin(); T f=em.find(cls,id); if(f!=null) em.remove(f); tx.commit(); } catch(Exception ex){ if(tx.isActive())tx.rollback(); throw ex; }
    finally{ em.close(); }
  }
  public T findById(Object id){
    EntityManager em=JPAConfig.getEntityManager();
    try{ return em.find(cls,id); } finally{ em.close(); }
  }
  public List<T> findAll(){
    EntityManager em=JPAConfig.getEntityManager();
    try{
      CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(cls);
      cq.select(cq.from(cls));
      return em.createQuery(cq).getResultList();
    } finally { em.close(); }
  }
  public Long countAll(){
    EntityManager em=JPAConfig.getEntityManager();
    try{
      CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
      Root<T> r = cq.from(cls);
      cq.select(em.getCriteriaBuilder().count(r));
      return em.createQuery(cq).getSingleResult();
    } finally { em.close(); }
  }
}
