package servlet.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import servlet.dao.ServiceUserDao;
import servlet.model.ServiceUser;
import servlet.common.ROLE;

import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {
    // Fake db connector
    private AtomicReference<ServiceUserDao> dao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dao = new AtomicReference<>(new ServiceUserDao());

        dao.get().addUser(new ServiceUser(1, "Admin", "1", ROLE.ADMIN));
        dao.get().addUser(new ServiceUser(2, "SimpleUser", "2", ROLE.USER));

        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
