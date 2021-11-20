package com.albertoapps.listtodo.Src.Main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelMainActivity (
    context: Context
): ViewModel() {

    var arrayOfTasks: ArrayList<String> = ArrayList()

    private val _listTasks = MutableLiveData<ArrayList<String>>()
    val listTask: LiveData<ArrayList<String>>
        get() = _listTasks

    fun searchTask(texto:String){
        
        val newData:ArrayList<String> = arrayOfTasks.filter {
            it.toLowerCase().contains(texto)
        } as ArrayList<String>

        _listTasks.value = newData

    }

    fun addNewTask(arrayList: ArrayList<String>){
        viewModelScope.launch {
            arrayOfTasks = arrayList
            _listTasks.value = arrayList
        }
    }

}