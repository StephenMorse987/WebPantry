package WebPantry.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebPantry.PantryDAO;
import WebPantry.PantryItem;
import WebPantry.SingleDB;

@WebServlet("/DeleteItem")
public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            PantryDAO pantry = new PantryDAO(SingleDB.getInstance().connection, session.getAttribute("user").toString());
            pantry.delete(new PantryItem(Integer.parseInt(req.getParameter("itemindex")), 0L, 1, ""));
        }
    }
    
}
