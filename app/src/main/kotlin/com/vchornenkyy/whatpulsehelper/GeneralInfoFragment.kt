package com.vchornenkyy.whatpulsehelper

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.databinding.FragmentGeneralInfoBinding
import com.vchornenkyy.whatpulsehelper.dto.User
import com.vchornenkyy.whatpulsehelper.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.presenter.GeneralInfoPresenter
import com.vchornenkyy.whatpulsehelper.view.GeneralInfoView

class GeneralInfoFragment : Fragment(), GeneralInfoView {
    var presenter: GeneralInfoPresenter? = null

    var binding: FragmentGeneralInfoBinding? = null

    companion object {

        fun newInstance(): GeneralInfoFragment {
            return GeneralInfoFragment()
        }
    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_info, container, false)

        presenter = GeneralInfoPresenter(SharedPrefAppProperties(context))

        presenter?.view = this

        presenter?.loadUser()

        return binding?.root
    }

    override fun onDestroyView() {
        presenter?.detach()
        super.onDestroyView()
    }
    //endregion

    //region View
    override fun bindUser(user: User) {
        println(user)
        binding?.user = user
    }
    //endregion
}
