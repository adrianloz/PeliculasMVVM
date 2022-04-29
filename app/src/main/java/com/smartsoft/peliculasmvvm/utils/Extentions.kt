package com.smartsoft.peliculasmvvm.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.smartsoft.peliculasmvvm.R

fun ViewGroup.inflate(idLayout: Int): View =
    LayoutInflater.from(this.context).inflate(idLayout, this, false)

fun Activity.toast(mensaje: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, mensaje, duration).show()

fun Activity.toast(mensaje: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, mensaje, duration).show()

fun View.hideKeyboard() {
    val inputMethodManager =
        this.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.crearDialogProgresoGeneral(idLayoutDialog: Int = R.layout.custom_dialog): AlertDialog {
    return AlertDialog.Builder(this)
        .setView(idLayoutDialog)
        .setCancelable(false)
        .create()
}

fun getConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
fun loadImage(context: Context?, url: String, imageView: ImageView) {
    Glide.with(context!!)
        .load(Constantes.IMAGE_BASE_URL.toString() + url)
        .error(R.drawable.ic_baseline_error_24)
        .into(imageView)
}

fun respuestaErrorWs(responseCode: Int): String {
    return when (responseCode) {
        RespuestaWS.FORBIDDEN -> RespuestaWS.RESPONSE_MENSSAGE_FORBIDDEN
        RespuestaWS.FOUND -> RespuestaWS.RESPONSE_MENSSAGE_FOUND
        RespuestaWS.INTERNAL_SERVER_ERROR -> RespuestaWS.RESPONSE_MENSSAGE_INTERNAL
        RespuestaWS.MOVED_PERMANENTLY -> RespuestaWS.RESPONSE_MENSSAGE_MOVED_PERMANENTLY
        RespuestaWS.NOT_FOUND -> RespuestaWS.RESPONSE_MENSSAGE_NO_FOUND
        RespuestaWS.SERVICE_UNAVAILABLE -> RespuestaWS.RESPONSE_MENSSAGE_SERVICE
        else -> RespuestaWS.RESPONSE_MENSSAGE
    }

}
