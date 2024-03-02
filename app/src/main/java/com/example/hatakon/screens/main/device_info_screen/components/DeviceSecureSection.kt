package com.example.hatakon.screens.main.device_info_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hatakon.R
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.NoInfoColor
import com.example.hatakon.ui.theme.NonSecureColor
import com.example.hatakon.ui.theme.PurpleDark
import com.example.hatakon.ui.theme.SecureColor


@Composable
fun DeviceSecureSection(
    deviceSecurity: Boolean?
) {
    var iconId: Int = R.drawable.ic_lock
    var textInfo = stringResource(R.string.check_complited_your_device_is_not_secure)
    var mainColor: Color = SecureColor

    when (deviceSecurity) {
        true -> {
            iconId = R.drawable.ic_lock
            textInfo = stringResource(R.string.check_complited_your_device_is_secure)
            mainColor = SecureColor
        }

        false -> {
            iconId = R.drawable.ic_unlock
            textInfo = stringResource(R.string.check_complited_your_device_is_not_secure)
            mainColor = NonSecureColor
        }

        null -> {
            iconId = R.drawable.ic_no_info
            textInfo =
                stringResource(R.string.check_complited_we_do_not_have_enough_information_about_the_device)
            mainColor = NoInfoColor
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = iconId), contentDescription = "icon")
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = textInfo, color = mainColor, style = AppTextStyle.RobotoSemiBold.sp20)
    }
}