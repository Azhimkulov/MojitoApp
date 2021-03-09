package com.azhimkulov.mojitoapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by azamat  on 2/21/21.
 */
class UltimateAdapter :
    RecyclerView.Adapter<UltimateAdapter.ViewHolder>() {

    var interceptor: UltimateAdapterInterceptor? = null
    var dataSource: UltimateAdapterDataSource? = null

    private val cells: MutableList<CellInfo> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemViewBinding = DataBindingUtil.bind<ViewDataBinding>(itemView)
    }

    class CellInfo(val layoutId: Int, val type: Class<out Any>)

    interface UltimateAdapterInterceptor {
        fun recyclerView(view: View, position: Int) {}
        fun footerView(): Int? = null
        fun headerView(): Int? = null
        fun footerView(view: View) {}
        fun headerView(view: View) {}
    }

    interface UltimateAdapterDataSource {
        fun recyclerView(): Int
        fun recyclerView(position: Int): Any
    }

    companion object {
        fun newInstance(): UltimateAdapter {
            return UltimateAdapter()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemViewBinding?.setVariable(BR.adapter, this)
        return viewHolder
    }

    override fun getItemCount(): Int = getRowCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isFooterPosition(position) || isHeaderPosition(position)) {
            return
        }

        setItemBindingVariables(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isFooterPosition(position) -> interceptor!!.footerView()!!
            isHeaderPosition(position) -> interceptor!!.headerView()!!
            else -> getRowViewType(position)
        }
    }

    fun register(layoutId: Int, type: Class<*>) {
        val cell = CellInfo(layoutId, type)
        cells.add(cell)
    }

    fun onItemClick(view: View, position: Int) {
        interceptor?.recyclerView(view, position)
    }

    fun onFooterClick(view: View) {
        interceptor?.footerView(view)
    }

    private fun getRowCount(): Int {
        dataSource?.let {
            var count = it.recyclerView()

            if (interceptor?.footerView() != null) {
                count++
            }

            if (interceptor?.headerView() != null) {
                count++
            }

            return count
        }

        return 0
    }

    private fun getRowViewType(position: Int): Int {
        val adaptivePosition = getForHeaderAdaptivePosition(position)
        val model = dataSource?.recyclerView(adaptivePosition)!!
        val cellInfo = getCellInfo(model.javaClass)
        return cellInfo.layoutId
    }

    private fun getCellInfo(type: Class<out Any>): CellInfo {
        val cellInfo = cells.firstOrNull { it.type == type }
        if (cellInfo != null) {
            return cellInfo
        } else {
            throw NoSuchElementException()
        }
    }

    private fun isFooterPosition(position: Int): Boolean {
        return interceptor?.footerView() != null && dataSource?.recyclerView() == getForHeaderAdaptivePosition(
            position
        )
    }

    private fun isHeaderPosition(position: Int): Boolean {
        return interceptor?.headerView() != null && 0 == position
    }

    private fun getForHeaderAdaptivePosition(originalPosition: Int): Int {
        if (interceptor?.headerView() != null) {
            return originalPosition - 1
        }

        return originalPosition
    }

    private fun setItemBindingVariables(holder: ViewHolder, position: Int) {
        val adaptivePosition = getForHeaderAdaptivePosition(position)
        val model = dataSource?.recyclerView(adaptivePosition)
        holder.itemViewBinding?.setVariable(BR.model, model)
        holder.itemViewBinding?.setVariable(BR.position, adaptivePosition)
    }
}