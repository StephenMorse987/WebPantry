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
import WebPantry.User;
import WebPantry.UserDAO;

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
            httpout.print(pb.makeNewUserPage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            // User not logged in, process input data
            boolean valid = true;
            String username = "";
            String email = "";
            String password = "";
            try {
                username = req.getParameter("username");
                if (username.length() < 1) throw new Exception("The username is empty.");
                email = req.getParameter("email");
                if (!email.matches("^.+@.+\\..+$")) throw new Exception("The Email is not in an expected format");
                password = req.getParameter("pass");
                if (password.length() < 1) throw new Exception("The password is empty.");
            } catch (Exception e) {
                // Reject ALL invalid inputs
                valid = false;
            }

            if (valid) {
                UserDAO users = new UserDAO(SingleDB.getInstance().connection);
                users.insert(new User(username, email, password));
                session = req.getSession();
                session.setAttribute("user", username);
            }

            resp.sendRedirect("/lib/Menu");
        } else {
            resp.sendRedirect("/lib/Menu");
        }
    }
    
}
