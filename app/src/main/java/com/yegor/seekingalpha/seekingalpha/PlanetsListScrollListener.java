package com.yegor.seekingalpha.seekingalpha;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Doing endless scrolling, taken from (https://gist.github.com/ssinss/e06f12ef66c51252563e)
 */
abstract class PlanetsListScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.

    private int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;

    private int mCurrentPage = 0;

    private LinearLayoutManager mLinearLayoutManager;

    PlanetsListScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLinearLayoutManager.getItemCount();
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (mTotalItemCount > previousTotal) {
                loading = false;
                previousTotal = mTotalItemCount;
            }
        }
        if (!loading && (mTotalItemCount - mVisibleItemCount)
                <= (mFirstVisibleItem + VISIBLE_THRESHOLD)) {
            // End has been reached

            // Do something
            mCurrentPage++;

            onLoadMore(mCurrentPage);

            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
