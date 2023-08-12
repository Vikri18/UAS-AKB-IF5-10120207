package com.thisvyx.diaryappwithlogin_10120207;
/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewPagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewPager viewPager;
        ViewPagerAdapter viewPagerAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

    }

    public void onClick(View view){
        Intent intent = new Intent(ViewPagerActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}