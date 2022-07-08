package com.woowatech.android_mail_20.info

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class InfoViewModel: ViewModel() {
    val nickname = MutableStateFlow("")
    val email = MutableStateFlow("")

    fun getNicknameError(): Boolean {
        val regex = Regex("^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}\$")
        return nickname.value == "" || !regex.matches(nickname.value)
    }

    fun getEmailError(): Boolean {
        val regex = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
        return email.value == "" || !regex.matches(email.value)
    }
}