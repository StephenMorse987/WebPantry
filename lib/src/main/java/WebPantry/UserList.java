package WebPantry;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class UserList {
    private List<User> users;

    public UserList (Connection connection) {
        UserDAO userdao = new UserDAO(connection);
        this.users = userdao.getAll();
    }

    public boolean checkPass (String username, String password) {
        Iterator<User> i = this.users.listIterator();
        while (i.hasNext()) {
            User temp = i.next();
            if (temp.getusername().equals(username)) return temp.checkpassword(password);
        }
        return false;
    }

}
