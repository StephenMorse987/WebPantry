package WebPantry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PantryDAO implements DAO<PantryItem> {
    private Connection connection;
    private String username;

    public PantryDAO (Connection connection, String username) {
        this.connection = connection;
        this.username = username;
    }

    @Override
    public List<PantryItem> getAll() {
        List<PantryItem> returnList = new LinkedList<PantryItem>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + username + "_Pantry ORDER BY item_name ASC;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PantryItem newItem = new PantryItem(result.getInt("item_id"), result.getFloat("amount"), result.getInt("measure_index"), result.getString("item_name"));
                returnList.add(newItem);
            }
        } catch (SQLException e) {
            System.out.println("Problem encountered aquiring the " + username + "_Pantry table.");
            e.printStackTrace();
        }
        return returnList;
    }

    public List<String> getAllNames () {
        List<String> returnList = new ArrayList<String>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT item_name FROM " + username + "_Pantry;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                returnList.add(result.getString("item_name"));
            }
        } catch (SQLException e) {
            System.out.println("Problem encountered aquiring the " + username + "_Pantry table.");
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    public void insert(PantryItem item) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + username + "_Pantry (amount,measure_index,item_name) VALUES (?,?,?);");
            statement.setFloat(1, item.amount);
            statement.setInt(2, item.measureIndex);
            statement.setString(3, item.ingredientName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PantryItem item) {
        // No Reason to call this
    }

    @Override
    public void delete(PantryItem item) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + this.username + "_Pantry WHERE item_id = ?;");
            statement.setInt(1, item.getItemIndex());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
