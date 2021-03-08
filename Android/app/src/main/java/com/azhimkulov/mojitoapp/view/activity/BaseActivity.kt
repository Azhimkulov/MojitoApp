package com.azhimkulov.mojitoapp.view.activity

import android.os.Handler
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.azhimkulov.mojitoapp.AndroidApplication
import com.azhimkulov.mojitoapp.internal.di.component.ApplicationComponent
import com.azhimkulov.mojitoapp.internal.di.module.ActivityModule
import com.azhimkulov.mojitoapp.model.FragmentAction

abstract class BaseActivity : AppCompatActivity() {

    protected fun replaceFragment(containerView: ViewGroup, fragment: Fragment) {
        setFragment(containerView, fragment, FragmentAction.REPLACE)
    }

    protected fun addFragment(containerView: ViewGroup, fragment: Fragment) {
        setFragment(containerView, fragment, FragmentAction.ADD)
    }

    private fun setFragment(
        containerView: ViewGroup,
        fragment: Fragment,
        fragmentAction: FragmentAction
    ) {
        Handler().post { setFragmentAux(containerView, fragment, fragmentAction) }
    }

    private fun setFragmentAux(
        containerView: ViewGroup,
        fragment: Fragment,
        fragmentAction: FragmentAction
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (fragmentAction) {
            FragmentAction.REPLACE -> fragmentTransaction.replace(
                containerView.id,
                fragment,
                fragment.javaClass.name
            )
            FragmentAction.ADD -> {
                fragmentTransaction.replace(containerView.id, fragment, fragment.javaClass.name)
                fragmentTransaction.addToBackStack(null)
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun getApplicationComponent(): ApplicationComponent {
        return getAndroidApplication().applicationComponent
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    protected fun hideActionBar() {
        supportActionBar?.hide()
    }

    protected fun hideStatsBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun getAndroidApplication(): AndroidApplication {
        return application as AndroidApplication
    }
}