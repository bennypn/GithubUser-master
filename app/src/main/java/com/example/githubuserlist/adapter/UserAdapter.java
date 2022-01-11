package com.example.githubuserlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.githubuserlist.R;
import com.example.githubuserlist.UserProfileActivity;
import com.example.githubuserlist.model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    public static final String DATA_USER = "datauser";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<UserModel> data = new ArrayList<>();

    public UserAdapter(Context context, List<UserModel> data){
        this.context = context;
        this.data = data;
    }

    //Connect to Layout Item
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //introducing to the item on recyclerview
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(itemview);
    }

    //Set Data
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tvUsername.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(data.get(position)));
                intent.putExtra(DATA_EXTRA, bundle);
                context.startActivity(intent);
            }
        });
    }


    //Data Count
    @Override
    public int getItemCount(){
        //to show it as same number as total account on search
        return data.size();
    }

    //Initialize Item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivAvatar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_user);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
