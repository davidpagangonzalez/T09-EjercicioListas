package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.DialogoDeConfirmacion
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.TareasV01Theme
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.DynamicSelectTextField
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.RowRadioButtonCompose
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.EditOutlinedTextField
import net.iessochoa.davidpagan.tareasv01.ui.theme.components.RatingBar


@Composable
fun TaskScreen(
    viewModel: TareaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.uiStateTarea.collectAsState()

    /*
    val listaPrioridad = stringArrayResource(id = R.array.prioridades).toList()
    var selectedPriority by remember { mutableStateOf(listaPrioridad[2]) }
    */

    val snackbarHostState = remember { SnackbarHostState() } // Estado para el Snackbar
    val scope = rememberCoroutineScope() // Scope para lanzar corutinas

    val listaCategoria = stringArrayResource(id = R.array.categoria).toList()
    var selectedCategory by remember { mutableStateOf(listaCategoria[0]) }

    var isPaid by remember { mutableStateOf(false) }

    val listaEstados = stringArrayResource(id = R.array.estados).toList()
    var taskStatus by remember { mutableStateOf(listaEstados[0]) }

    var rating by remember { mutableStateOf(3) } // Valoración de cliente

    var technicianName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    //val colorTarea = if (selectedPriority == listaPrioridad[0]) ColorPrioridadAlta else Color.White

    val imageVector = Icons.Default.Star
    val iconEstadoTarea =
        when (taskStatus) {
            listaEstados[0] -> Icons.Default.ThumbUp
            listaEstados[1] -> Icons.Default.ShoppingCart
            else -> Icons.Default.Lock
        }

    var icon =
        when {
            isPaid -> Icons.Default.Check
            else -> Icons.Default.Clear
        }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (uiState.esFormularioValido)
                    viewModel.onGuardar()
                else{
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Rellene todos los campos",
                            duration = SnackbarDuration.Short

                        )
                    }
                }
            }) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_menu_save),
                    contentDescription = "Guardar"
                )
            }
        }

    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),

            color = uiState.colorFondo

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(modifier = Modifier.weight(1F)) {
                            // Categoría
                            DynamicSelectTextField(
                                selectedValue = selectedCategory,
                                options = stringArrayResource(id = R.array.categoria).toList(),
                                label = stringResource(R.string.categor_a),
                                onValueChangedEvent = { selectedCategory = it }
                            )
                            // Prioridad
                            DynamicSelectTextField(
                                selectedValue = uiState.prioridad,
                                options = viewModel.listaPrioridad,
                                label = stringResource(R.string.prioridad),
                                onValueChangedEvent = { viewModel.OnValueChangedPrioridad(it) }
                            )
                        }
                        // Imagen superior
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background), // Tu imagen aquí
                            contentDescription = stringResource(R.string.imagen_a_cambiar),
                            modifier = Modifier
                                .height(150.dp)
                                .weight(1F)
                        )
                    }
                    // Estado de pago
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(R.string.estado_de_pago),

                            )
                        Text(stringResource(R.string.est_pagado))
                        Switch(checked = isPaid, onCheckedChange = { isPaid = it })
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(stringResource(R.string.estado_de_la_tarea))
                        Icon(
                            imageVector = iconEstadoTarea,
                            contentDescription = "Estado de la tarea",
                            )
                    }

                    // Estado de la tarea
                    RowRadioButtonCompose(
                        listaOpciones = stringArrayResource(id = R.array.estados),
                        opcionSeleccionada = taskStatus,
                        onOptionSelected = { taskStatus = it }
                    )

                    // Valoración de cliente
                    RatingBar(
                        maxRating = 5,
                        currentRating = rating,
                        onRatingChanged = { rating = it }
                    )
                    // Valoración cliente

                    Text(stringResource(R.string.valoraci_n_cliente))

                    // Campo para técnico
                    EditOutlinedTextField(
                        label = stringResource(R.string.t_cnico),
                        keyboardOptions = KeyboardOptions.Default,
                        value = technicianName,
                        onValueChanged = { viewModel.onTecnicoValueChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    // Campo para descripción
                    EditOutlinedTextField(
                        label = stringResource(R.string.descripcion),
                        keyboardOptions = KeyboardOptions.Default,
                        value = description,
                        onValueChanged = { viewModel.onDescripcionValueChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )

// Sección para la descripción con scroll vertical.
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    //Dialogo de confirmación
                    if (uiState.mostrarDialogo) {
                        DialogoDeConfirmacion(
                            onDismissRequest = {
                                //cancela el dialogo
                                viewModel.onCancelarDialogoGuardar()
                            },
                            onConfirmation = {
                                //guardaría los cambios
                                viewModel.onConfirmarDialogoGuardar()
                                scope.launch{
                                    snackbarHostState.showSnackbar(
                                        message = "Tarea guardada",
                                        duration = SnackbarDuration.Short
                                    )

                                }
                            },
                            dialogTitle = "Atención",
                            dialogText = "Desea guardar los cambios?",
                            icon = Icons.Default.Info
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TareasV01Theme {
        TaskScreen()
    }
}

