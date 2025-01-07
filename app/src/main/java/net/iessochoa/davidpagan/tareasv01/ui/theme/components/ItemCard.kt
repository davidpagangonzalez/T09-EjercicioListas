package net.iessochoa.davidpagan.tareasv01.ui.theme.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text


@Composable
fun ItemCard(
    image: Painter, icono: ImageVector, tipo: String, mecanico: String, descripcion: String,
    prioridad: String,
    modifier: Modifier = Modifier) {
    val color = when(prioridad) {
        "2" -> Color.Red
        else -> Color.White
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(color)
        ) {
        Row{
            Image(
                painter = image,
                contentDescription = "Prueba",
                modifier = Modifier
                    .weight(1F)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(3F).background(color)) {
                Row {
                    Icon(
                        imageVector = icono,
                        contentDescription = "Estado de la tarea",
                        modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically)
                    )
                    Text(
                        text = tipo,
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 15.sp
                    )
                }
                Text(
                    text = mecanico,
                    modifier = Modifier.padding(5.dp,0.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 20.sp
                )
                Text(
                    text = descripcion,
                    maxLines = 2,
                    lineHeight = 1.2.em,
                    modifier = Modifier.padding(5.dp,5.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 15.sp
                )
            }
        }
    }
}
