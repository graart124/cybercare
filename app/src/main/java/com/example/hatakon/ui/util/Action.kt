package com.example.hatakon.ui.util


data class Action(
    val info: String? = null,
    val success: Boolean? = null
)

data class ActionWithData<T>(
    val info: String? = null,
    val success: Boolean? = null,
    val data: T? = null
)