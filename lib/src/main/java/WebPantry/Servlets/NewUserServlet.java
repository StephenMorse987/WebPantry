package WebPantry.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NewUser")
public class NewUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            // User already logged in, Send to Menu
            resp.sendRedirect("/lib/Menu");
        } else {
            // User not logged in, Create New User Page
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            PrintWriter httpout = resp.getWriter();
            PageBuilder pb = new PageBuilder();
            httpout.print(pb.makeNewUserPage(""));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            // User not logged in, process input data
        }
    }
    
}
