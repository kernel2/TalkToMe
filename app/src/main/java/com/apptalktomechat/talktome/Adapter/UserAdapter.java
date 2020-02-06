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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers){
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            User user = mUsers.get(position);
            holder.username.setText(user.getUsername());
            if ("default".equals(user.getImageURL())) {
                holder.profile_image.setImageResource(R.mipmap.ic_launcher);
            } else{
                Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
            }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView username;
        private ImageView profile_image;

        ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
        }

    }
}
