package com.jobs.basemvp.ui.login

import com.jobs.basemvp.data.model.response.LoginData
import com.jobs.basemvp.ui.base.BaseView

interface LoginView : BaseView {

    fun validateMessage(errorCode: Int)

    fun loginSuccess(response: LoginData?)

    fun loginFailure(message: String?)
}