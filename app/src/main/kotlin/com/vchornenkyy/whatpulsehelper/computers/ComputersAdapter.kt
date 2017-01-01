package com.vchornenkyy.whatpulsehelper.computers

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.databinding.ComputerLayoutBinding
import java.util.*

class ComputersAdapter : RecyclerView.Adapter<ComputersAdapter.ComputersViewHolder>() {

    val data = ArrayList<Computer>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComputersViewHolder? {
        val binding: ComputerLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.computer_layout, parent, false)
        return ComputersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComputersViewHolder, position: Int) {
        holder.binder.computer = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(computers: List<Computer>) {
        // TODO use DiffUtil

        data.clear()
        data.addAll(computers)
        notifyDataSetChanged()
    }

    class ComputersViewHolder(layoutBinder: ComputerLayoutBinding) : RecyclerView.ViewHolder(layoutBinder.root) {
        val binder = layoutBinder
    }
}