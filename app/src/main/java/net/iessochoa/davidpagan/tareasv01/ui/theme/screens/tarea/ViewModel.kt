package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.ColorPrioridadAlta



class TareaViewModel(application: Application): AndroidViewModel(application){

    private val context = application.applicationContext

    val listaPrioridad = context.resources.getStringArray(R.array.prioridades).toList()
    val PRIORIDADALTA = listaPrioridad[0]

    private val _uiStateTarea = MutableStateFlow(UiStateTarea(prioridad = listaPrioridad[0]))
    val uiStateTarea: StateFlow<UiStateTarea> = _uiStateTarea.asStateFlow()

    fun OnValueChangedPrioridad(nuevaPrioridad : String){
        val colorFondo : Color
        if (PRIORIDADALTA == nuevaPrioridad)
            colorFondo = ColorPrioridadAlta
        else
            colorFondo = Color.Transparent

        _uiStateTarea.value = _uiStateTarea.value.copy(
            prioridad = nuevaPrioridad, colorFondo = colorFondo)
    }

    fun onTecnicoValueChange(nuevoTecnico: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            technicianName = nuevoTecnico,
            esFormularioValido = nuevoTecnico.isNotBlank() && _uiStateTarea.value.description.isNotBlank())

    }

    fun onDescripcionValueChange(nuevaDescripcion: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            description = nuevaDescripcion,
            esFormularioValido = nuevaDescripcion.isNotBlank() && _uiStateTarea.value.description.isNotBlank())
    }




}