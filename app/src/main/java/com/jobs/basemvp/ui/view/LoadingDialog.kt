package com.jobs.basemvp.ui.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.annotation.NonNull
import com.jobs.basemvp.R

class LoadingDialog(@NonNull context: Context) {

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context)
    }

    fun show() {
        hide()
        progressDialog.show()
    }

    fun hide() {
        if(progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    class ProgressDialog(@NonNull context: Context) : Dialog(context, R.style.SmoothProgressDialog) {
        init {
            setContentView(R.layout.layout_loading)
            val lp = window!!.attributes
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.dimAmount = 0.7f
            window!!.attributes = lp
            setCancelable(true)
            setCanceledOnTouchOutside(false)
        }
    }
}