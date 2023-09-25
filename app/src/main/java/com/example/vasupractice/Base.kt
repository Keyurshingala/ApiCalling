package com.example.vasupractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings

abstract class Base<T : ViewBinding> : AppCompatActivity() {

    lateinit var bind : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = setBinding(layoutInflater)
        setContentView(bind.root)

        ext { initUI() }
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): T

    abstract fun initUI()

    fun <T> T.tos() = Toast.makeText(this@Base, "$this", Toast.LENGTH_SHORT).show()
    fun <T> T.tosL() = Toast.makeText(this@Base, "$this", Toast.LENGTH_LONG).show()

}