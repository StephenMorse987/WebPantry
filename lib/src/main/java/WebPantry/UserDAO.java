package WebPantry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;

public class UserDAO implements DAO<User> {
    private Connection connection;

    public UserDAO (Connection connection) {
        this.connection = connection;
    }

    public boolean checkPass (String user, String pass) {
        try {
            PreparedStatement statement = connection.prepareStatement("select userpass from Users where username = ?;");
            statement.setString(1, user);
            ResultSet result = statement.executeQuery();
            if (result.next()) return pass.equals(result.getString("userpass"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> returnList = new LinkedList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Users");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User u = new User(result.getString("username"),result.getString("email") + "@" + result.getString("email_domain"),result.getString("userpass"));
                returnList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Problem encountered aquiring the User List.");
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    public void insert(User newUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Users (username,email,email_domain,userpass) values (?,?,?,?)");
            statement.setString(1, newUser.getusername());
            statement.setString(2, newUser.getemailname());
            statement.setString(3, newUser.getemaildomain());
            statement.setString(4, newUser.getPassword());
            if (statement.executeUpdate() < 1) throw new SQLException("The \"Users\" table did not accept the row you attempted to insert.");
            statement = connection.prepareStatement("CREATE TABLE " + newUser + "_Pantry (item_id SERIAL PRIMARY KEY, amount FLOAT4 NOT NULL, measure_index SMALLINT NOT NULL, item_name TEXT UNIQUE NOT NULL);");
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
        }
    }

    @Override
    public void update(User changedUser) {
        // Auto-generated method stub
    }

    @Override
    public void delete(User deletedUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from Users where username = ? and userpass = ?;");
            statement.setString(1, deletedUser.getusername());
            statement.setString(2, deletedUser.getPassword());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
