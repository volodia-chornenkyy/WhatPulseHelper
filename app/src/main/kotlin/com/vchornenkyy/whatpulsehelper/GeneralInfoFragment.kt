package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.api.Cache
import com.vchornenkyy.whatpulsehelper.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import kotlinx.android.synthetic.main.fragment_general_info.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GeneralInfoFragment : Fragment() {

    companion object {

        fun newInstance(): GeneralInfoFragment {
            return GeneralInfoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_general_info, container, false)

        loadUser()

        return view
    }

    private fun loadUser() {
        val userApi = WhatPulseRestApi().userApi
        val cache: Cache = InMemoryCache.instance
        val username = "temnoi"
        cache.getUser()
                .switchIfEmpty(userApi.getUser(username))
                .map { userResponse -> convert(userResponse) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userResponse ->
                    tvUsername.text = userResponse.accountName
                    tvCountry.text = userResponse.country

                    // todo take timestamp from 'GeneratedTime'
                    cache.saveUser(userResponse, System.currentTimeMillis())
                }
    }

    //TODO move it somewhere
    fun convert(userResponse: UserResponse): UserResponse {
        // prepare model for UI (for ex. format data/time)
        return userResponse
    }
}
