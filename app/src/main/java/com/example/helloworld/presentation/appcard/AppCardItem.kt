package com.example.helloworld.presentation.appcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.example.helloworld.R
import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appcard.AppCard
import com.example.helloworld.presentation.theme.VkEducationTheme

@Composable
fun AppRow(app: AppCard, onGoForward: (String) -> Unit = {}) {
    Button (
        onClick = {onGoForward(app.id)},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 10.dp
        )
        ) {
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                model = app.iconUrl,
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(Modifier.width(10.dp))
            Column (
                verticalArrangement = Arrangement.spacedBy(0.1.dp)
            ) {
                Text(
                    app.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Text(
                    app.description,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Text(
                    app.category.value,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 10.sp
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun Preview(){
//    val app = AppCard(
//        "Яндекс.Браузер - с Алисой",
//        "Быстрый и безопасный браузер",
//        AppCategory.TOOLS,
//        R.drawable.yandex_brow
//    )
//    VkEducationTheme() {
//        AppRow(app)
//    }
//}