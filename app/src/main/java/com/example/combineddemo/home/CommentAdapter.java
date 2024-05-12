package com.example.combineddemo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {

    private Context context;
    private ArrayList<ModelComment> dataList;

    public CommentAdapter(ArrayList<ModelComment> comments)
    {
        this.dataList = comments;
    }

    public CommentAdapter(Context context, ArrayList<ModelComment> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.sharerImage.setImageResource(dataList.get(position).getSharer().getProfilePhoto());
        holder.sharerName.setText(dataList.get(position).getSharerName());
        holder.postText.setText(dataList.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class CommentHolder extends RecyclerView.ViewHolder{
    CircleImageView sharerImage;
    TextView sharerName, postText;
    public CommentHolder(@NonNull View itemView) {
        super(itemView);
        sharerImage = itemView.findViewById(R.id.sharerImage);
        sharerName = itemView.findViewById(R.id.sharerName);
        postText = itemView.findViewById(R.id.commenttext);
    }
}
