package com.azhimkulov.mojitoapp.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.azhimkulov.mojitoapp.internal.di.HasComponent
import com.azhimkulov.mojitoapp.model.ToastDuration
import com.azhimkulov.mojitoapp.view.viewmodel.LoadingViewModel

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingViewModel()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getComponent())!!
    }

    private fun setupLoadingViewModel() {
        provideLoadingViewModel()?.let {
            it.setToast.observe(context as LifecycleOwner) { messageAndDuration ->
                showToast(
                    messageAndDuration.first,
                    messageAndDuration.second
                )
            }
            it.context = { context }
        }
    }

    private fun showToast(message: String, duration: ToastDuration) {
        context?.let {
            Toast.makeText(it, message, duration.value).show()
        }
    }

    abstract fun provideLoadingViewModel(): LoadingViewModel?
}