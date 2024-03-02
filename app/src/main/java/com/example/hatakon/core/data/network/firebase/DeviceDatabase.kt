package com.example.hatakon.core.data.network.firebase

import com.example.hatakon.core.data.model.Device
import com.google.firebase.firestore.FirebaseFirestore

class DeviceDatabase {

    private val db = FirebaseFirestore.getInstance()
    private val devicesCollection = db.collection("devices")

    fun getAllDevices(
        onSuccess: (List<Device>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        devicesCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val devicesList = mutableListOf<Device>()
                for (document in querySnapshot.documents) {
                    val device = document.toObject(Device::class.java)
                    device?.let {
                        devicesList.add(it)
                    }
                }
                onSuccess(devicesList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getAllDeviceTypes(
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        devicesCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val deviceTypesSet = mutableSetOf<String>()
                for (document in querySnapshot.documents) {
                    val deviceType = document.getString("device_type")
                    deviceType?.let {
                        deviceTypesSet.add(it)
                    }
                }
                onSuccess(deviceTypesSet.toList())
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getDeviceBrandsByType(
        deviceType: String,
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        devicesCollection.whereEqualTo("device_type", deviceType)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val deviceBrandsSet = mutableSetOf<String>()
                for (document in querySnapshot.documents) {
                    val deviceBrand = document.getString("device_brand")
                    deviceBrand?.let {
                        deviceBrandsSet.add(it)
                    }
                }
                onSuccess(deviceBrandsSet.toList())
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }


    fun getDevicesByTypeAndBrand(
        deviceType: String,
        deviceBrand: String,
        onSuccess: (List<Device>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        devicesCollection.whereEqualTo("device_type", deviceType)
            .whereEqualTo("device_brand", deviceBrand)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val devicesList = mutableListOf<Device>()
                for (document in querySnapshot.documents) {
                    val device = document.toObject(Device::class.java)
                    device?.let {
                        devicesList.add(it)
                    }
                }
                onSuccess(devicesList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}