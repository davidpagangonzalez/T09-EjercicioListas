package net.iessochoa.davidpagan.tareasv01.ui.theme.navigation

import kotlinx.serialization.Serializable

//Pantallas:
// Definimos las pantallas que queremos navegar donde  las llamamos mediante los siguientes
// elementos Serializable

// ListaTareasScreen: Pantalla inicial de la app que muestra la lista de palabras
//la definimos como object porque no tiene parámetros

@Serializable
object ListaTareasDestino

@Serializable
data class TareaDestino(val posTarea: Long? = null)
