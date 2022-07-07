package com.woowatech.android_mail_20.main.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.woowatech.android_mail_20.R
import com.woowatech.android_mail_20.databinding.FragmentInfoBinding

class InfoFragment(
    private val onChange: () -> Unit
) : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tilNickname.editText?.doOnTextChanged { text, start, before, count ->
                val nickname = text.toString()
                val error = nickname.isEmpty() || !Regex("^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}\$").matches(nickname)
                if (error) tilNickname.error = "닉네임은 영문과 숫자를 결합한 4~12자로 입력해 주세요."
                btnNext.isEnabled = !error
                tilNickname.isErrorEnabled = error
            }
        }
    }

}