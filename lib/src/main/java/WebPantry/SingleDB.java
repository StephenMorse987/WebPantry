package WebPantry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleDB {
    private static SingleDB dbinstance = null;
    public Connection connection;

    private SingleDB () {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Add an output
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "p4ssw0rd");
        } catch (SQLException e) {
            // TODO Add an output
        }
    }

    public static SingleDB getInstance() {
        if (dbinstance == null) dbinstance = new SingleDB();
        return dbinstance;
    }
}
