package com.example.hatakon.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.PurpleDark
import com.example.hatakon.ui.theme.UnselectedNavBarItemColor

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String?,
    hint: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(0.5.dp, UnselectedNavBarItemColor), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            value = value ?: "",
            modifier = Modifier.weight(1f),
            onValueChange = onValueChange,
            textStyle = AppTextStyle.RobotoRegular.sp18.copy(color = Color.Black),
            maxLines = 1,
            keyboardOptions = keyboardOptions?: KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (value.isNullOrEmpty()) {
                    Text(
                        text = hint,
                        style = AppTextStyle.RobotoRegular.sp18.copy(color = Color.Gray)
                    )
                }
                innerTextField()
            }
        )
    }
}