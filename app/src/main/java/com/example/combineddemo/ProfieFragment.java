package com.example.combineddemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfieFragment extends Fragment {

    TextView biography, userName;
    Button friendListButton, oldPostsButton, changePasswordButton, editProfileButton, logoutButton;
    ImageView profilePhoto;

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore firebaseFirestore;
    public ProfieFragment() {
        // Required empty public constructor
    }


    public static ProfieFragment newInstance(String param1, String param2) {
        ProfieFragment fragment = new ProfieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profie, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        // getting current user data
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilePhoto = view.findViewById(R.id.rounded_image_view);
        userName = view.findViewById(R.id.textViewUserName);
        biography = view.findViewById(R.id.textViewBiography);
        friendListButton = view.findViewById(R.id.buttonFriendList);
        oldPostsButton = view.findViewById(R.id.buttonMyPosts);
        editProfileButton = view.findViewById(R.id.buttonEditProfile);
        changePasswordButton = view.findViewById(R.id.buttonChangePassword);
        logoutButton = view.findViewById(R.id.buttonLogOut);

        if (firebaseUser != null) {
            DocumentReference docRef = firebaseFirestore.collection("users").document(firebaseUser.getUid());
            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name = document.getString("username");
                        userName.setText(name);
                    } else {
                        userName.setText("User");
                    }
                } else {
                    userName.setText("User");
                }
            });
        } else {
            userName.setText("User");
        }
        friendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oldPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new EditProfileFragment(), view);
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ChangePasswordFragment(), view);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(ProfieFragment.this.getContext(), signIn.class);
                startActivity(intent);
            }
        });
    }

    public void replaceFragment(Fragment fragment, View v)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_layout, fragment);
        fragmentTransaction.commitNow();
    }
}