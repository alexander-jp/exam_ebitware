package com.mx.ebitware.ext

import android.content.Intent
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

fun Fragment.colorAttr(@AttrRes attrColor: Int): Int {
    val typedValue = TypedValue()
    requireContext().theme.resolveAttribute(attrColor, typedValue, true)
    return typedValue.data
}

fun AppCompatActivity.colorAttr(@AttrRes attrColor: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrColor, typedValue, true)
    return typedValue.data
}

fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showSnackBar(view : View?, msg: String?) {
    view?.let {
        Snackbar.make(it, msg!!, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.showSnackBar(msg: String) {
    view?.let {
        Snackbar.make(it, msg, Snackbar.LENGTH_SHORT).show()
    }
}

fun <T> AppCompatActivity.changeActivity(clazz: Class<T>) {
    val intent = Intent(this, clazz)
    startActivity(intent)
}


fun <T> Fragment.changeActivity(clazz: Class<T>) {
    val intent = Intent(activity, clazz)
    //val transition : Transition = Slide(Gravity.RIGHT)
    //transition.duration = 1000
    //transition.interpolator = DecelerateInterpolator()
    //activity?.window?.exitTransition = transition
    //startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity()).toBundle());
    startActivity(intent)

}
