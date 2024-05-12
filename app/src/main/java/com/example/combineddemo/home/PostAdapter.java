package com.example.combineddemo.home;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.HomeFragment;
import com.example.combineddemo.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    private Context context;
    private ArrayList<ModelPost> dataList;
    HomeFragment homeFragment;


    public PostAdapter(ArrayList<ModelPost> posts, HomeFragment fragment)
    {
        this.homeFragment = fragment;
        this.dataList = posts;
    }

    public PostAdapter(Context context, ArrayList<ModelPost> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_posts, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.postImage.setImageResource(dataList.get(position).getPostImage());
        holder.userImage.setImageResource(dataList.get(position).getProfileImage());
        holder.userName.setText(dataList.get(position).getSharerName());
        holder.scoreAverage.setText(dataList.get(position).getScore());
        holder.currentPostSharer = dataList.get(position).getSharer();
        holder.currentPost = dataList.get(position);

        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                replaceFragment(new CommentFragment(holder.currentPost), v);
            }
        });

        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new OtherUserProfileFragment(holder.currentPostSharer), v);
            }
        });

        holder.scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v, holder.scoreRadioButtons, holder.scoreButtonLayout);
            }
        });

        holder.scoreRadioButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getCheckedRadioButtonId() != -1)
                {
                    holder.currentPost.removeScore(holder.lastPoint);
                }

                if(checkedId == R.id.imageButtonScore1)
                {
                    holder.currentPost.addScore(1);
                    holder.lastPoint = 1;
                }
                else if(checkedId == R.id.imageButtonScore2)
                {
                    holder.currentPost.addScore(2);
                    holder.lastPoint = 2;
                }
                else if(checkedId == R.id.imageButtonScore3)
                {
                    holder.currentPost.addScore(3);
                    holder.lastPoint = 3;
                }
                else if(checkedId == R.id.imageButtonScore4)
                {
                    holder.currentPost.addScore(4);
                    holder.lastPoint = 4;
                }
                else if(checkedId == R.id.imageButtonScore5)
                {
                    holder.currentPost.addScore(5);
                    holder.lastPoint = 5;
                }
                PostAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void replaceFragment(Fragment fragment, View v)
    {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.home, fragment);
        fragmentTransaction.commitNow();
    }

    public void expand(View view, RadioGroup buttons , LinearLayout layout) {
        if(buttons.getVisibility() == View.VISIBLE)
        {
            buttons.setVisibility(View.GONE);
        }
        else //View.GONE
        {
            buttons.setVisibility(View.VISIBLE);
        }
        TransitionManager.beginDelayedTransition(layout);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
class PostHolder extends RecyclerView.ViewHolder{
    ImageView postImage;
    CircleImageView userImage;
    Button userName;
    ImageButton commentButton, scoreButton;
    TextView scoreAverage;
    ModelUser currentPostSharer;
    ModelPost currentPost;
    LinearLayout scoreButtonLayout;

    RadioGroup scoreRadioButtons;
    RadioButton score1, score2, score3, score4, score5;
    int lastPoint;

    public PostHolder(@NonNull View itemView) {
        super(itemView);
        postImage = itemView.findViewById(R.id.postImage);
        userImage = itemView.findViewById(R.id.circleImageViewUser);
        userName = itemView.findViewById(R.id.buttonUserName);
        commentButton = itemView.findViewById(R.id.imageButtonComment);
        scoreButton = itemView.findViewById(R.id.imageButtonScore);
        scoreAverage = itemView.findViewById(R.id.textViewScoreAverage);
        scoreRadioButtons = itemView.findViewById(R.id.scoreRadioGroup);
        scoreButtonLayout = itemView.findViewById(R.id.radioButtonLayout);
        lastPoint = 0;
        score1 = itemView.findViewById(R.id.imageButtonScore1);
        score2 = itemView.findViewById(R.id.imageButtonScore2);
        score3 = itemView.findViewById(R.id.imageButtonScore3);
        score4 = itemView.findViewById(R.id.imageButtonScore4);
        score5 = itemView.findViewById(R.id.imageButtonScore5);

    }
}
