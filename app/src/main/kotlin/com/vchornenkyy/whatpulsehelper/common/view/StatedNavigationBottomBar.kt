package com.vchornenkyy.whatpulsehelper.common.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet
import android.view.View

/**
 * @author chornenkyy@gmail.com
 * *
 * @since 21.01.2017
 */

class StatedNavigationBottomBar : BottomNavigationView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    public override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.selectedMenuItemId = selectedItemId
        return ss
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)

        val selectedMenuItemId = ss.selectedMenuItemId
        restoreSelectedItemState(selectedMenuItemId)
    }

    private val selectedItemId: Int
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

    internal class SavedState : View.BaseSavedState {
        var selectedMenuItemId: Int = 0

        constructor(superState: Parcelable) : super(superState) {}

        private constructor(`in`: Parcel) : super(`in`) {
            selectedMenuItemId = `in`.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(selectedMenuItemId)
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
