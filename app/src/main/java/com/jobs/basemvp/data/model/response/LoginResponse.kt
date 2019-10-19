package com.jobs.basemvp.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("success")
    var status: Boolean? = false,

    @Expose
    @SerializedName("message")
    var message: String? = null,

    @Expose
    @SerializedName("user_name")
    var userName: String? = null,

    @Expose
    @SerializedName("email")
    var email: String? = null
)