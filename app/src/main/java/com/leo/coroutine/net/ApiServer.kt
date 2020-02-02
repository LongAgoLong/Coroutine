package com.leo.coroutine.net

import retrofit2.Call
import retrofit2.http.GET

interface ApiServer {
    @GET("getTimelineService")
    fun getTimelineService(): Call<String>

    @GET("getIndexRumorList")
    fun getIndexRumorList(): Call<String>
}
