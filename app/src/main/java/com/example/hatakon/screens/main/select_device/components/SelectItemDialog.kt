package com.example.hatakon.screens.main.select_device.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.hatakon.ui.theme.AppTextStyle
import com.example.hatakon.ui.theme.Background
import com.example.hatakon.ui.theme.NavBarColor
import com.example.hatakon.ui.theme.Primary
import com.example.hatakon.ui.theme.PurpleDark

@Composable
fun SelectItemDialog(
    showDialog: Boolean,
    onDismissClick: () -> Unit,
    onChooseItem: ((Int) -> Unit),
    items: List<String>,
    selectedItemId: Int? = null,
    text: String
) {

    if (!showDialog) return

    Dialog(
        onDismissRequest = onDismissClick,
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(5.dp, color = NavBarColor),
            color = Background,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier=Modifier.padding(16.dp)) {
                Text(
                    text = text,
                    style = AppTextStyle.RobotoBold.sp14,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(items) { index, item ->
                        ItemView(
                            item = item,
                            selected = selectedItemId == index,
                            onCliCk = {
                                onChooseItem(index)
                                onDismissClick()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemView(
    item: String,
    selected: Boolean,
    onCliCk: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PurpleDark, shape = RoundedCornerShape(size = 8.dp))
            .clickable {
                onCliCk()
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemRadioButton(selected = selected)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item, style = AppTextStyle.RobotoBold.sp14, color = Color.White)
    }
}

@Composable
fun ItemRadioButton(
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .background(color = Color.White, shape = CircleShape)
            .border(width = 1.dp, color = Primary, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color = Primary, shape = CircleShape)
            )
        }
    }
}