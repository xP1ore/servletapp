package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/file-servlet"})
@MultipartConfig(location = "/temp")
public class FileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            if (part.getName().equals("some_data")) {
                InputStream inputStream = part.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                String someData = new BufferedReader(isr)
                        .lines()
                        .collect(Collectors.joining("\n"));
                log(someData);
            } else {
                part.write(UUID.randomUUID() + part.getSubmittedFileName());
            }
        }

        resp.sendRedirect("/my-app/my-new-servlet");
    }
}
