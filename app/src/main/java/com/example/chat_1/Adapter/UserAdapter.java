package com.example.chat_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_1.MessageActivity;
import com.example.chat_1.Model.User;
import com.example.chat_1.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUsers;
    private Boolean ischat;

    public UserAdapter(Context mContext,List<User> mUsers,Boolean ischat){
        this.mUsers=mUsers;
        this.mContext=mContext;
        this.ischat=ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user=mUsers.get(position);

        holder.username.setText(user.getUserName());
        holder.profileImage.setImageResource(R.drawable.user);

        if(ischat){
            if(user.getStatus().equals("online")){
                holder.statusOn.setVisibility(View.VISIBLE);
            }
            else {
                holder.statusOn.setVisibility(View.GONE);
            }
        }
        else{
            holder.statusOn.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("userID",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profileImage;
        private ImageView statusOn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            profileImage=itemView.findViewById(R.id.profile_image);
            statusOn=itemView.findViewById(R.id.status);
        }
    }
}
