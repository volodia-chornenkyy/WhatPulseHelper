package com.vchornenkyy.whatpulsehelper.view.screens.computers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.view.BaseFragment

class ComputersFragment : BaseFragment(), ComputersPresenter.View {
    var presenter: ComputersPresenter<ComputersPresenter.View>? = null

    val adapter = ComputersAdapter()

    companion object {

        fun newInstance(): ComputersFragment {
            return ComputersFragment()
        }
    }

    // region Lifecycle
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.computers_layout, container, false)

        setupUi(view)

        updateComputersData()

        return view
    }
    //endregion

    //region View
    override fun updateComputersData() {
        presenter?.loadComputers()
    }

    override fun bindComputers(computers: List<Computer>) {
        adapter.updateData(computers)
    }
    //endregion

    override fun initPresenter() {
        presenter = ComputersPresenter(SharedPrefAppProperties(context), this)
        presenter?.attach()
    }

    override fun removePresenter() {
        presenter?.detach()
    }

    private fun setupUi(view: View) {
        // TODO find out why direct usage don't work
        val list: RecyclerView? = view.findViewById(R.id.list) as RecyclerView?
        val layoutManager = LinearLayoutManager(context)
        layoutManager.isAutoMeasureEnabled = true
        list?.layoutManager = layoutManager
        list?.isNestedScrollingEnabled = false
        list?.setHasFixedSize(true)
        list?.adapter = adapter
    }
}
