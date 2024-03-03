package com.example.hatakon.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hatakon.core.data.model.Device

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices")
    suspend fun getAllRecentDevices(): List<Device>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: Device)
}