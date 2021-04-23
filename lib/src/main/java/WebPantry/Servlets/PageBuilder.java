package WebPantry.Servlets;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import WebPantry.PantryDAO;
import WebPantry.PantryItem;

public class PageBuilder {

    public String makeLoginPage (boolean alreadyFailed) {
        String failMessage = "";
        if (alreadyFailed) {
            failMessage = "<p class=\"text-warning\">The username and/or password you entered were not recognized.</p>";
        }
        return makeHTMLTag(makeHead(addMeta("charset=\"UTF-8\"") + addMeta("http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"") + addMeta("name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"") + makeTitle("Pantry Landing Page") + linkStylesheet("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css") + linkJS("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js") + linkJS("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js")) + makeBody(makeDiv(makeHeader(3, "Please Login"), "container-fluid pt-3") + makeDiv(makeForm(failMessage + makeDiv(makeFormItem("Username:", "text", "form-control", "Enter Username", "username"), "form-group") + makeDiv(makeFormItem("Password:", "password", "form-control", "Enter Password", "pass"), "form-group") + makeButton("Submit", "submit", "btn btn-primary"), "Login", "POST"), "container-fluid pt-3") + makeDiv(makeParagraph(makeLink("I don't have a login", "/lib/NewUser", "btn btn-outline-secondary")), "container-fluid pt-3")));
    }

    public String makeNewUserPage (String failText) {
        String failMessage = "";
        if (failText.length() > 0) {
            failMessage = "<p class=\"text-warning\">" + failText + "</p>";
        }
        return makeHTMLTag(makeHead(addMeta("charset=\"UTF-8\"") + addMeta("http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"") + addMeta("name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"") + makeTitle("Pantry Landing Page") + linkStylesheet("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css") + linkJS("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js") + linkJS("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js")) + makeBody(makeDiv(makeForm(makeHeader(3, "Please fill the following fields") + failMessage + makeDiv(makeFormItem("Username:", "text", "form-control", "Enter Username", "username"), "form-group") + makeDiv(makeFormItem("Email:", "email", "form-control", "Enter Email Address", "email"), "form-group") + makeDiv(makeFormItem("Password:", "password", "form-control", "Enter Password", "pass"), "form-group") + makeButton("Submit", "submit", "btn btn-primary"), "Login", "POST"), "container-fluid")));
    }

    public String makeMenuPage (Connection connection, String username) {
        PantryDAO pantry = new PantryDAO(connection, username);
        return makeHTMLTag(makeHead(addMeta("charset=\"UTF-8\"") + addMeta("http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"") + addMeta("name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"") + makeTitle("Pantry Landing Page") + linkStylesheet("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css") + linkJS("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js") + linkJS("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"))
        + makeBody(makeDiv(makeHeader(2, "WebPantry"), "container")
            + ""
            + makePantryTable(pantry.getAll())));
    }

    private String makePantryTable(List<PantryItem> pantry) {
        Iterator<PantryItem> i = pantry.iterator();
        StringBuilder tableOutput = new StringBuilder("<table class=\"table table-hover\">");
        tableOutput.append("<thead><tr><th>Amount</th><th>Measure</th><th>Ingredient Name</th><th>Remove?</th></tr></thead><tbody>");
        while (i.hasNext()) {
            PantryItem temp = i.next();
            tableOutput.append("<tr><td>" + temp.amount + "</td><td>" + temp.getMeasure() + "</td><td>" + temp.ingredientName + "</td><td><input type=\"checkbox\" name=\"item" + temp.getItemIndex() + "\"></td></tr>");
        }
        tableOutput.append("<tr><td colspan=\"3\"></td><td colspan=\"1\">" + makeButton("Update", "submit", "btn btn-primary") + "</td></tr>");
        tableOutput.append("</tbody></table>");
        return tableOutput.toString();
    }

    private String makeHTMLTag (String inner) {
        return "<!DOCTYPE html><html lang=\"en\">" + inner + "</html>";
    }

    private String makeHead (String inner) {
        return "<head>" + inner + "</head>";
    }

    private String makeBody (String inner) {
        return "<body>" + inner + "</body>";
    }

    private String addMeta (String attributesString) {
        return "<meta " + attributesString + " />";
    }

    private String makeTitle (String inner) {
        return "<title>" + inner + "</title>";
    }

    private String linkStylesheet (String url) {
        return "<link rel=\"stylesheet\" href=\"" + url + "\">";
    }

    private String linkJS (String url) {
        return "<script src=\"" + url + "\"></script>";
    }

    private String makeHeader (int level, String inner) {
        return "<h" + level + ">" + inner + "</h" + level + ">";
    }

    private String makeDiv (String inner, String classValue) {
        return "<div class=\"" + classValue + "\">" + inner + "</div>";
    }

    private String makeForm (String inner, String action, String method) {
        return "<form action=\"" + action + "\" method=\"" + method + "\">" + inner + "</form>";
    }

    private String makeFormItem(String label, String typeValue, String classValue, String placeholder, String nameValue) {
        return "<label for=\"" + nameValue + "\">" + label + "</label><input type=\"" + typeValue + "\" class=\"" + classValue + "\" placeholder=\"" + placeholder + "\" name=\"" + nameValue + "\" required />";
    }

    private String makeButton (String inner, String typeValue, String classValue) {
        return "<button type=\"" + typeValue +  "\" class=\"" + classValue + "\">" + inner + "</button>";
    }

    private String makeParagraph (String inner) {
        return "<p>" + inner + "</p>";
    }

    private String makeLink (String inner, String url, String classValue) {
        return "<a class=\"" + classValue + "\" href=\"" + url + "\">" + inner + "</a>";
    }

}
