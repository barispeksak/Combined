package com.example.combineddemo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.HomeFragment;
import com.example.combineddemo.R;

import java.util.ArrayList;

public class OtherUserPostsAdapter extends RecyclerView.Adapter<OtherUserPostHolder> {

    private Context context;
    private ArrayList<ModelPost> dataList;
    HomeFragment homeFragment;


    public OtherUserPostsAdapter(Context context, ArrayList<ModelPost> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public OtherUserPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.otheruser_posts_item, parent, false);
        return new OtherUserPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherUserPostHolder holder, int position) {

        holder.postImage.setImageResource(dataList.get(position).getPostImage());
        holder.scoreAverage.setText("Score: " + dataList.get(position).getScore());

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class OtherUserPostHolder extends RecyclerView.ViewHolder{
    ImageView postImage;
    TextView scoreAverage;

    public OtherUserPostHolder(@NonNull View itemView) {
        super(itemView);
        postImage = itemView.findViewById(R.id.postImage);
        scoreAverage = itemView.findViewById(R.id.scoreAverageTextView);
    }
}
