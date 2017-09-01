package com.example.wangdongfang.appdemo.ui.lazyload;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wangdongfang.appdemo.R;

import java.util.ArrayList;
import java.util.List;

public class LazyloadActivity extends AppCompatActivity {

    private List<LazyloadFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazyload);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_title);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_lazy_list);

        mFragments.add(LazyloadFragment.newInstance("Page 1"));
        mFragments.add(LazyloadFragment.newInstance("Page 2"));
        mFragments.add(LazyloadFragment.newInstance("Page 3"));
        viewPager.setAdapter(new LazyFragmentAdapter<>(getSupportFragmentManager(), mFragments));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lazy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                for (LazyloadFragment fragment : mFragments) {
                    fragment.refreshData();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class LazyFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {

        private final List<T> mData;

        LazyFragmentAdapter(FragmentManager fm, List<T> fragments) {
            super(fm);
            this.mData = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }
}
