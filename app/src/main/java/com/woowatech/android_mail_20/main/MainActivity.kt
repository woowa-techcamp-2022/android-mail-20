package com.woowatech.android_mail_20.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.woowatech.android_mail_20.R
import com.woowatech.android_mail_20.databinding.ActivityMainBinding
import com.woowatech.android_mail_20.main.mail.ListType
import com.woowatech.android_mail_20.main.mail.MailFragment
import com.woowatech.android_mail_20.main.setting.SettingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentContainerView: FragmentContainerView
    private lateinit var navigationBar: NavigationBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        navigationView = binding.navigationView
        fragmentContainerView = binding.fragmentContainerView
        navigationBar = binding.navigationBar as NavigationBarView

        initFragments()
        initListeners()
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener {
            viewModel.listType.value = when(it.itemId) {
                R.id.primary -> ListType.Primary
                R.id.social -> ListType.Social
                R.id.promotion -> ListType.Promotion
                else -> ListType.Primary
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun initFragments() {
        val mailFragment = MailFragment()
        val settingFragment = SettingFragment.getSettingFragment(
            intent.getStringExtra(NICKNAME) ?: "",
            intent.getStringExtra(EMAIL) ?: ""
        )

        val fragmentManager = supportFragmentManager
        with(fragmentManager.beginTransaction()) {
            add(fragmentContainerView.id, mailFragment)
            add(fragmentContainerView.id, settingFragment)
            commit()
        }

            }
        }
    }

    override fun onBackPressed() {
        with(binding) {
            if (bottomNavigationView?.selectedItemId != R.id.mail || navigationRailView?.selectedItemId != R.id.mail) {
                super.onBackPressed()
            } else {
                bottomNavigationView.selectedItemId = R.id.mail
                navigationRailView.selectedItemId = R.id.mail
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