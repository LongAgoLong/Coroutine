package com.leo.coroutine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leo.coroutine.R
import com.leo.coroutine.databinding.FragmentTimelineBinding
import com.leo.coroutine.util.GsonHelp
import com.leo.coroutine.viewmodel.TimelineViewModel

class TimelineFragment : BaseFragment() {
    private lateinit var mBind: FragmentTimelineBinding
    private lateinit var mTimelineViewModel: TimelineViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_timeline,
            container,
            false
        )

        mTimelineViewModel =
            ViewModelProviders.of(requireActivity()).get(TimelineViewModel::class.java)
        mTimelineViewModel.mTimelineList.observe(viewLifecycleOwner, Observer {
            mBind.mResultTv.text = GsonHelp.getInstance().toJson(it)
        })
        mTimelineViewModel.downloadData()
        return mBind.root
    }
}
