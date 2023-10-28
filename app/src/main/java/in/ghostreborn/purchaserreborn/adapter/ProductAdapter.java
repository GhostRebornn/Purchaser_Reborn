package in.ghostreborn.purchaserreborn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.model.Products;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Products product = Constants.products.get(position);
        holder.productListNameText.setText(product.getName());
        holder.productListPriceText.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return Constants.products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productListNameText;
        public TextView productListPriceText;

        public ViewHolder(View itemView) {
            super(itemView);
            productListNameText = itemView.findViewById(R.id.product_list_name_text);
            productListPriceText = itemView.findViewById(R.id.product_list_price_text);
        }
    }

}