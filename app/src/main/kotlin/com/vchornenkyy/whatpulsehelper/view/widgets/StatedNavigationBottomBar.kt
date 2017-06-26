package com.vchornenkyy.whatpulsehelper.view.widgets

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet

/**
 * @author chornenkyy@gmail.com
 * *
 * @since 21.01.2017
 */

class StatedNavigationBottomBar : BottomNavigationView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val keySuperState = "superState"
    private val keySelectedItemId = "idOfSelectedItem"

    public override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(keySuperState, super.onSaveInstanceState())

        bundle.putInt(keySelectedItemId, idOfSelectedItem)
        // TODO check value from native selectedItemId

        return bundle
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        val bundle = state as Bundle
        super.onRestoreInstanceState(bundle.getParcelable<Parcelable>(keySuperState))

        val selectedItemId = bundle.getInt(keySelectedItemId)
        restoreSelectedItemState(selectedItemId)
    }

    private val idOfSelectedItem: Int
        get() {
            (0..menu.size() - 1)
                    .map { menu.getItem(it) }
                    .filter { it.isChecked }
                    .forEach { return it.itemId }
            return 0
        }

    private fun restoreSelectedItemState(menuItemId: Int) {
        val menuItem = menu.findItem(menuItemId)
        if (menuItem != null) {
            menuItem.isChecked = true
        }
    }
}
