package com.jobs.basemvp.data.network

import org.reactivestreams.Subscriber


abstract class ApiCallback<M> : Subscriber<M> {

    abstract fun onSuccess(model: M)

    abstract fun onFailure(message: String?)

    abstract fun onFinish()

    override fun onError(t: Throwable?) {
        t?.let {
            onFailure(it.message)
        }
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onComplete() {
        onFinish()
    }
}