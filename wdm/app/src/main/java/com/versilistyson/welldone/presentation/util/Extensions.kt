package com.versilistyson.welldone.presentation.util

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.DialogFragment
import com.versilistyson.welldone.R

fun DialogFragment.showCancelOrNotDialog() =
    AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog)
        .setMessage("Are you sure you want to cancel?")
        .setPositiveButton("YES"){ _, _ ->
            dismiss()
        }
        .setNegativeButton("NO"){ _, _ -> }
        .create().show()

fun View.beGone(){
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible(){
    visibility = View.INVISIBLE
}