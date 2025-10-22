package none.service.impl;

import jakarta.persistence.*;
import none.configs.JPAConfig;
import none.entity.User;
import none.service.UserService;
import none.utils.PasswordUtil;

public class UserServiceImpl implements UserService {
  @Override
  public User register(String username, String plainPassword, String fullName, int roleId) throws Exception {
    EntityManager em = JPAConfig.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try{
      if (em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username=:u", Long.class)
            .setParameter("u", username).getSingleResult() > 0) {
        throw new IllegalArgumentException("Username đã tồn tại");
      }
      User u = new User(null, username, PasswordUtil.hash(plainPassword), fullName, roleId);
      tx.begin(); em.persist(u); tx.commit(); return u;
    } catch(RuntimeException ex){ if(tx.isActive()) tx.rollback(); throw ex; }
    finally { em.close(); }
  }

  @Override
  public User login(String username, String plainPassword) {
    EntityManager em = JPAConfig.getEntityManager();
    try{
      User u = em.createNamedQuery("User.findByUsername", User.class)
                 .setParameter("username", username)
                 .getResultStream().findFirst().orElse(null);
      if (u==null) return null;
      return PasswordUtil.matches(plainPassword, u.getPassword()) ? u : null;
    } finally { em.close(); }
  }
}
