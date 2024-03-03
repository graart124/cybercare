package com.example.hatakon.core.data.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import com.example.hatakon.ui.theme.NoInfoColor
import com.example.hatakon.ui.theme.NonSecureColor
import com.example.hatakon.ui.theme.SecureColor
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "devices")
data class Device(
    @PrimaryKey
    val id: String,
    val deviceBrand: String? = null,
    val deviceModel: String? = null,
    val deviceType: String? = null,
    val deviceInfoLink: String? = null,
    val deviceSecurity: Boolean? = null,
    val imageLink: String? = null,
    val ghz: String? = null,
    val wifi: Boolean? = null,
    val privacyShutter: Boolean? = null,
    val comments: String? = null
): Parcelable {
    companion object {
        fun fromDocument(document: DocumentSnapshot): Device {
            return Device(
                id = document.id,
                deviceBrand = document.getString("device_brand"),
                deviceModel = document.getString("device_model"),
                deviceType = document.getString("device_type"),
                deviceInfoLink = document.getString("device_info_link"),
                deviceSecurity = document.getBoolean("device_security"),
                imageLink = document.getString("image_link"),
                ghz = document.getString("ghz"),
                wifi = document.getBoolean("wifi"),
                privacyShutter = document.getBoolean("privacy_shutter"),
                comments = document.getString("comments")
            )
        }
    }

    fun getDeviceTypeEnum():DeviceType{
        return if(deviceType == "Video Baby Monitor"){
            DeviceType.Video
        }else{
            DeviceType.Audio
        }
    }

    fun getSecureType(): SecureType {
        return when(deviceSecurity){
            true -> SecureType.Secure
            false -> SecureType.NotSecure
            null -> SecureType.NoEnoughInfo
        }
    }
}

enum class DeviceType(){
    Video,
    Audio
}

enum class SecureType(val displayName:String){
    Secure("Secure"),
    NotSecure("Not secure"),
    NoEnoughInfo("Not enough info")
}

fun SecureType.getColor(): Color {
    return when(this){
        SecureType.Secure -> SecureColor
        SecureType.NotSecure -> NonSecureColor
        SecureType.NoEnoughInfo -> NoInfoColor
    }
}
