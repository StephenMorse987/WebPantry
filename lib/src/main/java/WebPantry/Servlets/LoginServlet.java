package WebPantry.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebPantry.SingleDB;
import WebPantry.UserList;

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
            httpout.println("Username: " + session.getAttribute("user"));
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

        httpout.println("POST " + req.getRequestURL());
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
        if (session != null) {
            httpout.println("Session already exists!");
            httpout.println("Username: " + session.getAttribute("user"));
            httpout.println("Session ID: " + session.getId());
            httpout.println("Session Created: " + session.getCreationTime());
            httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
        }
        else {
            SingleDB db = SingleDB.getInstance();
            UserList users = new UserList(db.connection);
            if (users.checkPass(req.getParameter("username"), req.getParameter("pass"))) {
                session = req.getSession();
                httpout.println("Session Created!");
                session.setAttribute("user", req.getParameter("username"));
                httpout.println("Username: " + session.getAttribute("user"));
                httpout.println("Session ID: " + session.getId());
                httpout.println("Session Created: " + session.getCreationTime());
                httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
            } else {
                httpout.println("The username or password does not match existing information.");
            }
        }
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
    }

}
