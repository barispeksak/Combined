package com.example.combineddemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineddemo.home.ModelPost;
import com.example.combineddemo.home.PostAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ModelPost> dataList;
    PostAdapter adapter;
    ModelPost androidData;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public static HomeFragment newInstance(String param1,String param2)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataList = ModelPost.getPostData();
        adapter = new PostAdapter(this.getContext(),dataList);
        RecyclerView recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        LinearSnapHelper snapHelper = new LinearSnapOneItem();
        snapHelper.attachToRecyclerView(recyclerView);
    }
}
class LinearSnapOneItem extends LinearSnapHelper
{
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int X, int Y)
    {
        View snapView = findSnapView(layoutManager);
        int currentPosition = layoutManager.getPosition(snapView);
        LinearLayoutManager layoutManager1 = (LinearLayoutManager) layoutManager;
        int position1 = layoutManager1.findFirstVisibleItemPosition();
        int position2 = layoutManager1.findLastVisibleItemPosition();
        if(layoutManager.canScrollVertically())
        {
            if (X > 500) {
                currentPosition = position2;
            } else if (X < 500) {
                currentPosition = position1;
            }
        }
        return currentPosition;
    }
}