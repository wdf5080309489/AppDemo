package com.example.wangdongfang.appdemo.ui.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangdongfang on 2017/8/29.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * fragment's view created sign
     * */
    private boolean isViewCreated;

    /**
     * fragment visible sign
     * */
    private boolean isFragmentVisible;

    private boolean isFirstLoad = true;

    private boolean isForceLoad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            initParams(args);
        }
    }

    protected abstract void initParams(Bundle args);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        View view = initView(inflater, container, savedInstanceState);
        lazyLoad();
        return view;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    private void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    private void onInVisible() {
        isFragmentVisible = false;
    }

    private void lazyLoad() {
        if (isViewCreated && isFragmentVisible) {
            if (isFirstLoad || isForceLoad) {
                isFirstLoad = false;
                isForceLoad = false;
                loadData();
            }
        }
    }

    protected abstract void loadData();

    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    public void setForceLoad(boolean forceLoad) {
        isForceLoad = forceLoad;
    }
}
