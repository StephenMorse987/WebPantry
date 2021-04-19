package WebPantry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter httpout = resp.getWriter();
        httpout.println("GET " + req.getRequestURL());
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
        if (session != null) {
            httpout.println("Session already exists!");
            httpout.println("Session ID: " + session.getId());
            httpout.println("Session Created: " + session.getCreationTime());
            httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
        }
        else {
            httpout.println("No Session Found!");
            httpout.println("Sessions can only be created by logging in.");
        }
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter httpout = resp.getWriter();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e1) {
            httpout.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
            e1.printStackTrace(httpout);
        }

        httpout.println("POST " + req.getRequestURL());
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
        if (session != null) {
            httpout.println("Session already exists!");
            httpout.println("Session ID: " + session.getId());
            httpout.println("Session Created: " + session.getCreationTime());
            httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
        }
        else {
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "p4ssw0rd");
                httpout.println("Connection Created.");
                UserDAO users = new UserDAO(connection);
                if (users.checkPass(req.getParameter("username"), req.getParameter("pass"))) {
                    session = req.getSession();
                    httpout.println("Session Created!");
                    httpout.println("Session ID: " + session.getId());
                    httpout.println("Session Created: " + session.getCreationTime());
                    httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
                } else {
                    httpout.println("The username or password does not match existing information.");
                }
            } catch (SQLException e) {
                httpout.println("SQL Exception Occurred.");
                httpout.println(e.getMessage());
                e.printStackTrace(httpout);
            }
        }
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
    }

}
