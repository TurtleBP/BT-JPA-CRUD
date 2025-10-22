package none.boot;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppLifecycle implements ServletContextListener {
  @Override public void contextInitialized(ServletContextEvent sce) {
    System.out.println("✅ App started");
  }
  @Override public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("🛑 App stopped");
  }
}
