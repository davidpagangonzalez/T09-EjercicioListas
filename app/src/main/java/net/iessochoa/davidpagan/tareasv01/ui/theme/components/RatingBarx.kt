package net.iessochoa.davidpagan.tareasv01.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(maxRating: Int, currentRating: Int, onRatingChanged: (Int) -> Unit) {
    Row {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i) }) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                    tint = if (i <= currentRating) Color.Blue else Color.Gray
                )
            }
        }
    }
}