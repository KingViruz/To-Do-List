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

    fun addNewTask(arrayList: ArrayList<String>){
        viewModelScope.launch {

            _listTasks.value = arrayList
        }
    }

}