package com.vchornenkyy.whatpulsehelper.model.api

import com.vchornenkyy.whatpulsehelper.model.api.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface UserService {

    @GET("user.php?format=json")
    fun getUser(@Query("user") username: String): Observable<UserResponse>
}