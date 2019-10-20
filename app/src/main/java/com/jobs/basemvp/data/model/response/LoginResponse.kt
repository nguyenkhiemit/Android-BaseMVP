package com.jobs.basemvp.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("success")
    val status: Boolean? = false,

    @Expose
    @SerializedName("message")
    val message: String? = null,

    @Expose
    @SerializedName("data")
    val data: LoginData? = null
)

data class LoginData(
    @Expose
    @SerializedName("user_name")
    val userName: String? = null,

    @Expose
    @SerializedName("email")
    val email: String? = null
)