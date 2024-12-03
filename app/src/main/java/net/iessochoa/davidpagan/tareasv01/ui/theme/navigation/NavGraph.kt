package net.iessochoa.davidpagan.tareasv01.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iessochoa.davidpagan.tareasv01.ui.theme.screens.listatareas.ListaTareasScreen
import net.iessochoa.davidpagan.tareasv01.ui.theme.screens.listatareas.ListaTareasViewModel

@Composable
fun AppNavigationx(){
    val navController = rememberNavController()
NavHost(
    navController = navController,
    startDestination = ListaTareasDestino
){
    composable<ListaTareasDestino> {
ListaTareasScreen(
    onClickNueva = {
        // Navegamos a la pantalla TareaScreen. Pasamos null porque es una nueva Tarea
        navController.navigate(NuevaTareaScreenDestination())
    },
    //Navegamos a la pantalla Palabra editanto una tarea existente.
    // Pasamos la posición de la tarea en la lista
    onItemModificarClick = { posTarea ->
        navController.navigate(TareaScreenDestination(posTarea))
    }

) }
}
}

