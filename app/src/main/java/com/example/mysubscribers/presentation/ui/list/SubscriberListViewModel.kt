package com.example.mysubscribers.presentation.ui.list

import androidx.lifecycle.ViewModel
import com.example.mysubscribers.domain.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscribersEvent = repository.getAllSubscribers()

}