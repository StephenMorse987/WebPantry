package WebPantry.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import WebPantry.SingleDB;
import WebPantry.UserDAO;

@WebServlet("/GetUsernames")
public class GetUsernamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO users = new UserDAO(SingleDB.getInstance().connection);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        List<String> checkList = users.getAllUsernames();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, checkList);
    }
    
}
