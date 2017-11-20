package com.tabpageapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Path;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;
    ArrayList<Fragment> list;
    private int rgbNum = 1, rgbChange = 1, num;
    private TextView game, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定 ViewPager 和 Pager Adapter。
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        game = (TextView) findViewById(R.id.tv_title_game);
        result = (TextView) findViewById(R.id.tv_title_result);

        list = new ArrayList<>();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    clearColor();
                    num = mViewPager.getCurrentItem();
                    changeCurrent(num);
                }else {
                    clearColor();
                    num = mViewPager.getCurrentItem();
                    changeCurrent(num);
                }
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initFragment();

        // 設定 ViewPager 給 TabLayout，就會顯示 tab pages。
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setCustomView(game);
        tabLayout.getTabAt(1).setCustomView(result);
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
        ValueAnimator colorAnim = ObjectAnimator.ofInt(0, 255);
        colorAnim.setDuration(Integer.MAX_VALUE);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                switch (num) {
                    case 0:
                        switch (rgbChange) {
                            case 1:
                                if (rgbNum >= 255) {
                                    rgbChange = 2;
                                    rgbNum = 0;
                                }
                                game.setTextColor(Color.rgb(rgbNum, 0, 0));
                                rgbNum += 15;
                                break;
                            case 2:
                                if (rgbNum >= 255) {
                                    rgbChange = 3;
                                    rgbNum = 0;
                                }
                                game.setTextColor(Color.rgb(0, rgbNum, 0));
                                rgbNum += 15;
                                break;
                            case 3:
                                if (rgbNum >= 255) {
                                    rgbChange = 1;
                                    rgbNum = 0;
                                }
                                game.setTextColor(Color.rgb(0, 0, rgbNum));
                                rgbNum += 15;
                                break;
                        }
                        break;
                    case 1:
                        switch (rgbChange) {
                            case 1:
                                if (rgbNum >= 255) {
                                    rgbChange = 2;
                                    rgbNum = 1;
                                }
                                result.setTextColor(Color.rgb(rgbNum, 0, 0));
                                rgbNum += 15;
                                break;
                            case 2:
                                if (rgbNum >= 255) {
                                    rgbChange = 3;
                                    rgbNum = 1;
                                }
                                result.setTextColor(Color.rgb(0, rgbNum, 0));
                                rgbNum += 15;
                                break;
                            case 3:
                                if (rgbNum >= 255) {
                                    rgbChange = 1;
                                    rgbNum = 1;
                                }
                                result.setTextColor(Color.rgb(0, 0, rgbNum));
                                rgbNum += 15;
                                break;
                        }
                        break;
                }
            }
        });
        colorAnim.start();
    }

    private void clearColor(){
        game.setTextColor(Color.BLACK);
        result.setTextColor(Color.BLACK);
    }

    private void changeCurrent(int position){
        switch (position){
            case 0:
                game.setTextColor(Color.BLACK);
                mViewPager.setCurrentItem(0);
                break;
            case 1:
                result.setTextColor(Color.BLACK);
                mViewPager.setCurrentItem(1);
                break;
        }
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

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "電腦猜拳遊戲";
//                case 1:
//                    return "全部局數";
//                default:
//                    return null;
//            }
//        }
    }
}
