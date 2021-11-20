package com.albertoapps.listtodo.Src.Main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.Src.Adapters.Adapter_ListToDo
import com.albertoapps.listtodo.databinding.ActivityListToDoBinding

class MainActivity : AppCompatActivity(), Adapter_ListToDo.OnClickInterface {

    private lateinit var binding: ActivityListToDoBinding
    private lateinit var adapterListToDo: Adapter_ListToDo
    private lateinit var recyclerViewListToDo: RecyclerView
    private var viewModelMainActivity = ViewModelMainActivity(this)
    var arrayOfTasks: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListToDoBinding.inflate(layoutInflater)
        val view = binding.root

        recyclerViewListToDo = binding.rvListTask
        addNewTask()

        setContentView(view)
    }

    override fun onResume() {
        super.onResume()

        loadListTask(this)

    }

    fun addNewTask(){
        var cont = 0
        binding.addNewTask.setOnClickListener {
            arrayOfTasks.add("pollo" + cont++)
            viewModelMainActivity.addNewTask(arrayOfTasks)

        }

    }

    fun loadListTask(context: Context){

        viewModelMainActivity.listTask.observe(this, {

            adapterListToDo = Adapter_ListToDo(context,it,this)

            recyclerViewListToDo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewListToDo.adapter = adapterListToDo
            recyclerViewListToDo.adapter?.notifyDataSetChanged()

        })



    }

    override fun onClickItemAdapterItemList() {
        recyclerViewListToDo.adapter?.notifyDataSetChanged()
    }

}