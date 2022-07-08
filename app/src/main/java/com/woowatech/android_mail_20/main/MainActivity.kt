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
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentContainerView: FragmentContainerView
    private lateinit var navigationBar: NavigationBarView

    private lateinit var mailFragment: MailFragment
    private lateinit var settingFragment: SettingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        navigationView = binding.navigationView
        fragmentContainerView = binding.fragmentContainerView
        navigationBar = binding.navigationBar as NavigationBarView
        mailFragment = MailFragment()
        settingFragment = SettingFragment.getSettingFragment(
            intent?.getStringExtra(NICKNAME) ?: "",
            intent?.getStringExtra(EMAIL) ?: ""
        )

        initFragments()
        initListeners()
        initStateFlow()
    }

    private fun initStateFlow() {
        val fragmentManager = supportFragmentManager
        lifecycleScope.launch {
            viewModel.tabType.collect {
                with(fragmentManager.beginTransaction()) {
                    when (it) {
                        TabType.MailTab -> {
                            replace(fragmentContainerView.id, mailFragment)
                            navigationBar.selectedItemId = R.id.mail
                        }
                        TabType.SettingTab -> {
                            replace(fragmentContainerView.id, settingFragment)
                            navigationBar.selectedItemId = R.id.setting
                        }
                    }
                    commit()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.listType.collect {
                when (it) {
                    ListType.Primary -> {
                        navigationView.setCheckedItem(R.id.primary)
                    }
                    ListType.Social -> {
                        navigationView.setCheckedItem(R.id.social)
                    }
                    ListType.Promotion -> {
                        navigationView.setCheckedItem(R.id.promotion)
                    }
                }
            }
        }
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

        navigationBar.setOnItemSelectedListener {
            viewModel.tabType.value = when(it.itemId) {
                R.id.mail -> TabType.MailTab
                R.id.setting -> TabType.SettingTab
                else -> TabType.MailTab
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun initFragments() {

        val fragmentManager = supportFragmentManager
        with(fragmentManager.beginTransaction()) {
            add(fragmentContainerView.id, mailFragment)
            add(fragmentContainerView.id, settingFragment)
            commit()
        }
    }

    override fun onBackPressed() {
        with(viewModel) {
            if (tabType.value == TabType.MailTab && listType.value == ListType.Primary) {
                super.onBackPressed()
            } else if (tabType.value == TabType.MailTab && listType.value != ListType.Primary) {
                listType.value = ListType.Primary
            } else {
                tabType.value = TabType.MailTab
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