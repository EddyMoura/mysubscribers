package com.example.mysubscribers.data

import androidx.lifecycle.LiveData
import com.example.mysubscribers.data.framework.db.dao.SubscriberDao
import com.example.mysubscribers.data.framework.db.entity.SubscriberEntity
import com.example.mysubscribers.domain.repository.SubscriberRepository

class DatabaseDataSource(
    private val subscriberDao: SubscriberDao
) : SubscriberRepository {
    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntity(
            name = name,
            email = email,
        )
        return subscriberDao.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        return subscriberDao.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        return subscriberDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        return subscriberDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): LiveData<List<SubscriberEntity>> {
        return subscriberDao.getAll()
    }
}