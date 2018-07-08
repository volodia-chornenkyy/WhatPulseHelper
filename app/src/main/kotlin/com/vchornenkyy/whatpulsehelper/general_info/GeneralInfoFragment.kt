package com.vchornenkyy.whatpulsehelper.general_info

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.common.BaseFragment
import com.vchornenkyy.whatpulsehelper.common.ILoadingView
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties

class GeneralInfoFragment : BaseFragment(), GeneralInfoView {

    var presenter: GeneralInfoPresenter? = null

    var binding: GeneralInfoLayoutBinding? = null

    companion object {

        fun newInstance(): GeneralInfoFragment {
            return GeneralInfoFragment()
        }
    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.general_info_layout, container, false)

        presenter = GeneralInfoPresenter(SharedPrefAppProperties(context!!))

        presenter?.view = this

        binding?.infoLoading?.setOnClickListener { presenter?.onLoadingInfoDismissed() }

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

    override fun showLoadingInfo(date: String, time: String) {
        val string: String = getString(R.string.info_default_cache, date, time)
        binding?.infoLoading?.setContent(string)
        binding?.infoLoading?.visibility = View.VISIBLE
    }

    override fun hideLoadingInfo() {
        binding?.infoLoading?.visibility = View.GONE
    }

    //endregion

    //region IRefreshable
    override fun onRefresh() {
        presenter?.forceLoadUser()
    }
    //endregion

    //region ILoadingView
    override fun showLoading() {
        if (activity is ILoadingView) {
            (activity as ILoadingView).showLoading()
        }
    }

    override fun hideLoading() {
        if (activity is ILoadingView) {
            (activity as ILoadingView).hideLoading()
        }
    }
    //endregion
}
