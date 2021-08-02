package com.sahu.costats

import android.os.Bundle
import com.sahu.appUtil.ui.BaseActivity
import com.sahu.costats.ext.replaceFragment
import com.sahu.costats.ui.main.MainFragment
import com.sahu.costats.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>(R.layout.main_activity) {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//    }

    override fun init(savedInstanceState: Bundle?, binding: MainActivityBinding) {
        if(savedInstanceState == null) {
            replaceFragment(
                R.id.container,
                MainFragment.newInstance(),
                "MainFragment",
                addToStack = false
            )
        }
    }
}