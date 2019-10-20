package com.jobs.basemvp.data.services

import com.jobs.basemvp.data.model.requests.LoginRequest
import com.jobs.basemvp.data.model.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>
}