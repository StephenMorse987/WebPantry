package WebPantry.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import WebPantry.PantryDAO;
import WebPantry.SingleDB;

@WebServlet("/GetItemNames")
public class GetItemNamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Return the user's full list of item names in JSON format
        HttpSession session = req.getSession(false);

        if (session != null) {
            PantryDAO pantry = new PantryDAO(SingleDB.getInstance().connection, session.getAttribute("user").toString());
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            List<String> checkList = pantry.getAllNames();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, checkList);
        }
    }
    
}
