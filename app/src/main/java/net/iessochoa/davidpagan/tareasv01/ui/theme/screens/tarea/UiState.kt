package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import android.graphics.Color

data class UiStateTarea(
    val categoria: String = "",
    val prioridad: String = "",
    val isPaid: Boolean = false,
    val estadoTarea: String = "",
    val rating: Int = 3,
    val technicianName: String = "",
    val description: String = "",
    val colorFondo: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Transparent,
    val esFormularioValido: Boolean = false,
    val mostrarDialogo: Boolean = false,
    val esTareaNueva: Boolean = true


)



