package com.vchornenkyy.whatpulsehelper.general_info

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.BaseFragment
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.databinding.FragmentGeneralInfoBinding

class GeneralInfoFragment : BaseFragment(), GeneralInfoView {

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
        binding = DataBindingUtil.inflate(inflater, R.layout.general_info_layout, container, false)

        presenter = GeneralInfoPresenter(SharedPrefAppProperties(context))

        presenter?.view = this

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
