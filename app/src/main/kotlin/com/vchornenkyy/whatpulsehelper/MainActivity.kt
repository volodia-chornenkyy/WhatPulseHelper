package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .client(OkHttpClient())
                .build()
        val userService = retrofit.create(UserService::class.java)
        userService.getUser("temnoi").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e(MainActivity::class.java.name, t?.message, t)
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val responseContent = response?.body()?.string()
                Log.d(MainActivity::class.java.name, responseContent)
            }
        })

    }
}
