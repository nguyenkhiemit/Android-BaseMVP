package com.jobs.basemvp.ui.base.loadmore

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var isLoading = false
        private set // True if we are waiting for the last set of data to load
    private val visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    var currentPage = 1

    private var isFinishLoadMore: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(dy < 0) return
        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findLastVisibleItemPosition()

        if(totalItemCount == visibleItemCount)
            return

        if(totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && !isLoading && !isFinishLoadMore) {
            onLoadMore(currentPage)
            isLoading = true
        }
    }

    fun restate() {
        isLoading = false
        previousTotal = totalItemCount
    }

    fun finishLoadMore() {
        this.isFinishLoadMore = true
    }

    abstract fun onLoadMore(currentPage: Int)

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }

}