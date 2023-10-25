package in.ghostreborn.purchaserreborn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        String product = Constants.products.get(position);
        holder.productListText.setText(product);
    }

    @Override
    public int getItemCount() {
        return Constants.users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productListText;

        public ViewHolder(View itemView) {
            super(itemView);
            productListText = itemView.findViewById(R.id.product_list_text);
        }
    }

}