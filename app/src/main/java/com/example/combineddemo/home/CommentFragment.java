package com.example.combineddemo.home;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.R;


import java.util.ArrayList;

public class CommentFragment extends Fragment {

    ModelPost post;
    ModelUser postOwner;
    EditText typeText;
    ImageButton sendTextButton;

    RecyclerView descriptionRecyclerView;
    RecyclerView advisorRecyclerView ;
    RecyclerView friendRecyclerView ;
    RecyclerView regularRecyclerView;

    CardView descriptionCard, advisorCard, friendCard, regularCard;
    private  ArrayList<ModelComment> advisorComment, friendComment, regularComment, description;


    ArrayList<ModelComment> dataList;
    CommentAdapter descriptionAdapter, advisorCommentAdapter, friendCommentAdapter, regularCommentAdapter;
    ModelPost androidData;
    private String param1;
    private String param2;

    public CommentFragment(ModelPost post) {
        this.post = post;
        this.postOwner = post.getSharer();
        this.dataList = post.getPostComment();
        this.description = new ArrayList<>();
        this.advisorComment = new ArrayList<>();
        this.friendComment = new ArrayList<>();
        this.regularComment = new ArrayList<>();
        fillSubCommentArrays();

    }

    private void fillSubCommentArrays()
    {
        for(int i = 0; i < dataList.size(); i++)
        {
            if(dataList.get(i).getIsDesciption())
            {
                this.description.add(dataList.get(i));
            }
            else if(dataList.get(i).getIsAdvisorComment())
            {
                this.advisorComment.add(dataList.get(i));
            }
            else if(dataList.get(i).getIsFriendComment())
            {
                this.friendComment.add(dataList.get(i));
            }
            else
            {
                this.regularComment.add(dataList.get(i));
            }
        }
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString("param1");
            param2 = getArguments().getString("param2");
        }
    }

    public static CommentFragment newInstance(String param1, String param2)
    {
        CommentFragment fragment = new CommentFragment(null);
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        descriptionAdapter = new CommentAdapter(description);
        advisorCommentAdapter = new CommentAdapter(advisorComment);
        friendCommentAdapter = new CommentAdapter(friendComment);
        regularCommentAdapter = new CommentAdapter(regularComment);

        descriptionRecyclerView = view.findViewById(R.id.descriptionCommentrecyclerview);
        advisorRecyclerView = view.findViewById(R.id.advisorCommentrecyclerview);
        friendRecyclerView = view.findViewById(R.id.friendCommentrecyclerview);
        regularRecyclerView = view.findViewById(R.id.regularCommentrecyclerview);

        descriptionCard = view.findViewById(R.id.descriptionCard);
        advisorCard = view.findViewById(R.id.advisorCard);
        friendCard = view.findViewById(R.id.friendCard);
        regularCard = view.findViewById(R.id.regularCard);

        EditText typeText = view.findViewById(R.id.messaget);
        ImageButton backButton = view.findViewById(R.id.backButton);
        ImageButton sendTextButton = view.findViewById(R.id.sendmsg);

        descriptionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        descriptionRecyclerView.setAdapter(descriptionAdapter);
        advisorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        advisorRecyclerView.setAdapter(advisorCommentAdapter);
        friendRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        friendRecyclerView.setAdapter(friendCommentAdapter);
        regularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        regularRecyclerView.setAdapter(regularCommentAdapter);

        descriptionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v, descriptionRecyclerView, descriptionCard);
            }
        });

        advisorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v, advisorRecyclerView, advisorCard);
            }
        });

        friendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v, friendRecyclerView, friendCard);
            }
        });

        regularCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v, regularRecyclerView, regularCard);
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(CommentFragment.this).commit();
            }
        });

        sendTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = typeText.getText().toString();
            }
        });

    }

    public void expand(View view, RecyclerView recyclerView, CardView cardView) {
        if(recyclerView.getVisibility() == View.VISIBLE)
        {
            recyclerView.setVisibility(View.GONE);
        }
        else //View.GONE
        {
            recyclerView.setVisibility(View.VISIBLE);
        }
        TransitionManager.beginDelayedTransition(cardView);
    }
}
