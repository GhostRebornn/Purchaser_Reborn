package in.ghostreborn.purchaserreborn.model;

import android.net.Uri;

public class Products {

    int id;
    String name;
    int price;
    Uri pic_id;
    boolean cartAdded;
    public Products(
            int id,
            String name,
            int price,
            Uri pic_id,
            boolean cartAdded
    ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic_id = pic_id;
        this.cartAdded = cartAdded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Uri getPic_id() {
        return pic_id;
    }

    public boolean isCartAdded() {
        return cartAdded;
    }

    public void setCartAdded(boolean cartAdded) {
        this.cartAdded = cartAdded;
    }
}
