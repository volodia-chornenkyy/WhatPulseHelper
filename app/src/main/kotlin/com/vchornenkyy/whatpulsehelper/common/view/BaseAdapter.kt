package com.vchornenkyy.whatpulsehelper.common.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.BR
import java.util.*

/**
 * This class works with DataBinding.
 * As a result, getLayoutIdForPosition() should return respective layout id with variable name="obj".
 * Also class which is used in the variable should be passed into generic.
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BindingViewHolder<T>>() {

    private val data = ArrayList<T>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T>, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<T>) {
        // TODO use DiffUtil

        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun getObjForPosition(position: Int): T {
        return data[position]
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    class BindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: T) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings() // TODO not sure about this
        }
    }
}