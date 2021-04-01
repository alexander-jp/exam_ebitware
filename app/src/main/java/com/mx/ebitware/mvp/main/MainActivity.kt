package com.mx.ebitware.mvp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.ebitware.adapter.PagerAdapterFragment
import com.mx.ebitware.databinding.ActivityMainBinding
import com.mx.ebitware.mvp.clientLocal.ClientLocalFragment
import com.mx.ebitware.mvp.clientRest.ClientRestFragment

class MainActivity : AppCompatActivity() {


    private var pagerAdapter: PagerAdapterFragment? = null
    private lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        initSettings()
        initListeners()
    }

    private fun initSettings() {
        pagerAdapter = PagerAdapterFragment(supportFragmentManager)
        pagerAdapter?.addFragment(ClientRestFragment::class.java,"Api Rest")
        pagerAdapter?.addFragment(ClientLocalFragment::class.java, "Local")
        _binding.tabMain.setupWithViewPager(_binding.viewpagerMain)
        _binding.viewpagerMain.adapter = pagerAdapter
    }

    private fun initListeners() {


    }

    companion object {
        @JvmStatic
        fun newInstance() = MainActivity().apply {

        }

        @JvmStatic
        val TAG = MainActivity::class.simpleName
    }
}