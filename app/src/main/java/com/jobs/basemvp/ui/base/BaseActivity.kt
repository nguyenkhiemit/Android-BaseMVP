package com.jobs.basemvp.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.jobs.basemvp.ui.view.LoadingDialog
import com.jobs.basemvp.ui.view.SnackBar

abstract class BaseActivity: AppCompatActivity() {

    val navigationManager by lazy {
        NavigationManager(this)
    }

    val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    val snackBar: SnackBar by lazy {
        SnackBar()
    }
}