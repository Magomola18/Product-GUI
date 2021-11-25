package Product_GUI;

import java.io.Serializable;
public class Product implements Serializable{

    /**
     *
     */

    private int productID;
    private String productName;
    private double productPrice;

    //non-default constructors
    public Product() {

        productID = 0;
        productName = "";
        productPrice = 0.0;

    }

    public Product(int productID, String productName, double productPrice) {

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
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductID (int productID) {
        this.productID = productID;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }

    public void setProductPrice (double productPrice) {
        this.productPrice = productPrice;
    }

    public  String toString() {

        return "\nProduct ID: " +productID +
                "\nProduct Name: " +productName +
                "\nProduct Price: " +productPrice +"\n";
    }
}