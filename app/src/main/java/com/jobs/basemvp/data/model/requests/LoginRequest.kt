package com.jobs.basemvp.data.model.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose
    @SerializedName("c_loginname") var userName: String,
    @Expose
    @SerializedName("c_password") var password: String)