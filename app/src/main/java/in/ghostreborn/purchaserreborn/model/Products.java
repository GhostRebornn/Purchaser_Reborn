package in.ghostreborn.purchaserreborn.model;

public class Products {

    String id;
    String name;
    String price;
    String pic_id;
    public Products(
            String id,
            String name,
            String price,
            String pic_id
    ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic_id = pic_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getPic_id() {
        return pic_id;
    }
}
