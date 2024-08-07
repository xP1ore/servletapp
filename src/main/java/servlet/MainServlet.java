package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/my-new-servlet"})
public class MainServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("method init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Method service enter\n");
        super.service(req, resp);
        resp.getWriter().write("Method service exit\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String params = formatParams(req);
        resp.getWriter().write("Method doGet\nURI: " + uri + "\nParams:\n" + params + "\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String params = formatParams(req);
        resp.getWriter().write("Method doPost\nURI: " + uri + "\nParams:\n" + params + "\n");
    }

    private String formatParams(HttpServletRequest req) {
        return req.getParameterMap()
                .entrySet()
                .stream()
                .map(stringEntry -> {
                        String param = String.join(" and ", stringEntry.getValue());
                        return stringEntry.getKey() + " => " + param;
                })
                .collect(Collectors.joining("\n"));
    }


    @Override
    public void destroy() {
        super.destroy();
        log("Method destroy");
    }
}
