package com.leo.coroutine.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.leo.coroutine.R
import com.leo.coroutine.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {
    private lateinit var mBind: ActivityMainBinding
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mNavController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.timeline, R.id.indexrumor
            )
        )
        setupActionBarWithNavController(mNavController, mAppBarConfiguration)
        mBind.navView.setupWithNavController(mNavController)
    }
}
