package com.woowatech.android_mail_20.main.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.woowatech.android_mail_20.databinding.FragmentMailBinding
import com.woowatech.android_mail_20.main.MainViewModel
import com.woowatech.android_mail_20.main.mail.adapter.MailAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MailFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMailBinding
    private lateinit var rvMailList: RecyclerView
    private lateinit var adapter: MailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MailAdapter()
        rvMailList = binding.rvMailList
        rvMailList.adapter = adapter
        lifecycleScope.launch {
            sharedViewModel.listType.collect {
                adapter.submitListType(it)
                sharedViewModel.getMailList()
            }
        }

        lifecycleScope.launch {
            sharedViewModel.mailList.collect {
                adapter.submitList(it.shuffled().toMutableList())
            }
        }

    }
}