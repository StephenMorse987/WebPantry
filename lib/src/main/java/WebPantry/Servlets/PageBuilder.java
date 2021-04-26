package WebPantry.Servlets;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import WebPantry.PantryDAO;
import WebPantry.PantryItem;

public class PageBuilder {

    public String makeLoginPage (boolean alreadyFailed) {
        String failMessage = "";
        if (alreadyFailed) failMessage = "<p class=\"text-warning\">The username and/or password you entered were not recognized.</p>";
        return makeHTMLTag(makeHeadCommon() + makeBody(makeDiv(makeHeader(3, "Please Login"), "container-fluid pt-3") + makeDiv(makeForm(failMessage + makeDiv(makeFormItem("Username:", "text", "form-control", "Enter Username", "username"), "form-group") + makeDiv(makeFormItem("Password:", "password", "form-control", "Enter Password", "pass"), "form-group") + makeButton("Submit", "submit", "btn btn-primary"), "Login", "POST"), "container-fluid pt-3") + makeDiv(makeParagraph(makeLink("I don't have a login", "/lib/NewUser", "btn btn-outline-secondary")), "container-fluid pt-3")));
    }

    public String makeNewUserPage () {
        return makeHTMLTag(makeHeadCommon()
        + makeBody(makeDiv(makeValidatedForm(
            makeHeader(3, "Please fill the following fields to create a profile")
            + makeDiv(makeLink("Return to Login", "/lib/Login", "btn btn-info"), "container-fluid pt-3")
            + makeDiv(makeFormItem("Username:", "text", "form-control", "Enter Username", "username"), "form-group")
            + makeDiv(makeFormItem("Email:", "email", "form-control", "Enter Email Address", "email"), "form-group")
            + makeDiv(makeFormItem("Password:", "password", "form-control", "Enter Password", "pass"), "form-group"), "NewUser", "POST")
            + makeOnClickButton("Submit", "btn btn-primary", "validateForm"), "container-fluid")
            + linkJS("uservalidation.js")));
    }

    public String makeMenuPage (Connection connection, String username) {
        PantryDAO pantry = new PantryDAO(connection, username);
        return makeHTMLTag(makeHeadCommon()
        + makeBody(makeDiv(makeHeader(2, "WebPantry"), "container pt-3")
            + makeDiv(makeLink("Add an Item", "/lib/AddItem", "btn btn-primary") + makeLink("Logout", "/lib/Logout", "btn btn-secondary"), "container pt-3")
            + makeDiv(makePantryTable(pantry.getAll()),"container pt-3")
            + linkJS("itemremoval.js")));
    }

    public String makeAddItemPage () {
        return makeHTMLTag(makeHeadCommon()
            + makeBody(
                makeDiv(makeHeader(2, "Add an Item to your Pantry"), "container-fluid pt-3")
                + makeDiv(makeLink("Return to Pantry", "/lib/Menu", "btn btn-info"), "container-fluid")
                + makeDiv(makeValidatedForm(makeTable(addTableRow(
                    addTableColumn(true, "Amount")
                    + addTableColumn(true, "Measure")
                    + addTableColumn(true, "Item Name")), addTableRow(
                    addTableColumn(false, makeInput("type=\"number\" step=\"0.1\" name=\"item1-amount\" id=\"amountfield\""))
                    + addTableColumn(false,
                        makeSelect(
                            addOption("-Measure-", "disabled selected value")
                            + addOption("ct", "value=\"1\"")
                            + addOption("dl", "value=\"2\"")
                            + addOption("L", "value=\"3\"")
                            + addOption("cc", "value=\"4\"")
                            + addOption("mL", "value=\"5\"")
                            + addOption("c", "value=\"6\"")
                            + addOption("cup", "value=\"7\"")
                            + addOption("dash", "value=\"8\"")
                            + addOption("fl oz", "value=\"9\"")
                            + addOption("gal", "value=\"10\"")
                            + addOption("pinch", "value=\"11\"")
                            + addOption("fl pt", "value=\"12\"")
                            + addOption("p", "value=\"13\"")
                            + addOption("pt", "value=\"14\"")
                            + addOption("fl qt", "value=\"15\"")
                            + addOption("q", "value=\"16\"")
                            + addOption("qt", "value=\"17\"")
                            + addOption("T", "value=\"18\"")
                            + addOption("tbl", "value=\"19\"")
                            + addOption("tbs", "value=\"20\"")
                            + addOption("tbsp", "value=\"21\"")
                            + addOption("t", "value=\"22\"")
                            + addOption("tsp", "value=\"23\"")
                            + addOption("g", "value=\"24\"")
                            + addOption("kg", "value=\"25\"")
                            + addOption("mg", "value=\"26\"")
                            + addOption("oz", "value=\"27\"")
                            + addOption("lb", "value=\"28\""),
                        "name=\"item1-measure\" id=\"measurefield\""))
                    + addTableColumn(false, makeInput("type=\"text\" name=\"item1-name\" id=\"namefield\""))), "table-bordered"), "/lib/AddItem", "POST"), "container-fluid pt-3")
                + makeDiv(makeOnClickButton("Add Item to Pantry", "btn btn-primary", "validateForm"), "container-fluid pt-3")
                + linkJSwithAsync("validation.js")
            ));
    }

