package com.vchornenkyy.whatpulsehelper.view.screens.computers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.view.BaseFragment
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties

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

        val view = LayoutInflater.from(context).inflate(R.layout.computers_layout, container, false)

        setupUi(view)

        presenter = ComputersPresenter(SharedPrefAppProperties(context))

        presenter?.attach(this)

        updateComputersData()

        return view
    }

    override fun onDestroyView() {
        presenter?.detach()
        super.onDestroyView()
    }
    //endregion

    override fun updateComputersData() {
        presenter?.loadComputers()
    }

    override fun bindComputers(computers: List<Computer>) {
        adapter.updateData(computers)
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
