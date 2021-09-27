package big.basket.order_service.entity;

import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_data")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int oID;


    private int uID;


    private String productlist;

    public Order(int uID, String productlist) {
        this.uID = uID;
        this.productlist = productlist;
    }

    public Order() {
    }

    public int getoID() {
        return oID;
    }

    public int getuID() {
        return uID;
    }

    public String getProductlist() {
        return productlist;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setProductlist(String productlist) {
        this.productlist = productlist;
    }
}
