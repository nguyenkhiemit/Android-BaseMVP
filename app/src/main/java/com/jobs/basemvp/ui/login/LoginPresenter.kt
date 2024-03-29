package com.jobs.basemvp.ui.login

import com.jobs.basemvp.BaseApplication.Companion.apiClient
import com.jobs.basemvp.data.model.requests.LoginRequest
import com.jobs.basemvp.ui.base.BasePresenter
import com.jobs.basemvp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter: BasePresenter<LoginView>() {

    fun login(request: LoginRequest) {
        when {
            request.userName.isEmpty() -> getView()?.validateMessage(Constants.USER_NAME_EMPTY)
            request.password.isEmpty() -> getView()?.validateMessage(Constants.PASSWORD_ERROR)
            else -> {
                getView()?.showLoading()
                var subscribe = apiClient.service.login(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getView()?.hideLoading()
                        if(it.status != null && it.status == 200) {
                            getView()?.loginSuccess(it.data)
                        } else {
                            getView()?.loginFailure(it.message)
                        }
                    }, {
                        getView()?.hideLoading()
                        getView()?.loginFailure(it.message)
                    })
                addDisposable(subscribe)
            }
        }
    }
}