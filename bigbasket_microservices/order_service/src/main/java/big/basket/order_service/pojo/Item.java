package big.basket.order_service.pojo;

public class Item {
    private int productID;
    private int productPrice;
    private String productName;

    public Item(int productID, int productPrice, String productName) {
        this.productID = productID;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public Item() {

    }

    public int getProductID() {
        return productID;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
