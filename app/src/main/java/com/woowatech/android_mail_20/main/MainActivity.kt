package com.woowatech.android_mail_20.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.woowatech.android_mail_20.R
import com.woowatech.android_mail_20.databinding.ActivityMainBinding
import com.woowatech.android_mail_20.main.mail.MailFragment
import com.woowatech.android_mail_20.main.setting.SettingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        val mailFragment = MailFragment()
        val settingFragment = SettingFragment.getSettingFragment(
            intent.getStringExtra(NICKNAME)?:"",
            intent.getStringExtra(EMAIL)?:""
        )

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .add(binding.fragmentContainerView.id, mailFragment)
            .add(binding.fragmentContainerView.id, settingFragment)
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mail -> {
                    fragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentContainerView.id, mailFragment)
                        .commit()
                    binding.toolbar.visibility = View.VISIBLE
                }
                R.id.setting -> {
                    fragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentContainerView.id, settingFragment)
                        .commit()
                    binding.toolbar.visibility = View.GONE
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        with(binding) {
            if (bottomNavigationView.selectedItemId != R.id.mail) {
                super.onBackPressed()
            } else {
                bottomNavigationView.selectedItemId = R.id.mail
            }
        }
    }

    companion object {
        const val NICKNAME = "nickname"
        const val EMAIL = "email"
        fun getIntent(context: Context, nickname: String, email: String) =
            Intent(context, MainActivity::class.java).apply {
                putExtra(NICKNAME, nickname)
                putExtra(EMAIL, email)
            }
    }
}