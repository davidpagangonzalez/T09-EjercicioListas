package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // Para manejar el espacio
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.ui.theme.ColorPrioridadAlta
import net.iessochoa.davidpagan.tareasv01.ui.theme.TareasV01Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareasV01Theme {
                TaskScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    selectedValue: String,
    options: Array<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //Permite saber si el menú desplegable está abierto. Inicialmente está cerrado
    var expanded by remember { mutableStateOf(false) }
    //Muestra el menu desplegable
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {//campo TextField que muestra el elemento seleccionado
        OutlinedTextField(
            //solo lectura
            readOnly = true,
            //el valor actual seleccionado
            value = selectedValue,
            //lambda vacia para evitar cambios
            onValueChange = {},
            //etiqueta que describe el contenido
            label = { Text(text = label) },
            //icono de la flecha, que muestra el menú desplegable
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        //representa el menú desplegable en sí
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false
        }) {
            //recorre la lista de opciones y añade la opción al menú desplegable
            options.forEach { option: String ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}
@Composable
fun RowRadioButtonCompose(
    listaOpciones: Array<String>,
    opcionSeleccionada: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listaOpciones.forEach { operation ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .selectable(
                        selected = opcionSeleccionada == operation,
                        onClick = { onOptionSelected(operation) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = opcionSeleccionada == operation,
                    onClick = null
                )
                Text(text = operation,
                    style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}


@Composable
fun EditOutlinedTextField(
    label: String,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        modifier = modifier,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun TaskScreen() {

    var selectedCategory by remember { mutableStateOf("Reparacion") }
    var selectedPriority by remember { mutableStateOf("Baja") }
    var isPaid by remember { mutableStateOf(false) }
    var taskStatus by remember { mutableStateOf("Abierta") }
    var rating by remember { mutableStateOf(3) } // Valoración de cliente
    var technicianName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var colorTarea = if (selectedPriority == "Alta") ColorPrioridadAlta else Color.White
    var listaCategoria = stringArrayResource(id = R.array.categoria).toList()
    var listaPrioridad = stringArrayResource(id = R.array.prioridades).toList()


    var iconEstadoTarea =
        when (taskStatus) {
            stringResource(R.string.abierta) -> Icons.Default.ThumbUp
            stringResource(R.string.en_curso) -> Icons.Default.ShoppingCart
            else -> Icons.Default.Lock
        }

    var icon =
    when {
        isPaid -> Icons.Default.Check
        else -> Icons.Default.Clear
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorTarea)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Column (modifier = Modifier.weight(1F)){
                // Categoría
                DynamicSelectTextField(
                    selectedValue = selectedCategory,
                    options = stringArrayResource(id = R.array.categoria),
                    label = stringResource(R.string.categor_a),
                    onValueChangedEvent = { selectedCategory = it }
                )
                // Prioridad
                DynamicSelectTextField(
                    selectedValue = selectedPriority,
                    options = stringArrayResource(id = R.array.prioridades),
                    label = stringResource(R.string.prioridad),
                    onValueChangedEvent = { selectedPriority = it }
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
                modifier = Modifier.size(24.dp)
            )
            Text(stringResource(R.string.est_pagado))
            Switch(checked = isPaid, onCheckedChange = { isPaid = it })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.estado_de_la_tarea))
            Icon(
                imageVector = iconEstadoTarea,
                contentDescription = "Estado de la tarea",
                modifier = Modifier.size(24.dp)
            )
        }
        // Estado de la tarea
        RowRadioButtonCompose(
            listaOpciones = stringArrayResource(id = R.array.estados),
            opcionSeleccionada = taskStatus,
            onOptionSelected = { taskStatus = it }
        )

        // Valoración cliente
        Text(stringResource(R.string.valoraci_n_cliente))
        Row {
            for (i in 1..5) {
                IconButton(onClick = { rating = i }) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null,
                        tint = if (i <= rating) Color.Blue else Color.Gray
                    )
                }
            }
        }

        // Campo para técnico
        EditOutlinedTextField(
            label = stringResource(R.string.t_cnico),
            keyboardOptions = KeyboardOptions.Default,
            value = technicianName,
            onValueChanged = { technicianName = it },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo para descripción
        EditOutlinedTextField(
            label = stringResource(R.string.descripcion),
            keyboardOptions = KeyboardOptions.Default,
            value = technicianName,
            onValueChanged = { technicianName = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TareasV01Theme {
        TaskScreen()
    }
}
