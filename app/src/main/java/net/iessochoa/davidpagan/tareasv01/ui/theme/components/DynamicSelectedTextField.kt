package net.iessochoa.davidpagan.tareasv01.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

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