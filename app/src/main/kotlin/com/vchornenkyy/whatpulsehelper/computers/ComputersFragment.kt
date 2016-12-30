package com.vchornenkyy.whatpulsehelper.computers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.BaseFragment
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import kotlinx.android.synthetic.main.computers_layout.*

class ComputersFragment : BaseFragment(), ComputersView {
    var presenter: ComputersPresenter? = null

    val adapter = ComputersAdapter()

    companion object {

        fun newInstance(): ComputersFragment {
            return ComputersFragment()
        }

    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = LayoutInflater.from(context).inflate(R.layout.computers_layout, container, false)

        setupUi()

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
        adapter.updateData(computers)
    }

    private fun setupUi() {
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }
}
