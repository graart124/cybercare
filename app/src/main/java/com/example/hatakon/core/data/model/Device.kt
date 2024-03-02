package com.example.hatakon.core.data.model

data class Device(
    val id: Int? = null,
    val deviceBrand: String? = null,
    val deviceModel: String? = null,
    val deviceType: String? = null,
    val deviceInfoLink: String? = null,
    val deviceSecurity: String? = null,
    val ghz: String? = null,
    val wifi: Boolean? = null,
    val privacyShutter: Boolean? = null,
    val comments: String? = null
)

