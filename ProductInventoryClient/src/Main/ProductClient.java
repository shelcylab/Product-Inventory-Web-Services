package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import services.Product;
import services.ProductManagementServices;
import services.ProductServicesService;

/**
 *
 * @author shelc
 */
public class ProductClient {

    public static void main(String[] args) {
        try {

            ProductServicesService service = new ProductServicesService();
            ProductManagementServices port = service.getProductManagementServicesPort();
            List<Product> productList = port.getAllProducts();

            List<ProductXML> proList = new ArrayList<>();

            //Displaying the results in terminal
            for (Product list : productList) {
                System.out.println("ID:" + list.getId() + "\nName:" + list.getName()
                        + "\nLocation:" + list.getLocation()
                        + "\nQuantity:" + list.getQuantity());
                int id = list.getId();
                String name = list.getName();
                String location = list.getLocation();
                int quantity = list.getQuantity();

                proList.add(new ProductXML(id, name, location, quantity));

            }

            for (int i = 0; i < proList.size(); i++) {
                System.out.println("Name:" + proList.get(i).getName());
            }

            System.out.println("Marshalling");

            ProductXML productXML = new ProductXML();

            for (Product list : productList) {

                productXML.setId(list.getId());
                productXML.setName(list.getName());
                productXML.setLocation(list.getLocation());
                productXML.setQuantity(list.getQuantity());

            }


            JAXBContext jaxBContext = JAXBContext.newInstance(ProductXML.class);
            Marshaller marshaller = jaxBContext.createMarshaller();
            marshaller.marshal(productXML, new FileOutputStream("response.xml"));

            System.out.println("UnMarshalling");

            File file = new File("response.xml");
            JAXBContext jaxBContextM = JAXBContext.newInstance(ProductXML.class);
            Unmarshaller unmarshaller = jaxBContextM.createUnmarshaller();
            ProductXML productXML_UM = (ProductXML) unmarshaller.unmarshal(file);

            System.out.println("Product ID: " + productXML_UM.getId());
            System.out.println("Name: " + productXML_UM.getName());
            System.out.println("Location: " + productXML_UM.getLocation());
            System.out.println("Quantity: " + productXML_UM.getQuantity());

        } catch (Exception e) {
            System.out.println("Error message:" + e);
        }
    }

}
