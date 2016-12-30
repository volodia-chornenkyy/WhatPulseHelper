package com.vchornenkyy.whatpulsehelper.computers

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import java.util.*

// TODO check this for binding https://blog.jayway.com/2015/12/08/recyclerview-and-databinding/

class ComputersAdapter : RecyclerView.Adapter<ComputersAdapter.ComputersViewHolder>() {

    val data = ArrayList<Computer>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComputersViewHolder? {
        return null
    }

    override fun onBindViewHolder(holder: ComputersViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(computers: List<Computer>) {

    }

    class ComputersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
