package com.woowatech.android_mail_20.info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.woowatech.android_mail_20.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding
    private val viewModel: InfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        with(binding) {
            etNickname.editText?.addTextChangedListener {
                val errorEnabled = viewModel.getNicknameError()
                etNickname.isErrorEnabled = errorEnabled
                if (errorEnabled) etNickname.error = "닉네임은 영문과 숫자를 결합한 4~12자로 입력해주세요."
                btnNext.isEnabled = !(viewModel.getNicknameError() && viewModel.getEmailError())
            }

            etEmail.editText?.addTextChangedListener {
                val errorEnabled = viewModel.getEmailError()
                etEmail.isErrorEnabled = errorEnabled
                if (errorEnabled) etEmail.error = "이메일 형식이 잘못되었습니다."
                btnNext.isEnabled = !(viewModel.getNicknameError() && viewModel.getEmailError())
            }

        }
    }
}