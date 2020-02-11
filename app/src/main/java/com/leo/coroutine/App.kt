package com.leo.coroutine

import android.app.Application
import com.leo.system.ContextHelp
import com.leo.system.LogUtil
import com.leo.system.enume.LogType

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextHelp.setContext(this)
        LogUtil.setType(LogType.DEBUG)
    }
}