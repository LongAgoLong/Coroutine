package com.leo.coroutine.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.reflect.TypeToken
import com.leo.coroutine.R
import com.leo.coroutine.databinding.ActivityMainBinding
import com.leo.coroutine.entity.TimeLineNews
import com.leo.coroutine.net.ApiProxy
import com.leo.coroutine.util.GsonHelp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var mBind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        downloadData()
    }

    private fun downloadData() {
        GlobalScope.launch {
            val result = ApiProxy.getInstance().getTimelineService()
            val code = result.code()
            val body = result.body()
            if (code == 200 && !body.isNullOrEmpty()) {
                val type = object : TypeToken<MutableList<TimeLineNews>>() {

                }.type
                val list = GsonHelp.getInstance().fromJson<MutableList<TimeLineNews>>(body.toString(), type)
            } else {
                val errorBody = result.errorBody()
            }
        }
    }
}
