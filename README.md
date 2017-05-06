# MultiViewpager

博客地址http://blog.csdn.net/iromkoear/article/details/71279454

效果图

![这里写图片描述](http://img.blog.csdn.net/20170506185558460?aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaXJvbWtvZWFy/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


####**GitHub地址：https://github.com/mocn26169/MultiViewpager**

关键代码：

**1、Toolbar**
```
    <android.support.v7.widget.Toolbar
        android:id="@+id/id_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorPrimary"/>
```

```
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("GridView");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
```

**2、点击时让scrollView滑到最底端**
```
   new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
```

**3、GridVIew兼容ScrollView，使用自定义的MyGridView**

```
package com.bourne.multiviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView{

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    public MyGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}

```

**4、根据版本判断是否使用动画**
```
  //根据版本判断是否使用动画
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
             setUpWindowTransition();
        }else {
            img_background.setAlpha(1f);
            tv_content.setAlpha(1f);
        }
```

