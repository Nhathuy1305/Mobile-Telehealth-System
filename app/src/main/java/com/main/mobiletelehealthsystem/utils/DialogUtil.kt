package com.main.mobiletelehealthsystem.utils

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.main.mobiletelehealthsystem.R


object DialogUtil {

    fun Context.createBottomSheet(): BottomSheetDialog {
        return BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
    }

    fun Activity.createBottomSheet(): BottomSheetDialog {
        return BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
    }

    fun View.setBottomSheet(bottomSheet: BottomSheetDialog) {
        bottomSheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheet.setContentView(this)
        bottomSheet.create()
        bottomSheet.show()
    }
}