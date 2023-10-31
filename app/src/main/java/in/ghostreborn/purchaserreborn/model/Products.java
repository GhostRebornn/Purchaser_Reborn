package in.ghostreborn.purchaserreborn.model;

import android.net.Uri;

public class Products {

    int id;
    String name;
    int price;
    Uri pic_id;
    public Products(
            int id,
            String name,
            int price,
            Uri pic_id
    ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic_id = pic_id;
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
}
