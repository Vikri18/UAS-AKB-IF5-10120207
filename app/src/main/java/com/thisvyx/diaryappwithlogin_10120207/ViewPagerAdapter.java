package com.thisvyx.diaryappwithlogin_10120207;

/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_screen, container, false);

        ImageView logo = view.findViewById(R.id.logo);
        ImageView dot1 = view.findViewById(R.id.dot1);
        ImageView dot2 = view.findViewById(R.id.dot2);

        TextView judul = view.findViewById(R.id.judul);
        TextView deskripsi = view.findViewById(R.id.deskripsi);

        Button selesai = view.findViewById(R.id.btn_selesai);


        switch (position) {
            case 0:
                logo.setImageResource(R.drawable.welcome);
                dot1.setImageResource(R.drawable.dot_select);
                dot2.setImageResource(R.drawable.dot_unselect);

                deskripsi.setText("Geser layar untuk informasi aplikasi.");

                selesai.setVisibility(view.GONE);


                break;
            case 1:
                logo.setImageResource(R.drawable.gambar_viewpager);
                dot1.setImageResource(R.drawable.dot_unselect);
                dot2.setImageResource(R.drawable.dot_select);

                judul.setText("Diary App");
                deskripsi.setText("aplikasi yang bisa menyimpan catatan harian, melihat catatan, serta mengedit dan menghapus catatan harian kalian");

                selesai.setVisibility(view.VISIBLE);

                break;

        }

        container.addView(view);
        return view;
    }
}
