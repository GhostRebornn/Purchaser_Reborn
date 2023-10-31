package in.ghostreborn.purchaserreborn.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.model.Products;
import in.ghostreborn.purchaserreborn.ui.BuyActivity;
import in.ghostreborn.purchaserreborn.ui.ProductEditActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    boolean editable;
    ArrayList<Products> products;
    public ProductAdapter(Context context, ArrayList<Products> products, boolean editable){
        this.context = context;
        this.products = products;
        this.editable = editable;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Products product = products.get(holder.getAdapterPosition());
        holder.productListNameText.setText(product.getName());
        String price = product.getPrice() + "₹";
        holder.productListPriceText.setText(price);

        Uri uri = product.getPic_id();
        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(holder.itemView.getContext().getContentResolver(), uri);
            holder.productListImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!product.isCartAdded()){
            holder.productListCartAddImage.setImageResource(R.drawable.cart_add);
        }else {
            holder.productListCartAddImage.setImageResource(R.drawable.cart_remove);
        }

        holder.productListCartAddImage.setOnClickListener(v -> {
            if (!product.isCartAdded()){
                if (!Constants.cart.contains(product)){
                    Constants.cart.add(product);
                    holder.productListCartAddImage.setImageResource(R.drawable.cart_remove);
                    product.setCartAdded(true);
                }
            }else {
                if (Constants.cart.contains(product)){
                    Constants.cart.remove(product);
                    holder.productListCartAddImage.setImageResource(R.drawable.cart_add);
                    product.setCartAdded(false);
                }
            }
        });

        if (editable){
            holder.productListEditImage.setVisibility(View.VISIBLE);
            holder.productListEditImage.setOnClickListener(v -> {
                Constants.productIndex = holder.getAdapterPosition();
                context.startActivity(new Intent(context, ProductEditActivity.class));
            });
        }

        holder.productConstraint.setOnClickListener(v -> {
            Constants.productIndex = holder.getAdapterPosition();
            context.startActivity(new Intent(context, BuyActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productListNameText;
        public TextView productListPriceText;
        public ImageView productListImageView;
        public ImageView productListEditImage;
        public ImageView productListCartAddImage;
        public ConstraintLayout productConstraint;

        public ViewHolder(View itemView) {
            super(itemView);
            productListNameText = itemView.findViewById(R.id.product_list_name_text);
            productListPriceText = itemView.findViewById(R.id.product_list_price_text);
            productListImageView = itemView.findViewById(R.id.product_list_image);
            productListEditImage = itemView.findViewById(R.id.product_list_edit);
            productConstraint = itemView.findViewById(R.id.product_constraint);
            productListCartAddImage = itemView.findViewById(R.id.cart_add_image_view);
        }
    }

}