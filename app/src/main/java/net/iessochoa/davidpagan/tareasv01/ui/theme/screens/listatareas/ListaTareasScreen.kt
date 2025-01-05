package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.listatareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.AppBar
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.TareasV01Theme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.ItemCard

fun CategoriaRota(id: Int): String {
    return when (id) {
        0 -> "Reparacion"
        1 -> "Instalacion"
        2 -> "Mantenimiento"
        3 -> "Comercial"
        4 -> "Otros"
        else -> "Categoría desconocida" // Manejo de caso por defecto
    }
}

fun EstadosRoto(id: Int): ImageVector {
    return when (id) {
        0 -> Icons.Default.FavoriteBorder
        1 -> Icons.Default.DateRange
        2 -> Icons.Default.Lock
        else -> Icons.Default.Lock
    }
}

@Composable
fun ListaTareasScreen(
    viewModel: ListaTareasViewModel = viewModel(),
    onClickNueva: () -> Unit = {},
    onItemModificarClick: (pos: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val uiStateLista by viewModel.listaTareasUiState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                //muestra el título de la pantalla
                tituloPantallaActual = "Tareas",
                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                //navega a la pantalla de PalabraScreen para añadir una nueva palabra
                onClick = onClickNueva
            ) {
                Icon(Icons.Filled.Add, "Nueva Tarea")
            }
        }
    ) { padding ->
        Column (
            modifier = modifier
            .fillMaxSize()
            .padding(padding))
        {
            LazyColumn(modifier =
            Modifier
                .fillMaxSize()
                .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            )
            {
                items(uiStateLista.listaTareas) { item ->
                    ItemCard(
                        image = painterResource(id = R.drawable.ic_launcher_background),
                        icono = EstadosRoto(item.estado),
                        tipo = CategoriaRota(item.categoria),
                        mecanico = item.tecnico,
                        descripcion = item.descripcion,
                        prioridad = item.prioridad.toString(),
                        modifier = Modifier.clickable{
                            item.id?.let { onItemModificarClick(it.toInt()) }
                        }
                    )
                }
            }
        }

        /*
        Column(modifier = modifier.fillMaxSize().padding(padding)) {
            uiStateLista.listaTareas.forEachIndexed { index, tarea ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = tarea.id.toString() + " - " + tarea.prioridad + " - " + tarea.categoria + " " + tarea.tecnico     ,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            //navegamos a la pantalla de PalabraScreen para modificar la tarea
                            .clickable {
                                onItemModificarClick(index)
                            }
                    )
                }
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }
        }

         */
    }
}

@Preview(showBackground = true)
@Composable
fun ListaPalabrasScreenPreview() {
    TareasV01Theme {
        ListaTareasScreen(
            onClickNueva = {})
    }
}
