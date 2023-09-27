package com.example.vasupractice

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
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


fun ImageView.load(any: Any?, withCrossFade: Boolean = false) {
//    val cpd = CircularProgressDrawable(this.context)
//    cpd.strokeWidth = 7f
//    cpd.centerRadius = 35f
//    cpd.setColorSchemeColors(Color.GREEN, Color.CYAN, Color.MAGENTA)
//    cpd.start()

    Glide.with(this).load(any)
//            .placeholder(cpd)
            .error(R.drawable.ic_launcher_background)
            .override(100)
            .apply {
                if (withCrossFade)
                    transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
                else
                    into(this@load)
            }
}

fun ImageView.loadWithoutCatch(any: Any?) =
        Glide.with(this)
                .load(any)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(this@loadWithoutCatch)

fun onBackground(block: () -> Unit) {
    Executors.newSingleThreadExecutor().execute {
        block()
    }
}

