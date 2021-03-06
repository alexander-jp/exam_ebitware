package com.mx.ebitware.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.mx.ebitware.R
import com.mx.ebitware.ext.colorAttr
import com.mx.ebitware.utils.ViewGroupUtils
import com.wang.avi.AVLoadingIndicatorView

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

open class BaseActivity : AppCompatActivity() {

    private var progress: AVLoadingIndicatorView? = null
    private var myProgress: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressDialog()

    }

    @SuppressLint("InflateParams")
    private fun initProgressDialog() {
        val myView = layoutInflater.inflate(R.layout.custom_progress_dialog, null)
        myProgress = Dialog(this, R.style.cornerDialog)
        myProgress?.setCancelable(false)
        myProgress?.setContentView(myView)
        myProgress?.create()
    }

    fun showDialogProgress(status: Boolean) {
        if (status) {
            if (!myProgress?.isShowing!!) {
                myProgress?.show()
            }
        } else {
            if (myProgress?.isShowing!!) {
                myProgress?.dismiss()
            }
        }
    }


    fun initProgress(view: ViewGroup?) {
        view?.let {
            val relative = RelativeLayout(this)
            relative.gravity = Gravity.CENTER
            progress = AVLoadingIndicatorView(this)
            progress?.setIndicator(getString(R.string.name_progressAV))
            progress?.layoutParams = ViewGroupUtils.VGProgress()
            progress?.setIndicatorColor(colorAttr(R.attr.colorAccent))
            relative.layoutParams = ViewGroupUtils.VGWidthAndHeight()
            relative.addView(progress)
            it.addView(relative)
        }
    }

    fun showProgress(show: Boolean) {
        if (show) {
            if (!progress?.isShown!!) {
                progress?.show()
            }
        } else {
            if (progress?.isShown!!) {
                progress?.hide()
            }
        }
    }
}