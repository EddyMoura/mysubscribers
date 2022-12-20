package com.example.mysubscribers.data.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysubscribers.data.framework.db.DbConstants

@Entity(tableName = DbConstants.SUBSCRIBER_TABLE_NAME)
data class SubscriberEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbConstants.SUBSCRIBER_COLUMN_INFO_ID)
    val id: Long = 0,
    @ColumnInfo(name = DbConstants.SUBSCRIBER_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.SUBSCRIBER_COLUMN_INFO_EMAIL)
    val email: String
)
