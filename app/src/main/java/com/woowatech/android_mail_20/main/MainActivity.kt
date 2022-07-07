package com.woowatech.android_mail_20.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.woowatech.android_mail_20.R
import com.woowatech.android_mail_20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            toolbar.setNavigationOnClickListener {
                drawerLayout.open()
            }

            navigationView.setNavigationItemSelectedListener {
                viewModel.listType.value = when (it.itemId) {
                    R.id.primary -> ListType.Primary
                    R.id.social -> ListType.Social
                    R.id.promotion -> ListType.Promotion
                    else -> ListType.Primary
                }

                return@setNavigationItemSelectedListener true
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