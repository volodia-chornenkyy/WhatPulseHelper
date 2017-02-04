package com.vchornenkyy.whatpulsehelper.view.screens.general_info

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.view.BaseFragment
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.databinding.GeneralInfoLayoutBinding

class GeneralInfoFragment : BaseFragment(), GeneralInfoPresenter.View {

    var presenter: GeneralInfoPresenter<GeneralInfoPresenter.View>? = null

    var binding: GeneralInfoLayoutBinding? = null

    companion object {

        fun newInstance(): GeneralInfoFragment {
            return GeneralInfoFragment()
        }
    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.general_info_layout, container, false)

        presenter = GeneralInfoPresenter(SharedPrefAppProperties(context))

        presenter?.attach(this)

        presenter?.loadUser()

        return binding?.root
    }

    override fun onDestroyView() {
        presenter?.detach()
        binding?.unbind()
        super.onDestroyView()
    }
    //endregion

    //region View
    override fun bindUser(user: User) {
        binding?.user = user
    }
    //endregion
}
