package com.vchornenkyy.whatpulsehelper.common.api

import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("user.php?format=json")
    fun getUser(@Query("user") username: String): Observable<UserResponse>
}