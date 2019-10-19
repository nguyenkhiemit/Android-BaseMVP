package com.jobs.basemvp

import android.app.Application
import android.content.Context
import com.jobs.basemvp.data.network.ApiClient

class BaseApplication: Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: BaseApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        val apiClient: ApiClient
            get() = ApiClient(applicationContext())
    }

    override fun onCreate() {
        super.onCreate()
    }
}