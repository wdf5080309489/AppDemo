package com.example.wangdongfang.appdemo.ui.recycler.decoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangdongfang.appdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDecorationActivity extends AppCompatActivity {

    @BindView(R.id.rc)
    RecyclerView rc;

    private int orientation = RecyclerView.VERTICAL;
    private LayoutManagerType layoutManagerType = LayoutManagerType.LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);
        ButterKnife.bind(this);
        setLayoutManager();
        rc.addItemDecoration(new SpacingDecoration(60));
        rc.setAdapter(new SimpleRecyclerAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_decoration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mu_linear:
                layoutManagerType = LayoutManagerType.LinearLayout;
                break;
            case R.id.mu_grid:
                layoutManagerType = LayoutManagerType.GridLayout;
                break;
            case R.id.mu_stag:
                layoutManagerType = LayoutManagerType.StaggeredGridLayout;
                break;
            case R.id.mu_orientation:
                if (orientation == RecyclerView.HORIZONTAL) {
                    orientation = RecyclerView.VERTICAL;
                    item.setTitle("Vertical");
                } else {
                    orientation = RecyclerView.HORIZONTAL;
                    item.setTitle("Horizontal");
                }
                break;
        }
        setLayoutManager();
        return true;
    }

    private void setLayoutManager() {
        RecyclerView.LayoutManager layoutManager = null;
        switch (layoutManagerType) {
            case LinearLayout:
                layoutManager = new LinearLayoutManager(this, orientation, false);
                break;
            case GridLayout:
                layoutManager = new GridLayoutManager(this, 4, orientation, false);
                break;
            case StaggeredGridLayout:
                layoutManager = new StaggeredGridLayoutManager(4, orientation);
                break;
        }
        rc.setLayoutManager(layoutManager);
    }

    private class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_decoration, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(String.format("item-%d", position));
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
