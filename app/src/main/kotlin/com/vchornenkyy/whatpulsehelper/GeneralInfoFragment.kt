package com.vchornenkyy.whatpulsehelper

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.databinding.FragmentGeneralInfoBinding
import com.vchornenkyy.whatpulsehelper.presenter.GeneralInfoPresenter
import com.vchornenkyy.whatpulsehelper.view.GeneralInfoView

class GeneralInfoFragment : Fragment(), GeneralInfoView {
    val presenter = GeneralInfoPresenter()

    var binding: FragmentGeneralInfoBinding? = null

    companion object {

        fun newInstance(): GeneralInfoFragment {
            return GeneralInfoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_info, container, false)

        presenter.view = this

        presenter.loadUser()

        return binding?.root
    }

    override fun bindUser(user: UserResponse) {
        println(user)
        binding?.user = user
    }
}
