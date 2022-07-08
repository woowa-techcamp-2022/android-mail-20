package com.woowatech.android_mail_20.main.mail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.woowatech.android_mail_20.databinding.HeaderMailBinding
import com.woowatech.android_mail_20.databinding.ItemMailBinding
import com.woowatech.android_mail_20.entity.Mail
import com.woowatech.android_mail_20.main.mail.ListType

class MailAdapter: ListAdapter<Mail, MailViewHolder>(diffUtil) {

    private var listType = ListType.Primary

    companion object {
        const val HEADER = 0
        const val ITEM = 1
        private val diffUtil = object: DiffUtil.ItemCallback<Mail>() {
            override fun areItemsTheSame(oldItem: Mail, newItem: Mail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Mail, newItem: Mail): Boolean {
                return oldItem.detail == newItem.detail
            }
        }
    }

    fun submitListType(it: ListType) {
        listType = it
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER else ITEM
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            HEADER -> MailViewHolder.MailHeaderViewHolder(HeaderMailBinding.inflate(inflater, parent, false))
            ITEM -> MailViewHolder.MailItemViewHolder(ItemMailBinding.inflate(inflater, parent, false))
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        when(holder) {
            is MailViewHolder.MailHeaderViewHolder -> {
                holder.bind(listType.value)
            }
            is MailViewHolder.MailItemViewHolder -> {
                holder.bind(getItem(position - 1))
            }
        }
    }
}