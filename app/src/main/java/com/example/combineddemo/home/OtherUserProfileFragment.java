package com.example.combineddemo.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.combineddemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherUserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherUserProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ModelUser user;
    ModelPost post;
    ImageView profilePhoto;
    TextView biography, rank, userName;
    Button addFriendButton, friendListButton, postsButton;
    ImageButton backButton;

    public OtherUserProfileFragment(ModelUser user) {
        super(R.layout.fragment_other_user_profile);
        this.user = user;
    }

    public String getUserName()
    {
        return user.getUserName();
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherUserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherUserProfileFragment newInstance(String param1, String param2) {
        OtherUserProfileFragment fragment = new OtherUserProfileFragment(null);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userName = view.findViewById(R.id.textViewUserName);
        profilePhoto = view.findViewById(R.id.rounded_profile_view);
        biography = view.findViewById(R.id.textViewBiography);
        rank = view.findViewById(R.id.textViewRank);
        addFriendButton = view.findViewById(R.id.buttonAddFriend);
        friendListButton = view.findViewById(R.id.buttonFriendList);
        postsButton = view.findViewById(R.id.buttonPosts);
        backButton = view.findViewById(R.id.backOtherUserProfileButton);

        userName.setText(this.user.getUserName());
        profilePhoto.setImageResource(this.user.getProfilePhoto());
        biography.setText(this.user.getBiography());
        rank.setText("Rank: " + this.user.getRank());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(OtherUserProfileFragment.this).commit();
            }
        });

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        friendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FriendListFragment(user), v);
            }
        });

        postsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new OtherUserPostsFragment(user), v);
            }
        });

    }

    public void replaceFragment(Fragment fragment, View v)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.other_user_layout, fragment);
        fragmentTransaction.commitNow();
    }
}