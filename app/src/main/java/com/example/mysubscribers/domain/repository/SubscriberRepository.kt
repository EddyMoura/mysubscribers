package com.example.mysubscribers.domain.repository

import androidx.lifecycle.LiveData
import com.example.mysubscribers.data.framework.db.entity.SubscriberEntity
import com.example.mysubscribers.domain.model.Subscriber

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long
    suspend fun updateSubscriber(id: Long, name: String, email: String)
    suspend fun deleteSubscriber(id: Long)
    suspend fun deleteAllSubscribers()
    fun getAllSubscribers(): LiveData<List<SubscriberEntity>>
}