package com.jobs.basemvp.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    val navigationManager by lazy {
        NavigationManager(baseActivity)
    }

    val baseActivity: BaseActivity by lazy {
        activity as BaseActivity
    }

}