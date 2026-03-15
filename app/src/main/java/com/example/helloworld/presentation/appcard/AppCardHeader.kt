package com.example.helloworld.presentation.appcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.R
import com.example.helloworld.presentation.theme.VkEducationTheme

@Composable
fun AppCardHeader(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(Color.Blue)
        .padding(
            start = 15.dp,
            end = 15.dp),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            null,
            modifier = Modifier
                .size(115.dp)
        )
        Image(
            painter = painterResource(R.drawable.view_cozy),
            null,
            modifier = Modifier
                .size(30.dp)
//                .background(RuStoreBlue)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme() {
        AppCardHeader()
    }
}
