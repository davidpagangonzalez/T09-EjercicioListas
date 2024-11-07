package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.davidpagan.tareasv01.R



class TareaViewModel(application: Application): AndroidViewModel(application){

    private val context = application.applicationContext

    val listaPrioridad = context.resources.getStringArray(R.array.prioridades).toList()
    val PRIORIDADALTA = listaPrioridad[0]

    private val _uiState = MutableStateFlow(UiState(prioridad = listaPrioridad[0]))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

}