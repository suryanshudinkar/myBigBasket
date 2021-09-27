package big.basket.config.entity;

public class Order {

	private int oID;
	private int uID;
	private String productlist;
	
	public Order() {}
	
	public Order(int oID, int uID, String productlist) {
		super();
		this.oID = oID;
		this.uID = uID;
		this.productlist = productlist;
	}

	public int getoID() {
		return oID;
	}
	public void setoID(int oID) {
		this.oID = oID;
	}
	public int getuID() {
		return uID;
	}
	public void setuID(int uID) {
		this.uID = uID;
	}
	public String getProductlist() {
		return productlist;
	}
	public void setProductlist(String productlist) {
		this.productlist = productlist;
	}
	
	
}
