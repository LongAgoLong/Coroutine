package com.leo.coroutine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leo.system.LogUtil

open class BaseFragment : Fragment() {
    protected val TAG = BaseFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d(TAG, "onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
