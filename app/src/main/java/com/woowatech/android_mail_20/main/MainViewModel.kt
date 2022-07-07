package com.woowatech.android_mail_20.main

import androidx.lifecycle.ViewModel
import com.woowatech.android_mail_20.entity.Mail
import com.woowatech.android_mail_20.main.instance.MailObject.mailRepository
import com.woowatech.android_mail_20.response.MailResponse
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {
    val listType = MutableStateFlow(ListType.Primary)

    private val _mailList = MutableStateFlow<List<Mail>>(emptyList())
    val mailList: MutableStateFlow<List<Mail>> = _mailList

    fun getMailList() {
        _mailList.value = when(listType.value) {
            ListType.Primary -> mailRepository.getPrimary().map(MailResponse::toMail)
            ListType.Social -> mailRepository.getSocial().map(MailResponse::toMail)
            ListType.Promotion -> mailRepository.getPromotion().map(MailResponse::toMail)
        }
    }
}