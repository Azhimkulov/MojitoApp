package com.azhimkulov.mojitoapp.view.custom.data.binding

import androidx.databinding.BindingAdapter
import com.azhimkulov.mojitoapp.view.adapter.UltimateAdapter
import com.azhimkulov.mojitoapp.view.custom.UltimateRecyclerView

/**
 * Created by azamat  on 2/21/21.
 */

@BindingAdapter("app:adapter")
fun setAdapter(ultimateRecyclerView: UltimateRecyclerView, ultimateAdapter: UltimateAdapter) {
    ultimateRecyclerView.setAdapter(ultimateAdapter)

}