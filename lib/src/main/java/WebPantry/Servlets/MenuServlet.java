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

@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            // Session found, display menu
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            PrintWriter httpout = resp.getWriter();
            SingleDB db = SingleDB.getInstance();
            PageBuilder pb = new PageBuilder();
            httpout.print(pb.makeMenuPage(db.connection, session.getAttribute("user").toString()));
        } else {
            // Session not found, return to Login
            resp.sendRedirect("/lib/Login");
        }
    }
    
}
