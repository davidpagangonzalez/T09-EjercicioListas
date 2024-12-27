package net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.data.db.entities.Tarea
import net.iessochoa.davidpagan.tareasv01.data.repository.Repository
import net.iessochoa.davidpagan.tareasv01.ui.theme.theme.utils.ColorPrioridadAlta

class TareaViewModel(application: Application): AndroidViewModel(application){

    private val context = application.applicationContext

    val listaCategoria = context.resources.getStringArray(R.array.categoria).toList()

    val listaEstado = context.resources.getStringArray(R.array.estados).toList()

    val listaPrioridad = context.resources.getStringArray(R.array.prioridades).toList()

    val PRIORIDADALTA = listaPrioridad[2]

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

    fun onValueChangeEstado(nuevoEstado: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy( estadoTarea = nuevoEstado)
    }

    fun onValueChangeCategoria(nuevaCategoria: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(categoria = nuevaCategoria)
    }

    fun onValueChangePagado(nuevoPagado: Boolean) {
        _uiStateTarea.value = _uiStateTarea.value.copy(isPaid = nuevoPagado)
    }

    fun onValueChangeValoracion(nuevaValoracion: Int) {
        _uiStateTarea.value = _uiStateTarea.value.copy(rating = nuevaValoracion)
    }

    fun onTecnicoValueChange(nuevoTecnico: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            technicianName = nuevoTecnico,
            esFormularioValido = nuevoTecnico.isNotBlank() && _uiStateTarea.value.technicianName.isNotBlank())
    }

    fun onDescripcionValueChange(nuevaDescripcion: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            description = nuevaDescripcion,
            esFormularioValido = nuevaDescripcion.isNotBlank() && _uiStateTarea.value.description.isNotBlank())
    }

    //muestra el dialogo
    fun onGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = true
        )
    }

    //guardará los cambios, por el momento solo cierra el dialogo
    fun onConfirmarDialogoGuardar() {
        guardarTarea()
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }

    //cierra el dialogo
    fun onCancelarDialogoGuardar() {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }

    fun getTarea(id: Long) {
        tarea = Repository.getTarea(id)
//si no es nueva inicia la UI con los valores de la tarea
        if (tarea != null) tareaToUiState(tarea!!)
    }

    fun guardarTarea() {
        val tarea = uiStateToTarea()
        if (uiStateTarea.value.esTareaNueva){
            Repository.addTarea(tarea)
        }else{
            Repository.addTarea(tarea)
        }
    }

    //tarea
    var tarea: Tarea.Tarea? = null
    /**
     *Carga los datos de la tarea en UiState,
     * que a su vez actualiza la interfaz de usuario *
     */
    fun tareaToUiState(tarea: Tarea.Tarea) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            categoria = listaCategoria[tarea.categoria],
            prioridad = listaPrioridad[tarea.prioridad],
            isPaid = tarea.pagado,
            estadoTarea = listaEstado[tarea.estado],
            rating = tarea.valoracionCliente,
            technicianName = tarea.tecnico,
            description = tarea.descripcion,
            esFormularioValido = tarea.tecnico.isNotBlank() &&
                    tarea.descripcion.isNotBlank(),
            esTareaNueva = false,
            colorFondo = if (PRIORIDADALTA == listaPrioridad[tarea.prioridad])
                ColorPrioridadAlta else Color.Transparent
        )
    }
    /**
     * Extrae los datos de la interfaz de usuario y los convierte en un objeto Tarea.
     */
    fun uiStateToTarea(): Tarea.Tarea {
        return if (uiStateTarea.value.esTareaNueva)
        //si es nueva, le asigna un id
            Tarea.Tarea(
                categoria = listaCategoria.indexOf(uiStateTarea.value.categoria),
                prioridad = listaPrioridad.indexOf(uiStateTarea.value.prioridad),
                img = R.drawable.ic_launcher_foreground.toString(),
                pagado = uiStateTarea.value.isPaid,
                estado = listaEstado.indexOf(uiStateTarea.value.estadoTarea),
                valoracionCliente = uiStateTarea.value.rating,
                tecnico = uiStateTarea.value.technicianName,
                descripcion = uiStateTarea.value.description
            ) //si no es nueva, actualiza la tarea
        else Tarea.Tarea(
            tarea!!.id,
            categoria = listaCategoria.indexOf(uiStateTarea.value.categoria),
            prioridad = listaPrioridad.indexOf(uiStateTarea.value.prioridad),
            img = tarea!!.img,
            pagado = uiStateTarea.value.isPaid,
            estado = listaEstado.indexOf(uiStateTarea.value.estadoTarea),
            valoracionCliente = uiStateTarea.value.rating,
            tecnico = uiStateTarea.value.technicianName,
            descripcion = uiStateTarea.value.description
        )
    }
}
