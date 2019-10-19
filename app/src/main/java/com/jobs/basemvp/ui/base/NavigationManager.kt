package com.jobs.basemvp.ui.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.jobs.basemvp.R
import java.util.*

class NavigationManager(var activity: BaseActivity) {

    private var preTime = 0L

    enum class Type {
        ADD,
        REPLACE
    }

    enum class AnimationType(val duration: Long) {
        RIGHT_LEFT(400L),
        BOTTOM_TOP(400L)
    }

    private val fragmentManager by lazy {
        activity.supportFragmentManager
    }

    fun openFragment(@IdRes containerId: Int, fragment: Fragment, type: Type, animationType: AnimationType?) {
        animationType?.let {
            val date = Date()
            val currentTime = date.time
            if(currentTime - preTime > it.duration)  {
                preTime = currentTime
            } else {
                return
            }
        }
        val transaction = fragmentManager.beginTransaction()
        if(findFragment(fragment.javaClass.simpleName)) {
            backToFragment(fragment.javaClass.simpleName)
        } else {
            animationType?.let {
                when(it) {
                    AnimationType.RIGHT_LEFT -> transaction.setCustomAnimations(R.anim.slide_enter_right, R.anim.slide_exit_left, R.anim.slide_enter_left, R.anim.slide_exit_right)
                    AnimationType.BOTTOM_TOP -> transaction.setCustomAnimations(R.anim.slide_enter_right, R.anim.slide_exit_left, R.anim.slide_enter_left, R.anim.slide_exit_right)
                }
            }
            when(type) {
                Type.ADD -> transaction.add(containerId, fragment, fragment.javaClass.simpleName)
                Type.REPLACE -> transaction.replace(containerId, fragment, fragment.javaClass.simpleName)
            }
            transaction.addToBackStack(fragment.javaClass.simpleName)
            transaction.commit()
        }
    }

    fun getCurrentFragment(@IdRes containerId: Int) : Fragment {
        return fragmentManager.findFragmentById(containerId) as Fragment
    }

    fun backToRoot() {
        var count = backStackCount()
        while (count > 1) {
            count--
            onBack()
        }
    }

    fun isRoot(): Boolean {
        return fragmentManager.backStackEntryCount <= 1
    }

    fun backToFragment(tag: String) {
        var count = backStackCount()
        while (count > 1) {
            if(tag.equals(fragmentManager.getBackStackEntryAt(count - 1).name)) {
                break
            }
            count--
            onBack()
        }
    }

    fun onBack() {
        fragmentManager.popBackStack()
        fragmentManager.executePendingTransactions()
    }

    fun findFragment(tag: String): Boolean {
        if(fragmentManager.findFragmentByTag(tag) != null) {
            return true
        }
        return false
    }

    fun backStackCount(): Int {
        return fragmentManager.backStackEntryCount
    }

}