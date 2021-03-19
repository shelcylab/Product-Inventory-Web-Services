package Services;

/**
 *
 * @author shelc
 */
public class Product {

    protected int id;
    protected String name;
    protected String location;
    protected int quantity;

    public Product(int id, String name, String location, int quantity) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.quantity = quantity;
    }

    public Product(int id, String location, int quantity) {
        super();
        this.id = id;
        this.location = location;
        this.quantity = quantity;
    }

    public Product(String name, String location, int quantity) {
        super();
        this.name = name;
        this.location = location;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
