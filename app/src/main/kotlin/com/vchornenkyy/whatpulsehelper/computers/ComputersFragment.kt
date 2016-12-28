package com.vchornenkyy.whatpulsehelper.computers

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.common.BaseFragment
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties

class ComputersFragment : BaseFragment(), ComputersView {
    var presenter: ComputersPresenter? = null

    companion object {

        fun newInstance(): ComputersFragment {
            return ComputersFragment()
        }

    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = ComputersPresenter(SharedPrefAppProperties(context))

        presenter?.view = this

        presenter?.loadComputers()

        return view
    }

    override fun onDestroyView() {
        presenter?.detach()
        super.onDestroyView()
    }

    //endregion

    override fun bindComputers(computers: List<Computer>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
