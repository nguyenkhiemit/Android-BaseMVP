package com.jobs.basemvp.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    val navigationManager by lazy {
        NavigationManager(this)
    }
}