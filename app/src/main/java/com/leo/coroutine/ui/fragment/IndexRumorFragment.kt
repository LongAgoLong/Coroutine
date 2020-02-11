package com.leo.coroutine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leo.coroutine.R
import com.leo.coroutine.databinding.FragmentIndexrumorBinding
import com.leo.coroutine.util.GsonHelp
import com.leo.coroutine.viewmodel.IndexRumorViewModel

class IndexRumorFragment : BaseFragment() {
    private lateinit var mBind: FragmentIndexrumorBinding
    private lateinit var mIndexRumorViewModel: IndexRumorViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_indexrumor,
            container,
            false
        )
        mIndexRumorViewModel =
            ViewModelProviders.of(requireActivity()).get(IndexRumorViewModel::class.java)
        mIndexRumorViewModel.mIndexRumorList.observe(viewLifecycleOwner, Observer {
            mBind.mResultTv.text = GsonHelp.getInstance().toJson(it)
        })
        mIndexRumorViewModel.downloadData()
        return mBind.root
    }
}