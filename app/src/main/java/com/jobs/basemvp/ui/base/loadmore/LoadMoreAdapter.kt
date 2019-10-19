package com.jobs.basemvp.ui.base.loadmore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jobs.basemvp.R
import kotlinx.android.synthetic.main.item_load_more.view.*
import java.util.*

/**
 * Created by apple on 9/17/17.
 */
abstract class LoadMoreAdapter<T>(var arrayData: ArrayList<T?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LOAD_MORE = 100

    abstract fun getItemVType(position: Int): Int

    abstract fun onCreateVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindVHolder(holder: RecyclerView.ViewHolder, position: Int)

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    protected var parentView: RecyclerView? = null

    fun setRecyclerView(view: RecyclerView) {
        this.parentView = view
    }

    fun setLoadMoreData(loadMoreListener: (currentPage: Int) -> Unit) {
        loadMoreListener(1)
        if(parentView == null)
            return
        scrollListener = object: EndlessRecyclerOnScrollListener(parentView?.layoutManager as LinearLayoutManager){
            override fun onLoadMore(currentPage: Int) {
                loadMore()
                loadMoreListener(currentPage)
            }
        }
        parentView?.addOnScrollListener(scrollListener)
    }

    private fun loadMore() {
        arrayData.add(null)
        parentView?.post(Runnable {  notifyItemInserted(arrayData.size - 1) })
    }

    override fun getItemViewType(position: Int): Int {
        if(arrayData[position] == null) {
            return TYPE_LOAD_MORE
        } else {
            return getItemVType(position)
        }
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is LoadMoreViewHoler) {
            holder.itemView.loadMoreView.isIndeterminate = true
        } else {
            onBindVHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        if(viewType == TYPE_LOAD_MORE) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false)
            viewHolder = LoadMoreViewHoler(view)
        } else {
            viewHolder = onCreateVHolder(parent, viewType)
        }
        return viewHolder
    }

    fun restate() {
        scrollListener.restate()
        if(arrayData.size > 0 && arrayData[arrayData.size - 1] == null) {
            arrayData.remove(arrayData[arrayData.size - 1])
            notifyItemRemoved(arrayData.size)
        }
    }

    fun finishLoadMore() {
        scrollListener.finishLoadMore()
    }

    fun incrementPage() {
        scrollListener.currentPage++
    }

    fun resetPage() {
        scrollListener.currentPage = 1
    }

    fun reloadAdapter(arrayMoreData: ArrayList<T>) {
        for(i in 0..arrayMoreData.size - 1) {
            arrayData.add(arrayMoreData[i])
            notifyItemInserted(arrayData.size - 1)
        }
    }

    fun clearAdapter() {
        arrayData.clear()
        notifyDataSetChanged()
    }

    class LoadMoreViewHoler(itemView: View): RecyclerView.ViewHolder(itemView)
}