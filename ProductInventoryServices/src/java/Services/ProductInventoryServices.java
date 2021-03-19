package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shelc
 */
public class ProductInventoryServices {

    String jdbcURL = "jdbc:derby://localhost:1527/sample;create=true;user=app;password=app";

    private static final String INSERT_PRODUCTS_SQL = "insert into Product_Inv" + " (name, location, quantity) VALUES "
            + " (?, ?, ?)";

    private static final String SELECT_PRODUCT_BY_ID = "select id,name,location,quantity from Product_Inv where id =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from Product_Inv";
    private static final String DELETE_PRODUCTS_SQL = "delete from Product_Inv where id = ?";
    private static final String UPDATE_PRODUCTS_SQL = "update Product_Inv set name = ?,location= ?, quantity =? where id = ?";

    public ProductInventoryServices() {
    }

    public List< Product> selectAllProducts() {

        List< Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, location, quantity));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return connection;
    }

    public void insertProduct(Product product) throws SQLException {

        System.out.println(INSERT_PRODUCTS_SQL);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getLocation());
            preparedStatement.setInt(3, product.getQuantity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Product selectProduct(int id) {
        Product product = null;

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                int quantity = rs.getInt("quantity");
                product = new Product(id, name, location, quantity);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getLocation());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
