package com.example.mysubscribers.data.framework.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mysubscribers.data.framework.db.DbConstants
import com.example.mysubscribers.data.framework.db.entity.SubscriberEntity

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM ${DbConstants.SUBSCRIBER_TABLE_NAME} WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM ${DbConstants.SUBSCRIBER_TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${DbConstants.SUBSCRIBER_TABLE_NAME}")
    fun getAll(): LiveData<List<SubscriberEntity>>

}