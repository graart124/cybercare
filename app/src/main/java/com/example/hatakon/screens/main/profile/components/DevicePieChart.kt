package com.example.hatakon.screens.main.profile.components

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatakon.core.data.model.Device
import com.example.hatakon.core.data.model.SecureType
import com.example.hatakon.core.data.model.getColor
import com.example.hatakon.ui.theme.NoInfoColor
import com.example.hatakon.ui.theme.NonSecureColor
import com.example.hatakon.ui.theme.PurpleDark
import com.example.hatakon.ui.theme.SecureColor

@Composable
fun DevicePieChart(recentDevicesChecks: List<Device>, selectedSecureType: SecureType?) {

    val secureCount = recentDevicesChecks.count { it.getSecureType() == SecureType.Secure }
    val notSecureCount = recentDevicesChecks.count { it.getSecureType() == SecureType.NotSecure }
    val notEnoughInfoCount = recentDevicesChecks.count { it.getSecureType() == SecureType.NoEnoughInfo }

    val totalCount = recentDevicesChecks.size
    val text:String = when(selectedSecureType) {
        SecureType.Secure -> secureCount.toString()
        SecureType.NotSecure -> notSecureCount.toString()
        SecureType.NoEnoughInfo -> notEnoughInfoCount.toString()
        null -> ""
    }

    val textColor = selectedSecureType?.getColor()?: Color.White

    Canvas(modifier = Modifier.size(150.dp)) {
        val radius = size.minDimension / 2

        val startAngle = 0f
        val secureSweepAngle = 360f * (secureCount.toFloat() / totalCount)
        val notSecureSweepAngle = 360f * (notSecureCount.toFloat() / totalCount)
        val notEnoughInfoSweepAngle = 360f * (notEnoughInfoCount.toFloat() / totalCount)

        drawArc(
            color = SecureColor,
            startAngle = startAngle,
            sweepAngle = secureSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawArc(
            color = NonSecureColor,
            startAngle = startAngle + secureSweepAngle,
            sweepAngle = notSecureSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawArc(
            color = NoInfoColor,
            startAngle = startAngle + secureSweepAngle + notSecureSweepAngle,
            sweepAngle = notEnoughInfoSweepAngle,
            useCenter = true,
            topLeft = Offset.Zero,
            size = Size(radius * 2, radius * 2)
        )

        drawCircle(
            color = PurpleDark,
            radius = radius - 10.dp.toPx(),
            center = Offset(radius, radius)
        )


        drawIntoCanvas { canvas ->
            val textPaint = Paint().asFrameworkPaint().apply {
                color = textColor.toArgb()
                textSize = 24.sp.toPx()
            }
            val textBounds = Rect()
            textPaint.getTextBounds(text, 0, text.length, textBounds)
            canvas.nativeCanvas.drawText(
                text,
                radius - textBounds.exactCenterX(),
                radius - textBounds.exactCenterY(),
                textPaint
            )
        }
    }
}
