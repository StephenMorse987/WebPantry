package WebPantry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void insert(PantryItem e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(PantryItem e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(PantryItem e) {
        // TODO Auto-generated method stub
        
    }
    
}
