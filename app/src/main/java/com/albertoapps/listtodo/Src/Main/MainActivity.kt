package com.albertoapps.listtodo.Src.Main

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class MainActivity : AppCompatActivity(), Adapter_ListToDo.OnClickInterface, Adapter_ListToDo.OnClickInterfacePosition {

    private lateinit var binding: ActivityListToDoBinding
    private lateinit var adapterListToDo: Adapter_ListToDo
    private lateinit var recyclerViewListToDo: RecyclerView
    private var showSearchview = false
    private var viewModelMainActivity = ViewModelMainActivity(this)
    var arrayOfTasks: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListToDoBinding.inflate(layoutInflater)
        val view = binding.root

        showSearchview()

        recyclerViewListToDo = binding.rvListTask
        addNewTask()

        setContentView(view)
    }

    override fun onResume() {
        super.onResume()

        search_task()

        loadListTask(this)

    }

    fun search_task(){

        binding.svListToDo.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(newText: Editable?) {
                viewModelMainActivity.searchTask(newText.toString())
            }
        })

    }

    fun addNewTask(){
        var cont = 0
        binding.addNewTask.setOnClickListener {
            arrayOfTasks.add("pollo" + cont++)
            viewModelMainActivity.addNewTask(arrayOfTasks)

        }

    }

    fun loadListTask(context: Context){

        viewModelMainActivity.listTask.observe(this) {

            adapterListToDo = Adapter_ListToDo(context, it, this, this,supportFragmentManager)

            recyclerViewListToDo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewListToDo.adapter = adapterListToDo
            recyclerViewListToDo.adapter?.notifyDataSetChanged()

        }

    }

    fun showSearchview(){

        binding.titleListToDo.imgFilter.setOnClickListener {

            if (!showSearchview){
                binding.svListToDo.lySearch.visibility = View.VISIBLE
                showSearchview = true
            } else {
                binding.svListToDo.lySearch.visibility = View.GONE
                showSearchview = false
            }
        }

    }

    override fun onClickItemAdapterItemList() {
        recyclerViewListToDo.adapter?.notifyDataSetChanged()
    }

    override fun onClickItemAdapterGetPosition(position:Int, newDescription:String) {
        arrayOfTasks[position] = newDescription
    }

}