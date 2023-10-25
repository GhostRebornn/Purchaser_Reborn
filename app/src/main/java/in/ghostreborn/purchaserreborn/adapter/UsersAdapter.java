package in.ghostreborn.purchaserreborn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {

        String user = Constants.users.get(position);
        holder.usersListText.setText(user);
    }

    @Override
    public int getItemCount() {
        return Constants.users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usersListText;

        public ViewHolder(View itemView) {
            super(itemView);
            usersListText = itemView.findViewById(R.id.user_list_text);
        }
    }

}