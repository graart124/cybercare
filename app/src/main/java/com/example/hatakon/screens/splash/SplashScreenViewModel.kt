package com.example.hatakon.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hatakon.features.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _isUserAuthorized = mutableStateOf(false)
    val isUserAuthorized: State<Boolean> = _isUserAuthorized

    init {
        repository.saveUserData(userId = "1", email = "1asfds@gmail.com", photoUrl = null, name = "cotuk3")
        _isUserAuthorized.value = repository.getUserData()!=null
    }

}