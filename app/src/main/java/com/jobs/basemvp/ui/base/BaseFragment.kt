package com.jobs.basemvp.ui.base

import androidx.fragment.app.Fragment
import com.jobs.basemvp.ui.view.LoadingDialog
import com.jobs.basemvp.ui.view.SnackBar

abstract class BaseFragment: Fragment() {

    val baseActivity: BaseActivity by lazy {
        activity as BaseActivity
    }

    val navigationManager by lazy {
        NavigationManager(baseActivity)
    }

    val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(baseActivity)
    }

    val snackBar: SnackBar by lazy {
        SnackBar()
    }

}