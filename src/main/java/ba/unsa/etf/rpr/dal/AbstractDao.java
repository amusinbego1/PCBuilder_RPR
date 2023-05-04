package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.Idable;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = properties.getProperty("db.connection");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public abstract T rowToObject(ResultSet resultSet) throws PCBuilderException;
    public abstract Map<String, Object> objectToRow(T object);

    
}
