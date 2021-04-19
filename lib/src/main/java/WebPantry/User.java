package WebPantry;

public class User {
    private String username;
    private String email;
    private String edomain;
    private String password;

    User (String username, String email, String password) {
        this.username = username;
        String[] temp = email.split("@");
        this.email = temp[0];
        this.edomain = temp[1];
        this.password = password;
    }

    String getusername () {
        return this.username;
    }

    String getemail () {
        return this.email + "@" + this.edomain;
    }

    String getemailname () {
        return this.email;
    }

    String getemaildomain () {
        return this.edomain;
    }

    String getPassword () {
        return this.password;
    }

    boolean checkpassword (String other) {
        return this.password.equals(other);
    }
}
