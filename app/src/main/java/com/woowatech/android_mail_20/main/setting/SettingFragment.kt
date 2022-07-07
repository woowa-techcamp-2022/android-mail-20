package com.woowatech.android_mail_20.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.woowatech.android_mail_20.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = arguments?.getString(NAME)
        binding.tvEmail.text = arguments?.getString(EMAIL)
    }

    companion object {

        const val NAME = "NAME"
        const val EMAIL = "EMAIL"

        fun getSettingFragment(name: String, email: String) =
            SettingFragment().apply {
                arguments = bundleOf(
                    NAME to name,
                    EMAIL to email
                )
            }
    }
}