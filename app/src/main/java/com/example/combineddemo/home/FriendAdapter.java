package com.example.combineddemo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    ArrayList<ModelUser> users;
    Context context;

    public FriendAdapter(Context context, ArrayList<ModelUser> users)
    {
        this.users = users;
        this.context = context;
    }
    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item, parent, false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {

        holder.friendName.setText(users.get(position).getUserName());
        holder.profilePhoto.setImageResource(users.get(position).getProfilePhoto());
        holder.currentUser = users.get(position);


        holder.friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new OtherUserProfileFragment(holder.currentUser), v);
            }
        });

    }

    public void replaceFragment(OtherUserProfileFragment fragment, View v)
    {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.friend_list_layout, fragment, fragment.getTag());
        fragmentTransaction.commitNow();
    }

    @Override
    public int getItemCount() {return this.users.size();}
}

class FriendHolder extends RecyclerView.ViewHolder {

    CircleImageView profilePhoto;
    LinearLayout friendButton;
    TextView friendName;
    ModelUser currentUser;

    public FriendHolder(@NonNull View itemView) {
        super(itemView);

        profilePhoto = itemView.findViewById(R.id.friendImage);
        friendButton = itemView.findViewById(R.id.friendButton);
        friendName = itemView.findViewById(R.id.friendName);
    }
}
