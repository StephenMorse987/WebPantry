package WebPantry.Servlets;

public class PageBuilder {

    public String makeLoginPage (boolean alreadyFailed) {
        String failMessage = "";
        if (alreadyFailed) {
            failMessage = "<p class=\"text-warning\">The username and/or password you entered were not recognized.</p>";
        }
        return makeHTMLTag(makeHead(addMeta("charset=\"UTF-8\"") + addMeta("http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"") + addMeta("name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"") + makeTitle("Pantry Landing Page") + linkStylesheet("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css") + linkJS("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js") + linkJS("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js")) + makeBody(makeDiv(makeForm(makeHeader(3, "Please Login") + failMessage + makeDiv(makeFormItem("Username:", "text", "form-control", "Enter Username", "username"), "form-group") + makeDiv(makeFormItem("Password:", "password", "form-control", "Enter Password", "pass"), "form-group") + makeButton("Submit", "submit", "btn btn-primary"), "Login", "POST"), "container-fluid")));
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
        return "<meta " + attributesString + "";
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
        return "<label for=\"" + nameValue + "\">" + label + "</label><input type=\"" + typeValue + "\" class=\"" + classValue + "\" placeholder=\"" + placeholder + "\" name=\"" + nameValue + "\">";
    }

    private String makeButton (String inner, String typeValue, String classValue) {
        return "<button type=\"" + typeValue +  "\" class=\"" + classValue + "\">" + inner + "</button>";
    }

}
