package com.example.combineddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.combineddemo.databinding.ActivityMainBinding;
import com.example.combineddemo.databinding.ActivityMainPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {


    ActivityMainPageBinding binding;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame_layout);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemID = item.getItemId();

                if(itemID == R.id.home) {
                    replaceFragment(new HomeFragment(), false);
                } else if (itemID == R.id.add) {
                    replaceFragment(new AddPhotoFragment(), false);
                } else if(itemID == R.id.profile) {
                    replaceFragment(new ProfieFragment(), false);
                } else {
                    replaceFragment(new RankingFragment(), false);
                }

                return true;
            }
        });



    }


    private void replaceFragment(Fragment fragment, boolean isAppInitialized)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isAppInitialized)
        {
            fragmentTransaction.add(R.id.frame_layout, fragment);
        }
        else
        {
            fragmentTransaction.replace(R.id.frame_layout,fragment);
        }
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}