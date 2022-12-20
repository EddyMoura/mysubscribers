package com.example.mysubscribers.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mysubscribers.data.framework.db.entity.SubscriberEntity
import com.example.mysubscribers.databinding.FragmentSubscriberListBinding

class SubscriberListFragment : Fragment() {

    private var _binding: FragmentSubscriberListBinding? = null
    private val binding: FragmentSubscriberListBinding get() = _binding!!

    private lateinit var viewModel: SubscriberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSubscriberListBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Eddy", "eddy@contato.com.br"),
                SubscriberEntity(2, "Luiza", "luiza@contato.com.br")
            )
        )

        binding.recyclerSubscribers.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }
    }
}