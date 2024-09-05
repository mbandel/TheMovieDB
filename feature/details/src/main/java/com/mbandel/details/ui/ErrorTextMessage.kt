package com.mbandel.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource

import androidx.compose.ui.unit.dp
import com.mbandel.details.R

@Composable
fun ErrorTextMessage(message: String) {
    Column {
        Text(
            text = message,
            color = colorResource(R.color.red)
        )
        Spacer(modifier = Modifier.height(28.dp))
    }
}
