package com.example.mysubscribers.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mysubscribers.R
import com.example.mysubscribers.data.DatabaseDataSource
import com.example.mysubscribers.data.framework.db.AppDatabase
import com.example.mysubscribers.data.framework.db.dao.SubscriberDao
import com.example.mysubscribers.databinding.FragmentSubscriberListBinding
import com.example.mysubscribers.domain.repository.SubscriberRepository
import com.example.mysubscribers.presentation.extension.navigateWithAnimations

class SubscriberListFragment : Fragment() {

    private var _binding: FragmentSubscriberListBinding? = null
    private val binding: FragmentSubscriberListBinding get() = _binding!!

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDao: SubscriberDao =
                    AppDatabase.getDatabase(requireContext()).subscriberDao()

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

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
        observeViewModelEvents()
        configureViewListener()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers)
            binding.recyclerSubscribers.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    private fun configureViewListener() {
        binding.buttonSubscribers.run {
            setOnClickListener {
                findNavController().navigateWithAnimations(R.id.subscriberFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}