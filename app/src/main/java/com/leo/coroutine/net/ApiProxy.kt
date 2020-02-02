package com.leo.coroutine.net

import com.leo.okhttplib.dns.TimeoutDNS
import com.leo.okhttplib.interceptor.LogIntercepter
import com.leo.okhttplib.interceptor.RetryIntercepter
import com.leo.okhttplib.ssl.HttpSSLUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit


class ApiProxy private constructor() {
    private var api: ApiServer

    init {
        val unSafeConnectionSpecs = HttpSSLUtils.getUnSafeConnectionSpecs()
        val sslParams = HttpSSLUtils.getSslSocketFactory(null, null, null)
        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .connectionSpecs(unSafeConnectionSpecs)
            .dns(TimeoutDNS(3, TimeUnit.SECONDS))
            .retryOnConnectionFailure(true)
            .addInterceptor(RetryIntercepter(2))
            .addInterceptor(LogIntercepter("OKHTTP", true))
            .proxy(Proxy.NO_PROXY)
            //其他配置
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://49.232.173.220:3001/data/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(ApiServer::class.java)
    }

    companion object {
        private var mInstance: ApiProxy? = null
        fun getInstance(): ApiProxy {
            return mInstance ?: synchronized(this) {
                mInstance ?: ApiProxy().also { mInstance = it }
            }
        }
    }

    /**
     * 按时间线获取事件
     */
    suspend fun getTimelineService(): Response<String> = withContext(Dispatchers.IO) {
        api.getTimelineService().execute()
    }

    /**
     * 最新辟谣
     */
    suspend fun getIndexRumorList(): Response<String> = withContext(Dispatchers.IO) {
        api.getIndexRumorList().execute()
    }
}