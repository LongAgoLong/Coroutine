package com.leo.coroutine.util

import com.google.gson.Gson


object GsonHelp {
    private object GsonHolder {
        val INSTANCE = Gson()
    }

    fun getInstance(): Gson {
        return GsonHolder.INSTANCE
    }
}