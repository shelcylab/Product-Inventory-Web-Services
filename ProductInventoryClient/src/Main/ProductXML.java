package Main;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shelc
 */
@XmlRootElement
public class ProductXML {

    protected int id;
    protected String name;
    protected String location;
    protected int quantity;

    public ProductXML(int id, String name, String location, int quantity) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.quantity = quantity;
    }

    ProductXML() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @XmlAttribute
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
