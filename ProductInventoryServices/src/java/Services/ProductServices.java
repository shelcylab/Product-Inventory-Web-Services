package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author shelc
 */
@WebService(name = "ProductManagementServices")
public class ProductServices {

    String jdbcURL = "jdbc:derby://localhost:1527/sample;create=true;user=app;password=app";

    @WebMethod(operationName = "GetAllProducts")
    @WebResult(name = "ProductList")
    public List< Product> selectAllProducts() {

        List<Product> product = new ArrayList<>();

        try {
            Connection conn = InitiateDBConnection();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("Select * from Product_Inv");

            while (rs.next()) {
                product.add(new Product(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Location"),
                        rs.getInt("Quantity")));
            }
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return product;

    }

    private Connection InitiateDBConnection() {

        Connection conn = null;
        try {
            String db_URL = "jdbc:derby://localhost:1527/sample;create=true;user=app;password=app";

            conn = DriverManager.getConnection(db_URL);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return conn;
    }

}