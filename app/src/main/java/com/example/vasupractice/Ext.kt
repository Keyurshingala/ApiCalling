package com.example.vasupractice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import java.io.File
import java.util.*
import java.util.concurrent.Executors


typealias st = R.string

@JvmField
val gson = Gson()

fun <T> T.toGson(): String = gson.toJson(this)

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.gon() {
    this.visibility = GONE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun <T> T.tos(ctx: Context) = Toast.makeText(ctx, "$this", Toast.LENGTH_SHORT).show()
fun <T> T.tosL(ctx: Context) = Toast.makeText(ctx, "$this", Toast.LENGTH_LONG).show()

//fun getTransparentBmp(ctx: Context): BitmapDrawable {
//    val bitmapDrawable = BitmapDrawable(ctx.resources, BaseAc.transBmp)
//    bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
//    return bitmapDrawable;
//}

fun SeekBar.customSbChangeListener(onChange: (Int) -> Unit, onStop: (Int) -> Unit) {
    this.setOnSeekBarChangeListener(null)
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(sb: SeekBar, progress: Int, b: Boolean) {
            onChange(progress)
        }

        override fun onStopTrackingTouch(sb: SeekBar) {
            onStop(sb.progress)
        }

        override fun onStartTrackingTouch(sb: SeekBar) {}
    })
}

fun String.toColor(): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        e.print()
        Color.parseColor("#000000")
    }
}

//------------------------------------- debug utils -----------------------------------------//
const val TAG = "FATZ"
fun Any?.log() = ext { Log.wtf(TAG, "$this") }

fun ext(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.print()
    }
}

fun Exception.print() {
//    if (BuildConfig.DEBUG) {
        printStackTrace()
        "$message | ${javaClass.name}".log()
//    }
//    FirebaseCrashlytics.getInstance().recordException(this)
}
//------------------------------------- debug utils -----------------------------------------//


//------------------------------------- bitmap utils -------------------------------------//
fun Bitmap.copy(): Bitmap {
    System.gc()
    return copy(config, true)
}


//Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions.
// If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use override(Target.SIZE_ORIGINAL).
// Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.
fun ImageView.load(any: Any?, withCrossFade: Boolean = false) {
    Glide.with(this).load(any)
//        .placeholder(R.drawable.ic_img_loading)
        .error(R.drawable.ic_launcher_background)
            .override(250)
        .apply {
            if (withCrossFade)
                transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
            else
                into(this@load)
        }
}

fun ImageView.loadOnly(any: Any?) =
    Glide.with(this)
        .load(any)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(this@loadOnly)


fun String.capFirstLetter(): String {
    if (isEmpty() || isBlank()) return this

    val words = trim().split("\\s".toRegex()).toTypedArray()
    var capitalizeStr = ""
    for (word in words) {
        if (word.isEmpty()) continue

        val firstLetter = word.substring(0, 1)                       // Capitalize first letter
        val remainingLetters = word.substring(1)            // Get remaining letter
        capitalizeStr += firstLetter.uppercase(Locale.getDefault()) + remainingLetters.lowercase(
            Locale.getDefault()
        ) + " "
    }
    return capitalizeStr
}


fun isVPN(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    /*for (n in cm.allNetworks) {
        cm.getNetworkCapabilities(n)?.let {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                return true
            }
        }
    }*/

    cm.getNetworkCapabilities(cm.activeNetwork)?.let {
        if (it.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) return true
    }
    return false
}


fun disableBtn(v: View) {
    v.isEnabled = false
    Handler(Looper.getMainLooper()).postDelayed({ v.isEnabled = true }, 1500)
}

fun View.removeFromParent() {
    if (parent != null) (parent as ViewGroup).removeView(this)
}


fun File.toBmp(): Bitmap = BitmapFactory.decodeFile(absolutePath, BitmapFactory.Options())
/*
Execution Order java
 static{}
 {}
 onCreate()
 onStart()
*/



fun onBackground(block: () -> Unit) {
    Executors.newSingleThreadExecutor().execute {
        block()
    }
}

