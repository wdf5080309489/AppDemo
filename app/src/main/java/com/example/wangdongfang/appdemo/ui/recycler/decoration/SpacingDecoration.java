package com.example.wangdongfang.appdemo.ui.recycler.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by wangdongfang on 2017/9/8.
 */

public class SpacingDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = SpacingDecoration.class.getSimpleName();

    private static final int VERTICAL = OrientationHelper.VERTICAL;

    private int spacing;
    private boolean isHeaderDividerEnable = false;
    private boolean isFooterDividerEnable = true;

    public SpacingDecoration(int spacing) {
        this.spacing = spacing;
    }

    /**
     * 可以通过outRect.set()为每个Item设置一定的偏移量，主要用于绘制Decorator
     */
    @Override
    public void getItemOffsets(Rect outRect, final View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        if (layoutManager.getClass().equals(LinearLayoutManager.class)) {
            LinearLayoutManager llm = (LinearLayoutManager) layoutManager;
            if (llm.getOrientation() == LinearLayoutManager.VERTICAL) {
                if (isHeaderDividerEnable && position == 0) {
                    outRect.top = spacing;
                }
                if (isFooterDividerEnable || position != itemCount - 1) {
                    outRect.bottom = spacing;
                }
            } else {
                if (isHeaderDividerEnable && position == 0) {
                    outRect.left = spacing;
                }
                if (isFooterDividerEnable || position != itemCount - 1) {
                    outRect.right = spacing;
                }
            }
        } else if (layoutManager.getClass().equals(GridLayoutManager.class)) {
            GridLayoutManager glm = (GridLayoutManager) layoutManager;
            GridLayoutManager.SpanSizeLookup ssl = glm.getSpanSizeLookup();
            int spanCount = glm.getSpanCount();
            // 是否最后一列
            if (ssl.getSpanIndex(position + 1, spanCount) != 0) {
                outRect.right = spacing;
            }
            // 是否最后一行
            if (!isLastRow(itemCount, spanCount, position, ssl)) {
                outRect.bottom = spacing;
            }
        } else if (layoutManager.getClass().equals(StaggeredGridLayoutManager.class)) {
            outRect.set(0, 0, spacing, spacing);
        }


//        int orientation = getOrientation(parent);
//        int spanCount = getTotalSpan(parent);
//
//        int childCount = state.getItemCount();
//        int childIndex = parent.getChildAdapterPosition(view);
//
//        int itemSpanSize = getItemSpanSize(parent, childIndex);
//        int spanIndex = getItemSpanIndex(parent, childIndex);
//
//        if (spanCount < 1)
//            return;
//
//        setSpacings(outRect, parent, childCount, childIndex, itemSpanSize, spanIndex);
    }

    private boolean isLastRow(int itemCount, int spanCount, int position, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        if (itemCount - position <= spanCount) {
            int rowCount = 0;
            for (int i = position; i < itemCount - 1; i++) {
                rowCount += spanSizeLookup.getSpanSize(i);
                if (rowCount > spanCount) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
