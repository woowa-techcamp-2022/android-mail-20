package com.woowatech.android_mail_20.response

import com.woowatech.android_mail_20.entity.Mail

class MailResponse(
    private val profile: String,
    private val name: String,
    private val title: String,
    private val detail: String
) {
    fun toMail(): Mail {
        return Mail(
            profile, name, title, detail
        )
    }
}