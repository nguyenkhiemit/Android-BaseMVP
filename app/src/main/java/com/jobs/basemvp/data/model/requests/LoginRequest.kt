package com.jobs.basemvp.data.model.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose
    @SerializedName("name") var userName: String,
    @Expose
    @SerializedName("pass") var password: String)