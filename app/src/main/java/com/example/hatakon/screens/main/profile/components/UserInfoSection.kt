package com.example.hatakon.screens.main.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hatakon.core.data.model.UserData
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.PurpleDark

@Composable
fun UserInfoSection(
    user: UserData?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 8.dp)
            .background(color = PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                model = user?.photoUrl,
                contentDescription = "user photo",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(34.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = user?.displayName ?: "",
                style = AppTextStyle.RobotoSemiBold.sp20.copy(color = Color.White),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = user?.email ?: "",
                style = AppTextStyle.RobotoRegular.sp16.copy(color = Color.White),
            )
        }
    }
}