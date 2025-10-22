package none.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAConfig {
  private static final EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("MyPersistenceUnit");

  private JPAConfig() {}
  public static EntityManager getEntityManager() {
    return emf.createEntityManager();
  }
}
