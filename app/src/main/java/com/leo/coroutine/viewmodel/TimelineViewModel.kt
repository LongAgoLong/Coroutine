package com.leo.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.leo.coroutine.entity.TimeLineNews
import com.leo.coroutine.net.ApiProxy
import com.leo.coroutine.util.GsonHelp
import kotlinx.coroutines.launch

class TimelineViewModel : BaseViewModel() {
    var mTimelineList: MutableLiveData<MutableList<TimeLineNews>> = MutableLiveData()

    fun downloadData() {
        uiScope.launch {
            val result = ApiProxy.getInstance().getTimelineService()
            val code = result.code()
            val body = result.body()
            if (code == 200 && !body.isNullOrEmpty()) {
                val type = object : TypeToken<MutableList<TimeLineNews>>() {

                }.type
                val list = GsonHelp.getInstance()
                    .fromJson<MutableList<TimeLineNews>>(body.toString(), type)
                mTimelineList.postValue(list)
            } else {
                val errorBody = result.errorBody()
            }
        }
    }
}