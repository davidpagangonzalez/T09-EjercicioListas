package net.iessochoa.davidpagan.tareasv01.data.tempmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import net.iessochoa.davidpagan.tareasv01.R
import net.iessochoa.davidpagan.tareasv01.data.db.entities.Tarea.Tarea
import kotlin.random.Random

object TempModelTareas {

    //lista de tareas
    private val listaTareas = ArrayList<Tarea>()
    //StateFlow observable
    private val _tareasStateFlow =
        MutableStateFlow<List<Tarea>>(listaTareas)
    /**
     * devuelve un Flow como sucede en la base de datos
     */
    fun getAllTareas(): Flow<List<Tarea>> {
        return _tareasStateFlow
    }
    /**
     * añade una tarea, si existe (id iguales) la sustituye
     * y si no la añade.
     */
    fun addTarea(tarea: Tarea) {
        val pos = listaTareas.indexOf(tarea)
        if (pos < 0) {//si no existe
            listaTareas.add(tarea)
        } else {
            //si existe se sustituye
            listaTareas.set(pos, tarea)
        }
        //actualizamos el StateFlow
        _tareasStateFlow.value = listaTareas
    }
    fun delTarea(tarea: Tarea) {
        listaTareas.remove(tarea)
        _tareasStateFlow.value = listaTareas
    }
    //obtiene una tarea a partir de su id
    fun getTarea(id: Long): Tarea? {
        return listaTareas.find { it.id == id +1 }
    }
    /**
     * Crea unas Tareas de prueba de forma aleatoria.
     */
    fun iniciaPruebaTareas() {
        val tecnicos = listOf(
            "Pepe Gotero",
            "Sacarino Pómez",
            "Mortadelo Fernández",
            "Filemón López",
            "Zipi Climent",
            "Zape Gómez",
            "Pepito Grillo"
        )
        val fotos=
            listOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)
        lateinit var tarea: Tarea
        (0..10).forEach {
            tarea = Tarea(
                (0..4).random(),
                (0..2).random(),
                fotos.random().toString(),
                Random.nextBoolean(),
                (0..2).random(),
                (0..5).random(),
                tecnicos.random(),
                "tarea $it : Lorem cosa cosa cosa pero mucho se dise jajaja ipsum IPS ALONG LOLOL XD "
            )
            listaTareas.add(tarea)
        }
        _tareasStateFlow.value = listaTareas
    }
    //Permite iniciar el objeto Singleton
    operator fun invoke(){
        //this.application= context.applicationContext as Application
        iniciaPruebaTareas()
    }
}