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


}