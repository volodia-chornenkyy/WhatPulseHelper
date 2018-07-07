package com.vchornenkyy.whatpulsehelper.common.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.AbsSavedState
import android.util.AttributeSet

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

    override fun getSelectedItemId(): Int {
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

    /**
     * Fixed with help of these:
     * Kotlin problem: https://medium.com/@kirillsuslov/how-to-save-android-view-state-in-kotlin-9dbe96074d49
     * BottomNavigation problem: https://stackoverflow.com/a/48412463/3160214
     */
    internal class SavedState : AbsSavedState {
        var selectedMenuItemId: Int = 0

        constructor(superState: Parcelable) : super(superState) {}

        private constructor(parcel: Parcel, classLoader: ClassLoader?) : super(parcel, classLoader) {
            selectedMenuItemId = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(selectedMenuItemId)
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.ClassLoaderCreator<SavedState> {
                override fun createFromParcel(parcel: Parcel, classLoader: ClassLoader): SavedState {
                    return SavedState(parcel, classLoader)
                }

                override fun createFromParcel(source: Parcel): SavedState {
                    return SavedState(source, null)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