    private String makePantryTable(List<PantryItem> pantry) {
        Iterator<PantryItem> i = pantry.iterator();
        StringBuilder tableOutput = new StringBuilder("<table class=\"table table-hover\">");
        tableOutput.append("<thead><tr><th>Amount</th><th>Measure</th><th>Ingredient Name</th><th></th></tr></thead><tbody>");
        int items = 0;
        while (i.hasNext()) {
            PantryItem temp = i.next();
            tableOutput.append("<tr><td>" + temp.amount + "</td><td>" + temp.getMeasure() + "</td><td>" + temp.ingredientName + "</td><td><button class=\"btn btn-warning\" type=\"button\" onClick=\"removeItem(" + temp.getItemIndex() + ")\">Remove</button></td></tr>");
            items++;
        }
        if (items == 0) {
            tableOutput.append("<tr><td colspan=\"3\">Your pantry has no items. To add items, click 'Add Items' in the Navigation Bar.</td><td></td></tr>");
        }
        tableOutput.append("</tbody></table>");
        return tableOutput.toString();
    }

    private String makeTable (String header, String inner, String classValue) {
        return "<table class=\"" + classValue + "\"><thead>" + header + "</thead><tbody>" + inner + "</tbody></table>";
    }

    private String addTableRow (String inner) {
        return "<tr>" + inner + "</tr>";
    }

    private String addTableColumn (boolean isHeader, String inner) {
        String cType;
        if (isHeader) cType = "h";
        else cType = "d";
        return "<t" + cType + ">" + inner + "";
    }

    private String makeHTMLTag (String inner) {
        return "<!DOCTYPE html><html lang=\"en\">" + inner + "</html>";
    }

    private String makeHead (String inner) {
        return "<head>" + inner + "</head>";
    }

    private String makeHeadCommon () {
        return makeHead(addMeta("charset=\"UTF-8\"") + addMeta("http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"") + addMeta("name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"") + makeTitle("Pantry Landing Page") + linkStylesheet("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css") + linkJS("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js") + linkJS("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"));
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

    private String linkJSwithAsync (String url) {
        return "<script src=\"" + url + "\" async defer></script>";
    }

    private String makeHeader (int level, String inner) {
        return "<h" + level + ">" + inner + "</h" + level + ">";
    }

    private String makeDiv (String inner, String classValue) {
        return "<div class=\"" + classValue + "\">" + inner + "</div>";
    }

    private String makeValidatedForm (String inner, String action, String method) {
        return "<form id=\"validated-form\" action=\"" + action + "\" method=\"" + method + "\">" + inner + "</form>";
    }

    private String makeForm (String inner, String action, String method) {
        return "<form action=\"" + action + "\" method=\"" + method + "\">" + inner + "</form>";
    }

    private String makeFormItem(String label, String typeValue, String classValue, String placeholder, String nameValue) {
        return "<label for=\"" + nameValue + "\">" + label + "</label>" + makeInput("type=\"" + typeValue + "\" class=\"" + classValue + "\" placeholder=\"" + placeholder + "\" id=\"" + nameValue + "\" name=\"" + nameValue + "\" required");
    }

    private String makeButton (String inner, String typeValue, String classValue) {
        return "<button type=\"" + typeValue +  "\" class=\"" + classValue + "\">" + inner + "</button>";
    }

    private String makeOnClickButton (String inner, String classValue, String jsFunction) {
        return "<button type=\"button\" class=\"" + classValue + "\" onclick=\"" + jsFunction + "()\">" + inner + "</button>";
    }

    private String makeParagraph (String inner) {
        return "<p>" + inner + "</p>";
    }

    private String makeLink (String inner, String url, String classValue) {
        return "<a class=\"" + classValue + "\" href=\"" + url + "\">" + inner + "</a>";
    }

    private String makeInput (String attributesString) {
        return "<input " + attributesString + " />";
    }

    private String makeSelect (String inner, String attributesString) {
        return "<select " + attributesString + ">" + inner + "</select>";
    }

    private String addOption (String inner, String attributesString) {
        return "<option " + attributesString + ">" + inner + "</option>";
    }

}
