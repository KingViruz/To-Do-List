package com.albertoapps.listtodo.Src.Main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import kotlinx.coroutines.launch

class ViewModelMainActivity (
    context: Context
): ViewModel() {

    var arrayOfTasks: ArrayList<ListToDo> = ArrayList()

    private val _listTasks = MutableLiveData<ArrayList<ListToDo>>()
    val listTask: LiveData<ArrayList<ListToDo>>
        get() = _listTasks

    fun searchTask(texto:String){
        
        val newData:ArrayList<ListToDo> = arrayOfTasks.filter {
            it.descripcion.toLowerCase().contains(texto)
        } as ArrayList<ListToDo>

        _listTasks.value = newData

    }

    fun addNewTask(arrayList: ArrayList<ListToDo>){
        viewModelScope.launch {
            arrayOfTasks = arrayList
            _listTasks.value = arrayList
        }
    }

}