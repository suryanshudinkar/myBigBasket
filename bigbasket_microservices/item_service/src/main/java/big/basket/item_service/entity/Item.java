package big.basket.item_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Catalog")
public class Item {

    @Id
    @Column
    private int productID;
    @Column
    private String productName;
    @Column
    private int productPrice;

    public Item() {
    }

    public Item(int productID, String productName, int productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    @Override
    public String toString() {
        return "Student [Name=" + productName + ", ID=" + productID + ", Price=" + productPrice + "]";
    }
}
