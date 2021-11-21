package com.albertoapps.listtodo.Src.Main

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.Data.Repositories.ListToDoRepository.ListToDoRepository
import kotlinx.coroutines.launch

class ViewModelMainActivity (
    context: Context
): ViewModel() {

    //Repositorios
    val repository = ListToDoRepository()

    //variables internas
    var arrayOfTasks: ArrayList<ListToDo> = ArrayList()

    //Atributos
    private val _listTasks = MutableLiveData<ArrayList<ListToDo>>()
    val listTask: LiveData<ArrayList<ListToDo>>
        get() = _listTasks

    fun getCatSentences(arrayListToDo: ArrayList<ListToDo>){
        viewModelScope.launch {
            val res = repository.listCatSentences()
            val catSentence = ListToDo(res.descripcion, false)
            arrayListToDo.add(catSentence)
            addNewTask(arrayListToDo)
            //_listTasks.value = arrayListToDo
        }
    }

    fun searchTask(texto:String){
        
        val newData:ArrayList<ListToDo> = arrayOfTasks.filter {
            it.descripcion.toLowerCase().contains(texto)
        } as ArrayList<ListToDo>

        _listTasks.value = newData

    }

    fun addNewTask(arrayListToDo: ArrayList<ListToDo>){
        viewModelScope.launch {
            arrayOfTasks = arrayListToDo
            _listTasks.value = arrayListToDo
        }
    }

}