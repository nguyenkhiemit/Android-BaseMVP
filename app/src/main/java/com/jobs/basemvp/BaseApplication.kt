package com.jobs.basemvp

import android.app.Application
import android.content.Context
import com.jobs.basemvp.data.network.ApiClient
import com.jobs.basemvp.ui.view.LoadingDialog
import com.jobs.basemvp.ui.view.SnackBar

class BaseApplication: Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: BaseApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        val apiClient: ApiClient by lazy {
            ApiClient()
        }

        val loadingDialog: LoadingDialog by lazy {
            LoadingDialog(applicationContext())
        }

        val snackBar: SnackBar by lazy {
            SnackBar()
        }
    }

}