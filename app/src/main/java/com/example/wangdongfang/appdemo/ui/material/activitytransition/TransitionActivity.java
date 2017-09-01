package com.example.wangdongfang.appdemo.ui.material.activitytransition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.wangdongfang.appdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionActivity extends AppCompatActivity {

    @BindView(R.id.rv_transition)
    RecyclerView rvTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);
    }
}
