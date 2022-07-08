package com.woowatech.android_mail_20.main.mail.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.api.load
import com.woowatech.android_mail_20.databinding.HeaderMailBinding
import com.woowatech.android_mail_20.databinding.ItemMailBinding
import com.woowatech.android_mail_20.entity.Mail
import com.woowatech.android_mail_20.main.mail.ListType

sealed class MailViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
    class MailHeaderViewHolder(
        private val binding: HeaderMailBinding
    ): MailViewHolder(binding) {
        fun bind(listType: String) {
            binding.tvTitle.text = listType
        }
    }

    class MailItemViewHolder(
        private val binding: ItemMailBinding
    ): MailViewHolder(binding) {
        fun bind(mail: Mail) {
            binding.tvTitle.text = mail.title
            binding.tvName.text = mail.name
            binding.tvDetail.text = mail.detail
        }
    }
}