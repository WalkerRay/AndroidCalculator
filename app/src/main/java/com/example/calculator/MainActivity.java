package com.example.calculator;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;
import java.util.List;
import java.util.ArrayList;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //用于标记切换角度制弧度制的状态
    private int flag = 0;

    //标题及标签信息
    private TextView title, item_calculate, item_unit, item_binary, item_rate;
    //创建ViewPager
    private ViewPager vp ;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    String[] titles = new String[]{"计算器", "进制转换", "单位换算", "汇率转换"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        initViews();
        //去除工具栏
        getSupportActionBar().hide();

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
//        vp = (ViewPager) findViewById(R.id.mainViewPager);
        vp.setOffscreenPageLimit(4);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_calculate.setTextColor(Color.parseColor("#66CDAA"));

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                title.setText(titles[position]);
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    //onClick事件（点击底部Text动态修改ViewPager内容）
    @Override
    public  void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_calculate:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_binary:
                vp.setCurrentItem(1, true);
                break;
            case R.id.item_unit:
                vp.setCurrentItem(2, true);
                break;
            case R.id.item_rate:
                vp.setCurrentItem(3, true);
                break;
        }
    }


    //初始化布局View
    public void initViews(){
        //四个竖屏Fragment
        CalculateFragment cFragment;
        UnitFragment uFragment;
        RateFragment rFragment;
        BinaryFragment bFragment;

        System.out.println("doingInitPortView");
        title = (TextView) findViewById(R.id.title);
        item_calculate = (TextView) findViewById(R.id.item_calculate);
        item_binary = (TextView) findViewById(R.id.item_binary);
        item_unit = (TextView) findViewById(R.id.item_unit);
        item_rate = (TextView) findViewById(R.id.item_rate);

        item_calculate.setOnClickListener(this);
        item_binary.setOnClickListener(this);
        item_unit.setOnClickListener(this);
        item_rate.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.mainViewPager);
        cFragment = new CalculateFragment();
        uFragment = new UnitFragment();
        bFragment = new BinaryFragment();
        rFragment = new RateFragment();
        //给FragmentList添加数据
        mFragmentList.add(cFragment);
        mFragmentList.add(bFragment);
        mFragmentList.add(uFragment);
        mFragmentList.add(rFragment);


    }



    //ViewPager的适配器(ViewPager类需要一个PagerAdapter适配器类给它提供数据。这里面需要的数据是Fragment，所以是Fragment适配器)
    public class FragmentAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void changeTextColor(int position) {
        if (position == 0) {
            item_calculate.setTextColor(Color.parseColor("#66CDAA"));
            item_binary.setTextColor(Color.parseColor("#000000"));
            item_unit.setTextColor(Color.parseColor("#000000"));
            item_rate.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            item_binary.setTextColor(Color.parseColor("#66CDAA"));
            item_calculate.setTextColor(Color.parseColor("#000000"));
            item_unit.setTextColor(Color.parseColor("#000000"));
            item_rate.setTextColor(Color.parseColor("#000000"));
        }else if (position == 2){
            item_unit.setTextColor(Color.parseColor("#66CDAA"));
            item_calculate.setTextColor(Color.parseColor("#000000"));
            item_binary.setTextColor(Color.parseColor("#000000"));
            item_rate.setTextColor(Color.parseColor("#000000"));
        } else if (position == 3) {
            item_rate.setTextColor(Color.parseColor("#66CDAA"));
            item_calculate.setTextColor(Color.parseColor("#000000"));
            item_binary.setTextColor(Color.parseColor("#000000"));
            item_unit.setTextColor(Color.parseColor("#000000"));
        }
    }

}
