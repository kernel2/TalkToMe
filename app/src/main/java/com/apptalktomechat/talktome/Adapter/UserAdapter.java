package com.apptalktomechat.talktome.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apptalktomechat.talktome.Model.User;
import com.apptalktomechat.talktome.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUser;

    public  UserAdapter(Context mContext, List<User> mUser){
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapter.ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUser.get(position);
        holder.username.setText(user.getUsername());
        if(user.getImageURL() == null){
            holder.profileImage.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(user.getImageURL()).into(holder.profileImage);
        }
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profileImage;

        public ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.textViewUsername);
            profileImage = itemView.findViewById(R.id.imageViewUser);
        }
    }
}
