package com.example.wangdongfang.appdemo.ui.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.wangdongfang.appdemo.R;
import com.example.wangdongfang.appdemo.ui.recycler.decoration.ItemDecorationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerMainActivity extends AppCompatActivity {

    @BindView(R.id.decoration)
    Button decoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.decoration)
    public void onViewClicked() {
        startActivity(new Intent(this, ItemDecorationActivity.class));
    }
}
