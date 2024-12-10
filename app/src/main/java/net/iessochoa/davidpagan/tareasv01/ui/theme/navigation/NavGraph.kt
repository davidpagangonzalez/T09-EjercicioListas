package net.iessochoa.davidpagan.tareasv01.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import net.iessochoa.davidpagan.tareasv01.ui.theme.screens.listatareas.ListaTareasScreen
import net.iessochoa.davidpagan.tareasv01.ui.theme.screens.tarea.TaskScreen

@Composable
fun AppNavigationx(){
    val navController = rememberNavController()
NavHost(
    navController = navController,
    startDestination = ListaTareasDestino
){
    composable<ListaTareasDestino> {
        ListaTareasScreen(
            //Navegamos a la pantalla Palabra editanto una tarea existente.
            //Pasamos la posición de la tarea en la lista
            onItemModificarClick = { posTarea ->
                navController.navigate(TareaDestino(posTarea.toLong()))
            },
            onClickNueva = {
                navController.navigate(TareaDestino(null))
            }
        ) }

    composable<TareaDestino> { backStackEntry ->
        //recuperamos el parámetro posPalabra de la navegación
        val tarea: TareaDestino = backStackEntry.toRoute()
        if (tarea.posTarea != null) {
            TaskScreen(
                idTarea = tarea.posTarea,
                //pasamos la lambda para volver a la pantalla anterior
                onVolver = {
                    navController.navigateUp()
                },
                //pasamos la lambda para navegar a la pantalla de vista palabra si no es nueva
            )
        }
        else
            TaskScreen(
                idTarea = null,
                //pasamos la lambda para volver a la pantalla anterior
                onVolver = {
                    navController.navigateUp()
                }
            )
    }
}
}

