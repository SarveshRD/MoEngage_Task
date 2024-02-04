package com.wss.eat_space_iz.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object ToastUtil {

    private const val COMING_SOON_TOAST = "Coming Soon"

    fun showToastMassage(context: Context, massage_Desc: String, massage_value: String?) {
        Toast.makeText(
            context, "$massage_Desc $massage_value",
            Toast.LENGTH_LONG
        ).show()
    }

    fun showErrorToast(context: Context, msg: String, maxTime: Int = 0) {
        Toast.makeText(
            context, msg,
            if (maxTime == 0)
                Toast.LENGTH_SHORT
            else
                Toast.LENGTH_LONG
        ).show()
    }

    fun showSuccessfulToast(context: Context, msg: String, maxTime: Int = 0) {
        Toast.makeText(
            context, msg,
            if (maxTime == 0)
                Toast.LENGTH_SHORT
            else
                Toast.LENGTH_LONG
        ).show()
    }

    fun showCustomMassageToast(
        context: Context,
        customMassage: String = COMING_SOON_TOAST,
        maxTime: Int = 0
    ) {
        Toast.makeText(
            context, customMassage,
            if (maxTime == 0)
                Toast.LENGTH_SHORT
            else
                Toast.LENGTH_LONG
        ).show()
    }


    fun errorSnackbar(message: String, view: View?, callback: ((Boolean) -> Unit)?) {

        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).let {
            it.view.setBackgroundColor(Color.parseColor("#dd5a5a"))
            it.addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {

                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    callback?.invoke(true)
                }
            })
//        (snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView).setTextColor(Color.WHITE)
            it.show()
        }
    }

    fun successSnackbar(message: String, view: View?, callback: ((Boolean) -> Unit)?) {

        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).let {
            it.view.setBackgroundColor(Color.parseColor("#87cc6c"))
            it.addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {

                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    callback?.invoke(true)
                }
            })
//        (snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView).setTextColor(Color.WHITE)
            it.show()
        }
    }
}