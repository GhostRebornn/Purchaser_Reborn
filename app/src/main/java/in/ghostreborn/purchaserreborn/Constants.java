package in.ghostreborn.purchaserreborn;

import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.model.Products;

public class Constants {

    public static final String TABLE_NAME_USER = "USERS";
    public static final String TABLE_NAME_PRODUCT = "PRODUCT";

    public static final String TABLE_USER_ID = "USER_ID";
    public static final String TABLE_USER_NAME = "USER_NAME";
    public static final String TABLE_USER_PASS = "USER_PASS";
    public static final String TABLE_USER_SELLER ="USER_SELLER";
    public static final String TABLE_USER_ADMIN = "USER_ADMIN";

    public static final String TABLE_PRODUCT_ID = "PRODUCT_ID";
    public static final String TABLE_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String TABLE_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String TABLE_PRODUCT_PIC_ID = "PRODUCT_PIC_ID";

    public static ArrayList<String> users;
    public static ArrayList<Products> products;
    public static int productIndex = 0;
    public static boolean isAdmin = false;
    public static boolean isSeller = false;
    public static boolean isUser = false;

}
