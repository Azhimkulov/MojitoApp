package com.azhimkulov.mojitoapp.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azhimkulov.mojitoapp.view.adapter.UltimateAdapter

/**
 * Created by azamat  on 2/21/21.
 */
class UltimateRecyclerView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RecyclerView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private lateinit var ultimateAdapter: UltimateAdapter
    var spanCount = 1
        set(value) {
            field = value
            setupLayoutManager()
        }
    var orientation = VERTICAL
        set(value) {
            field = value
            setupLayoutManager()
        }

    init {
        setupLayoutManager()
    }

    fun setAdapter(ultimateAdapter: UltimateAdapter) {
        this.ultimateAdapter = ultimateAdapter
        adapter = ultimateAdapter
    }

    fun setInterceptor(interceptor: UltimateAdapter.UltimateAdapterInterceptor) {
        ultimateAdapter.interceptor = interceptor
    }

    fun setDataSource(dataSource: UltimateAdapter.UltimateAdapterDataSource) {
        ultimateAdapter.dataSource = dataSource
    }

    fun register(layoutId: Int, type: Class<out Any>) {
        ultimateAdapter.register(layoutId, type)
    }

    fun notifyDataSetChanged() {
        if (this::ultimateAdapter.isInitialized) {
            ultimateAdapter.notifyDataSetChanged()
        }
    }

    fun notifyItemChanged(position: Int) {
        if (this::ultimateAdapter.isInitialized) {
            ultimateAdapter.notifyItemChanged(position)
        }
    }

    private fun setupLayoutManager() {
        val layoutManager = if (spanCount == 1) {
            LinearLayoutManager(context, orientation, false)
        } else {
            GridLayoutManager(context, spanCount, orientation, false)
        }
        this.layoutManager = layoutManager
    }
}