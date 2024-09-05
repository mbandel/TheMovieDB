package com.mbandel.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator() {
    Box(modifier = Modifier.width(48.dp)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
