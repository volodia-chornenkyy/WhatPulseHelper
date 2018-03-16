package com.vchornenkyy.whatpulsehelper.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.BaseFragment

class TeamsFragment : BaseFragment() {

    companion object {

        fun newInstance(): TeamsFragment {
            return TeamsFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.teams_layout, container, false)
    }
}
