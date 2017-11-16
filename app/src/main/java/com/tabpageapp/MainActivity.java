package com.tabpageapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    ArrayList<Fragment> list;
//    public int miCountSet = 0,
//            miCountPlayerWin = 0,
//            miCountComWin = 0,
//            miCountDraw = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定 ViewPager 和 Pager Adapter。
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        list = new ArrayList<>();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mViewPager.getCurrentItem()
        initFragment();

        // 設定 ViewPager 給 TabLayout，就會顯示 tab pages。
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.smile);
        tabLayout.getTabAt(1).setIcon(R.drawable.safe);

        changeTextColor();
    }

    private void initFragment() {
        list.add(new GameFragment());
        list.add(new GameResultFragment());
    }

    public void changeIcon(boolean win, boolean lose, boolean draw) {
        if (win) {
            tabLayout.getTabAt(0).setIcon(R.drawable.smile);
        } else if (lose) {
            tabLayout.getTabAt(0).setIcon(R.drawable.qq);
        } else if (draw) {
            tabLayout.getTabAt(0).setIcon(R.drawable.safe);
        }
    }

    public void sendToResult(int miCountSet, int miCountPlayerWin, int miCountComWin, int miCountDraw) {
        ((GameResultFragment) list.get(1)).showResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
    }

    private void changeTextColor() {
        int colorVal, colorEnd, colorBack;
        colorBack = ((ColorDrawable) (tabLayout.getBackground())).getColor();
        colorVal = (colorBack & 0x00FF0000) >> 16;
        if (colorVal > 127) {
            colorEnd = 0;
        } else {
            colorEnd = 255;
        }
        ValueAnimator colorAnim = ObjectAnimator.ofInt(colorVal, colorEnd);
        colorAnim.setDuration(1000);
        colorAnim.setInterpolator(new LinearInterpolator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.start();
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int val = (Integer) animator.getAnimatedValue();
                tabLayout.setTabTextColors(ColorStateList.valueOf(0xFF808080 | val << 16));
            }
        });
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            // 根據目前tab標籤頁的位置，傳回對應的fragment物件
            switch (position) {
                case 0:
                    fragment = list.get(0);
                    break;
                case 1:
                    fragment = list.get(1);
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "電腦猜拳遊戲";
                case 1:
                    return "全部局數";
                default:
                    return null;
            }
        }
    }
}
