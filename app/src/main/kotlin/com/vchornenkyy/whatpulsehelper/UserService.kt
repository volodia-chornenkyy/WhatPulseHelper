package com.vchornenkyy.whatpulsehelper

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/user.php?format=json")
    fun getUser(@Query("user") username: String): Call<ResponseBody>
}