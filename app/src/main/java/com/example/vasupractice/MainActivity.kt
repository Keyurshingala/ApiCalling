package com.example.vasupractice

import MemeAdapter
import android.view.LayoutInflater
import com.example.vasupractice.api.RetrofitInstance
import com.example.vasupractice.databinding.ActivityMainBinding
import com.example.vasupractice.db.MyApp.Companion.db
import com.example.vasupractice.db.daos.MyTableDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : Base<ActivityMainBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
//        var myTableDao = db.myTableDao()
//        CoroutineScope(Dispatchers.Main).launch {
//            myTableDao.delete()
//        }
//        myTableDao.getAllLive().observe(this) {
//
//        }



        CoroutineScope(Dispatchers.IO).launch {
            val res = RetrofitInstance.apiService.getUsers()

            withContext(Dispatchers.Main) {
                res.body().toGson().log()

                bind.rvMeme.adapter = MemeAdapter(res.body()!!.data.memes)

            }
        }
    }
}