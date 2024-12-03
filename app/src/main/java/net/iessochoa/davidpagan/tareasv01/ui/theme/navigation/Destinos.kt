package net.iessochoa.davidpagan.tareasv01.ui.theme.navigation

import kotlinx.serialization.Serializable

//Pantallas:
// Definimos las pantallas que queremos navegar donde  las llamamos mediante los siguientes
// elementos Serializable

// ListaPalabrasScreen: Pantalla inicial de la app que muestra la lista de palabras
//la definimos como object porque no tiene parámetros
@Serializable
object ListaTareasDestino

//las Pantallas con parámetros se definen como data class
@Serializable
data class NuevaTareaScreenDestination(val posTarea: Int? = null)

@Serializable
data class TareaScreenDestination(val posTarea: Int)