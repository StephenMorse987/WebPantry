package WebPantry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Activity")
public class ActivityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter httpout = resp.getWriter();
        httpout.println("GET " + req.getRequestURL());
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
        if (session != null) {
            httpout.println("Session Found!");
            httpout.println("Session ID: " + session.getId());
            httpout.println("Session Created: " + session.getCreationTime());
            httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
        } else {
            httpout.println("No Session Found!");
        }
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter httpout = resp.getWriter();
        httpout.println("POST " + req.getRequestURL());
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
        if ( session != null) {
            httpout.println("Session Found!");
            httpout.println("Session ID: " + session.getId());
            httpout.println("Session Created: " + session.getCreationTime());
            httpout.println("Session Last Accessed: " + session.getLastAccessedTime());
        } else {
            httpout.println("No Session Found!");
        }
        httpout.println("RSID is Valid: " + req.isRequestedSessionIdValid());
    }

    
}
