package com.example.mysubscribers.presentation.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysubscribers.data.DatabaseDataSource
import com.example.mysubscribers.data.framework.db.AppDatabase
import com.example.mysubscribers.data.framework.db.dao.SubscriberDao
import com.example.mysubscribers.databinding.FragmentSubscriberBinding
import com.example.mysubscribers.domain.repository.SubscriberRepository
import com.example.mysubscribers.presentation.extension.hideKeyboard
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment : Fragment() {

    private var _binding: FragmentSubscriberBinding? = null
    private val binding: FragmentSubscriberBinding get() = _binding!!

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDao: SubscriberDao =
                    AppDatabase.getDatabase(requireContext()).subscriberDao()
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDao)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSubscriberBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyBoard()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.inputName.text?.clear()
        binding.inputEmail.text?.clear()
    }

    private fun hideKeyBoard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.buttonSubscriber.setOnClickListener {
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()
            viewModel.addSubscriber(name, email)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}