package com.example.hatakon.core.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hatakon.core.data.local.room.dao.DeviceDao
import com.example.hatakon.core.data.model.Device

@Database(entities = [Device::class], exportSchema = false, version = 1)

abstract class CyberCareDatabase : RoomDatabase() {
    abstract val deviceDao : DeviceDao

}
