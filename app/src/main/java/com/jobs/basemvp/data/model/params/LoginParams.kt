package com.jobs.basemvp.data.model.params

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginParams internal constructor(
    @Expose
    @SerializedName("name") internal val name: String,
    @Expose
    @SerializedName("pass") internal val pass: String)