package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/temp-servlet")
public class MyJspServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> users = new ArrayList<>() {{
           add(new User("Igor", 22));
           add(new User("Lesha", 45));
           add(new User("Maksik", 12));
        }};

        req.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/myJsp.jsp").forward(req, resp);
    }
}
