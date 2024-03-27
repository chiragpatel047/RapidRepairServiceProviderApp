package com.chirag047.rapidservice.Common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SubjectImage(image: Int,modifier: Modifier) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = modifier
    )
}