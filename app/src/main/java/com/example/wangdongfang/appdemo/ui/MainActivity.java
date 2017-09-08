package com.example.wangdongfang.appdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangdongfang.appdemo.R;
import com.example.wangdongfang.appdemo.ui.inputmethod.InputMethodActivity;
import com.example.wangdongfang.appdemo.ui.lazyload.LazyloadActivity;
import com.example.wangdongfang.appdemo.ui.material.activitytransition.TransitionActivity;
import com.example.wangdongfang.appdemo.ui.recycler.RecyclerMainActivity;

public class MainActivity extends AppCompatActivity {

    private final static Class[] classData = new Class[] {
            InputMethodActivity.class,
            LazyloadActivity.class,
            TransitionActivity.class,
            RecyclerMainActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_activity_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActivityItemAdapter(this));
    }

    private static class ActivityItemAdapter extends RecyclerView.Adapter<ActivityItemAdapter.ViewHolder> {

        private final Context mContext;

        ActivityItemAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_activity, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Class activityClass = classData[position];
            holder.tvActivityName.setText(activityClass.getSimpleName());
            holder.itemView.setBackgroundColor(Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, activityClass));
                }
            });
        }

        @Override
        public int getItemCount() {
            return classData.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tvActivityName;

            ViewHolder(View itemView) {
                super(itemView);
                tvActivityName = (TextView) itemView.findViewById(R.id.tv_activity_name);
            }
        }
    }
}
