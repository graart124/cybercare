package com.example.hatakon.screens.main.device_info_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hatakon.ui.theme.PurpleDark

@Composable
fun DeviceImage(
    imageLink:String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PurpleDark, shape = RoundedCornerShape(8.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            model = imageLink,
            contentDescription = "device photo",
            contentScale = ContentScale.FillWidth
        )
    }
}