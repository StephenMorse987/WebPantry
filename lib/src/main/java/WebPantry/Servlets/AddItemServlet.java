package WebPantry.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebPantry.PantryDAO;
import WebPantry.PantryItem;
import WebPantry.SingleDB;

@WebServlet("/AddItem")
public class AddItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            // User Logged In, proceed as planned
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            PrintWriter httpout = resp.getWriter();
            PageBuilder pb = new PageBuilder();
            httpout.print(pb.makeAddItemPage());
        } else {
            // User Not Logged In, return to Login Page
            resp.sendRedirect("/lib/Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            // User Logged In, proceed as planned
            boolean valid = true;
            float amountField = 0L;
            int measureField = 1;
            String nameField = "";
            try {
                amountField = Float.parseFloat(req.getParameter("item1-amount"));
                if (amountField <= 0) throw new NumberFormatException("The amount can not be less than or equal to 0.");
                measureField = Integer.parseInt(req.getParameter("item1-measure"));
                if ((measureField < 1) || (measureField > 28)) throw new ArrayIndexOutOfBoundsException("The measure must be one of the listed values.");
                nameField = req.getParameter("item1-name");
                if (nameField.length() < 1) throw new NullPointerException("The name is of an invalid length.");
            }
            catch (Exception e) {
                // Reject ALL invalid inputs
                valid = false;
                // Pantry Database will automatically reject duplicate item names
            }
            
            if (valid) {
                PantryDAO pantry = new PantryDAO(SingleDB.getInstance().connection, session.getAttribute("user").toString());
                pantry.insert(new PantryItem(0, amountField, measureField, nameField));
            }

            resp.sendRedirect("/lib/Menu");
        } else {
            // User Not Logged In, return to Login Page
            resp.sendRedirect("/lib/Login");
        }
    }
    
}
