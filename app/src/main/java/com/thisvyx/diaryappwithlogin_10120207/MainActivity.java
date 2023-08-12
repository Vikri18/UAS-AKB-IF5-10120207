package com.thisvyx.diaryappwithlogin_10120207;

/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.botnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                new ProfileFragment()).commit();


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.diary:
                        selectedFragment = new DiaryFragment();
                        break;

                    case R.id.logout:
                        selectedFragment = new LogoutFragment();
                        break;



                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                        selectedFragment).commit();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            Toast.makeText(MainActivity.this,"Silahkan Login terlebih dahulu",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}