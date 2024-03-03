package com.example.hatakon.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hatakon.R
import com.example.hatakon.screens.destinations.LoginScreenDestination
import com.example.hatakon.screens.destinations.SelectDeviceScreenDestination
import com.example.hatakon.screens.destinations.SplashScreenDestination
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.NavBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay


@RootNavGraph(start = true)
@ExperimentalMaterialApi
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val isUserAuthorized = viewModel.isUserAuthorized

    LaunchedEffect(Unit) {
        delay(1500L)
        navigator.popBackStack(SplashScreenDestination.route, true, saveState = true)
        if (!isUserAuthorized.value) {
            navigator.navigate(LoginScreenDestination())
        } else{
            navigator.navigate(SelectDeviceScreenDestination)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NavBarColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "welcome",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(100.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(320.dp)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = stringResource(R.string.take_care_of_the_security_of_your_devices),
                style = AppTextStyle.RobotoRegular.sp24.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }
    }
}
