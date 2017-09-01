package com.example.wangdongfang.appdemo.ui.lazyload;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wangdongfang.appdemo.R;

public class LazyloadFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = "lazy";

    private String mParam1;

    private ProgressBar pbLoading;
    private TextView tvContent;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static LazyloadFragment newInstance(String param1) {
        LazyloadFragment fragment = new LazyloadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initParams(Bundle args) {
        mParam1 = args.getString(ARG_PARAM1);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lazyload, container, false);
        pbLoading = (ProgressBar) view.findViewById(R.id.pb_loading);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText(mParam1);
        return view;
    }

    @Override
    protected void loadData() {
        pbLoading.setVisibility(View.VISIBLE);
        tvContent.setVisibility(View.GONE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pbLoading.setVisibility(View.GONE);
                tvContent.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void refreshData() {
        if (isFragmentVisible()) {
            loadData();
        } else {
            setForceLoad(true);
        }
    }
}
