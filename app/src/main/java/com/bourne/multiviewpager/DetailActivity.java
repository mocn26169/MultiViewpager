package com.bourne.multiviewpager;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    private ImageView img_frontCover;
    private ImageView img_background;
    private ImageView iv_tool_head;
    private TextView tv_content;
    private static final String OPTION_IMAGE = "image";
    private static final String ITEM_ID = "itemId";
    private ItemInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        toolbar.setTitle("详细页面");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));

        setSupportActionBar(toolbar);

        img_frontCover = (ImageView) findViewById(R.id.img_frontCover);
        iv_tool_head = (ImageView) findViewById(R.id.iv_tool_head);
        img_background = (ImageView) findViewById(R.id.img_background);
        tv_content = (TextView) findViewById(R.id.tv_content);

        // 获取数据
        info = (ItemInfo) getIntent().getExtras().get(ITEM_ID);

        // 根据数据设置图片
        img_frontCover.setBackgroundResource(info.imageRes);

        // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(img_frontCover, OPTION_IMAGE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            //使用api 23以上加入的方法
             setUpWindowTransition();
        }else {
            img_background.setAlpha(1f);
            tv_content.setAlpha(1f);
        }

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWindowTransition() {
        //进入的动画监听
        final ChangeBounds ts = new ChangeBounds();
        ts.setPathMotion(new ArcMotion());
        ts.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ts.removeListener(this);
                animateRevealShow(iv_tool_head);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setSharedElementEnterTransition(ts);

    }

    /**
     * toolbar的展开动画
     **/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow(View viewRoot) {

        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;

        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;

        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, finalRadius / 3, finalRadius);

        viewRoot.setVisibility(View.VISIBLE);

        anim.setDuration(50);

        anim.start();

        //开始前设置标题的背景
        iv_tool_head.setImageResource(R.color.white);

        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                //设置一个显示的动画
                img_background.animate().alpha(1).setStartDelay(50).start();
                tv_content.animate().alpha(1).setStartDelay(50).start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    /**
     * 打开本界面时的操作
     *
     * @param activity
     * @param transitionView
     * @param info
     */
    public static void StartOptionsActivity(AppCompatActivity activity, View transitionView, ItemInfo info) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(ITEM_ID, info);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());

    }

}
