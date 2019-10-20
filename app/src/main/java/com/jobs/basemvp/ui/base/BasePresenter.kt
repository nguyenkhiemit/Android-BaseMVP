package com.jobs.basemvp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V: BaseView> {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private var view: V? = null

    private val isViewAttached: Boolean
        get() = view != null

    fun onAttach(view: V?) {
        this.view = view
    }

    fun getView(): V? = view

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun unBindView() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        this.view = null
    }

}