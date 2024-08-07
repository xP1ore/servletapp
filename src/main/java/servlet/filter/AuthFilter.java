package servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlet.common.ROLE;
import servlet.dao.ServiceUserDao;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<ServiceUserDao> dao = (AtomicReference<ServiceUserDao>) req
                .getServletContext()
                .getAttribute("dao");

        final HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            final ROLE role = (ROLE) session.getAttribute("role");

            moveToMenu(req, resp, role);

        } else if (dao.get().userExists(login, password)) {
            final ROLE role = dao.get().getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, resp, role);

        } else {
            moveToMenu(req, resp, ROLE.UNKNOWN);
        }
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse resp, final ROLE role) throws ServletException, IOException {
        if (role.equals(ROLE.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/view/admin-menu.jsp").forward(req, resp);
        } else if (role.equals(ROLE.USER)) {
            req.getRequestDispatcher("/WEB-INF/view/user-menu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
