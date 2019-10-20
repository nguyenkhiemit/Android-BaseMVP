package com.jobs.basemvp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jobs.basemvp.R
import com.jobs.basemvp.data.model.requests.LoginRequest
import com.jobs.basemvp.data.model.response.LoginData
import com.jobs.basemvp.ui.base.BaseFragment
import com.jobs.basemvp.utils.Constants
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment: BaseFragment(), LoginView {

    private val presenter: LoginPresenter by lazy {
        LoginPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        presenter.onAttach(this)
        view.userNameEditText.setText("10001")
        view.passwordEditText.setText("123456")

        view.loginButton.setOnClickListener {
            val userName = userNameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val request = LoginRequest(userName, password)
            presenter.login(request)
        }
        return view
    }

    override fun validateMessage(errorCode: Int) {
        when(errorCode) {
            Constants.USER_NAME_EMPTY -> snackBar.show(view!!, getString(R.string.user_name_not_empty), false)
            Constants.PASSWORD_ERROR -> snackBar.show(view!!, getString(R.string.password_not_empty), false)
        }
    }

    override fun loginSuccess(response: LoginData?) {
        snackBar.show(view!!, getString(R.string.login_success), true)
    }

    override fun loginFailure(message: String?) {
        snackBar.show(view!!, getString(R.string.login_failure), false)
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unBindView()
    }

}