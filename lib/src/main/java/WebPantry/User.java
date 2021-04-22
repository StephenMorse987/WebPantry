package WebPantry;

public class User {
    private String username;
    private String email;
    private String edomain;
    private String password;

    public User (String username, String email, String password) {
        this.username = username;
        String[] temp = email.split("@");
        this.email = temp[0];
        this.edomain = temp[1];
        this.password = password;
    }

    public String getusername () {
        return this.username;
    }

    public String getemail () {
        return this.email + "@" + this.edomain;
    }

    public String getemailname () {
        return this.email;
    }

    public String getemaildomain () {
        return this.edomain;
    }

    public String getPassword () {
        return this.password;
    }

    public boolean checkpassword (String other) {
        return this.password.equals(other);
    }
}
