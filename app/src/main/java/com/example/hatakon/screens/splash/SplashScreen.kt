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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hatakon.R
import com.example.hatakon.screens.destinations.LoginScreenDestination
import com.example.hatakon.screens.destinations.SplashScreenDestination
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.Primary
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
            //todo navigate main screen
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "welcome",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(34.dp))
        Box(
            modifier = Modifier
                .background(color = Primary, shape = RoundedCornerShape(7.dp))
                .padding(horizontal = 10.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "",//stringResource(R.string.welcome_anita),
                style = AppTextStyle.RobotoRegular.sp36.copy(color = Color.White),
            )
        }
    }
}
