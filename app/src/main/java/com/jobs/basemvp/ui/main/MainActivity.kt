package com.jobs.basemvp.ui.main

import android.os.Bundle
import com.jobs.basemvp.R
import com.jobs.basemvp.ui.base.BaseActivity
import com.jobs.basemvp.ui.base.NavigationManager
import com.jobs.basemvp.ui.login.LoginFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationManager.openFragment(R.id.containerId, LoginFragment(), NavigationManager.Type.ADD, null)
    }
}
