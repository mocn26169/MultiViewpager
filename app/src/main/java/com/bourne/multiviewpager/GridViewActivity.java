package com.bourne.multiviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private List<Integer> mDatas;
    private GridView mGridView;
    private ScrollView scrollView;
    private GridViewAdapter adapter;
    private int[] imgs = {
            R.drawable.img1
            , R.drawable.img2
            , R.drawable.img3
            , R.drawable.img4
            , R.drawable.img5
            , R.drawable.img6
            , R.drawable.img7
            , R.drawable.img8
            , R.drawable.img9
            , R.drawable.img10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("GridView");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        initMultiViewPager();
        initGridView();
    }

    private void initMultiViewPager() {
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

    private void initGridView() {
        mGridView = (GridView) findViewById(R.id.gridView);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        initDatas();
        adapter = new GridViewAdapter(GridViewActivity.this, mDatas);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    int index = (int) (Math.random() * imgs.length);
                    Log.e("index",index+"");
                    mDatas.add(imgs[index]);

                    adapter = new GridViewAdapter(GridViewActivity.this, mDatas);
                    mGridView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });

                } else {
                    ItemInfo info = new ItemInfo(mDatas.get(position), "");
                    DetailActivity.StartOptionsActivity(GridViewActivity.this, view, info);
                }

            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(R.drawable.img1);
        mDatas.add(R.drawable.img2);
        mDatas.add(R.drawable.img3);
        mDatas.add(R.drawable.img4);
        mDatas.add(R.drawable.img5);

    }
}
