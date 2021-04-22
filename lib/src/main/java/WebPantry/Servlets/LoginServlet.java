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

        if (session != null) {
            // User already logged in, Send to Menu
            resp.sendRedirect("/Menu");
        } else {
            // User is not logged in, Create Login
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            PrintWriter httpout = resp.getWriter();
            PageBuilder pb = new PageBuilder();
            httpout.print(pb.makeLoginPage(false));
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        boolean success = false;

        if (session == null) {
            // User not logged in, check username and password
            SingleDB db = SingleDB.getInstance();
            UserList users = new UserList(db.connection);
            if (!users.checkPass(req.getParameter("username"), req.getParameter("pass"))) {
                // Failed, ask user to log in again
                resp.setContentType("text/html");
                resp.setCharacterEncoding("utf-8");
                PrintWriter httpout = resp.getWriter();
                PageBuilder pb = new PageBuilder();
                httpout.print(pb.makeLoginPage(true));
            } else {
                session = req.getSession();
                session.setAttribute("user", req.getParameter("username"));
                success = true;
            }
        } else { success = true; }
        if (success) {
            resp.sendRedirect("/Menu");
        }
    }

}
