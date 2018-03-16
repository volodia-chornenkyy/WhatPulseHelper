package com.vchornenkyy.whatpulsehelper.computers

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.view.BaseAdapter

class ComputersAdapter : BaseAdapter<Computer>() {

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.computer_layout
    }

    override fun onBindViewHolder(holder: BindingViewHolder<Computer>, position: Int) {
        super.onBindViewHolder(holder, position)

        val obj = getObjForPosition(position)

        val messageView = holder.itemView.findViewById<TextView>(R.id.computer_item_message)
        val mainDataContainer = holder.itemView.findViewById<LinearLayout>(R.id.computer_item_main_data)

        if (obj.lastPulse.isNullOrEmpty()) {
            messageView.setText(R.string.no_pulses_from_pc)
            messageView.visibility = VISIBLE
            mainDataContainer.visibility = GONE
        } else {
            messageView.text = ""
            messageView.visibility = GONE
            mainDataContainer.visibility = VISIBLE
        }
    }
}