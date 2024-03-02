package com.example.hatakon.screens.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hatakon.R
import com.example.hatakon.screens.destinations.SelectDeviceScreenDestination
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.Primary
import com.example.hatakon.ui.theme.PurpleLight
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel(),
) {


    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data?.let { intent ->
            viewModel.processGoogleSignInResult(intent)
        }
    }
    val loginResult by viewModel.loginResult.collectAsState()

    val webClientId = stringResource(id = R.string.web_client_id)

    LaunchedEffect(loginResult) {
        loginResult?.let { success ->
            if (success) {
                navigator.popBackStack()
                navigator.navigate(SelectDeviceScreenDestination)
            } else {
                snackbarHostState.showSnackbar("Error log in\nPlease, try again")
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it.calculateTopPadding()),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_log_in_assistant),
                contentDescription = "log in",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(horizontal = 22.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = PurpleLight.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(horizontal = 22.dp, vertical = 18.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = AppTextStyle.RobotoBold.sp14.copy(
                            color = Color.White,
                            lineHeight = TextUnit(value = 24f, type = TextUnitType.Sp)
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                GoogleButton(
                    onClick = {
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestServerAuthCode(webClientId, true)
                            .requestEmail()
                            .requestProfile()
                            .build()
                        val gsc = GoogleSignIn.getClient(context, gso)

                        launcher.launch(gsc.signInIntent)
                    }
                )

            }
        }
    }

}

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary
        ),
        shape = RoundedCornerShape(7.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(6.dp))
                    .padding(2.dp)
                    .height(40.dp)
                    .width(40.dp),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    painter = painterResource(id = R.drawable.ic_log_in_google_icon),
                    contentDescription = "google icon"
                )
            }
            Spacer(modifier = Modifier.width(120.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                //contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Log in",
                    style = AppTextStyle.RobotoBold.sp14
                )
            }
        }
    }
}

