package none.dao.impl;

import jakarta.persistence.*;
import none.configs.JPAConfig;
import none.dao.UserDao;
import none.entity.User;

public class UserDaoImpl implements UserDao {
  @Override
  public User findByUsername(String username){
    EntityManager em = JPAConfig.getEntityManager();
    try{
      TypedQuery<User> q = em.createNamedQuery("User.findByUsername", User.class);
      q.setParameter("username", username);
      return q.getResultStream().findFirst().orElse(null);
    } finally { em.close(); }
  }
}
