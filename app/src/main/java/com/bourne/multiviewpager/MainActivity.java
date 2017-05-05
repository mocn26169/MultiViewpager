package com.bourne.multiviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        toolbar.setTitle("主页");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        setSupportActionBar(toolbar);

        final MultiViewPager pager = (MultiViewPager) findViewById(R.id.pager);

        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Fragment getItem(int position) {
                return PageFragment.create(position);
            }

        };
        pager.setAdapter(adapter);
    }
}
