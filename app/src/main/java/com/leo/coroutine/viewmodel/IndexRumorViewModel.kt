package com.leo.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.leo.coroutine.entity.IndexRumorNews
import com.leo.coroutine.net.ApiProxy
import com.leo.coroutine.util.GsonHelp
import kotlinx.coroutines.launch

class IndexRumorViewModel : BaseViewModel() {
    var mIndexRumorList: MutableLiveData<MutableList<IndexRumorNews>> = MutableLiveData()
    fun downloadData() {
        uiScope.launch {
            val response = ApiProxy.getInstance().getIndexRumorList()
            val code = response.code()
            val body = response.body()
            if (code == 200 && !body.isNullOrEmpty()) {
                val type = object : TypeToken<MutableList<IndexRumorNews>>() {

                }.type
                val list = GsonHelp.getInstance()
                    .fromJson<MutableList<IndexRumorNews>>(body.toString(), type)
                mIndexRumorList.postValue(list)
            } else {
                val errorBody = response.errorBody()
            }
        }
    }
}