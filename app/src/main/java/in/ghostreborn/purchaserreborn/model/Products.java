package in.ghostreborn.purchaserreborn.model;

public class Products {

    String name;
    String price;
    public Products(
            String name,
            String price
    ){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
