package com.woowatech.android_mail_20.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.woowatech.android_mail_20.databinding.ActivityMainBinding
import com.woowatech.android_mail_20.extension.add
import com.woowatech.android_mail_20.main.info.InfoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val infoFragment = InfoFragment {
            //replace main page
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.add(binding.fragmentContainerViewMain, infoFragment)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }
    }
}