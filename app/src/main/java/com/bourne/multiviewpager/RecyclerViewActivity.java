package com.bourne.multiviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerGridViewAdapter recyclerGridViewAdapter;
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
            , R.mipmap.add_picture
    };
    private List<Integer> mDatas =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        for (int i = 0; i < imgs.length; i++) {
            mDatas.add(imgs[i]);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("RecyclerView");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        initMultiViewPager();
        initRecyclerView();
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

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.fragment_recyclerview);

        GridLayoutManager mgr = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mgr);
//        int spanCount = 4;//跟布局里面的spanCount属性是一致的
//        int spacing = 2;//每一个矩形的间距
//        boolean includeEdge = false;//如果设置成false那边缘地带就没有间距s
//        //设置每个item间距
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        //设置适配器
        recyclerGridViewAdapter = new RecyclerGridViewAdapter(RecyclerViewActivity.this, mDatas);
        recyclerView.setAdapter(recyclerGridViewAdapter);
        onRecyclerItemClickListener();
    }

    private void onRecyclerItemClickListener() {
        recyclerGridViewAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (position == (mDatas.size()-1)) {

                    int index = (int) (Math.random() * imgs.length);
                    Log.e("index",index+"");
                    mDatas.add(mDatas.size()-1,imgs[index]);

                    recyclerGridViewAdapter = new RecyclerGridViewAdapter(RecyclerViewActivity.this, mDatas);
                    recyclerView.setAdapter(recyclerGridViewAdapter);
                    recyclerGridViewAdapter.notifyDataSetChanged();

                }else {
                    ItemInfo info = new ItemInfo(mDatas.get(position), "");
                    DetailActivity.StartOptionsActivity(RecyclerViewActivity.this, view, info);
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "onLongClick:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
