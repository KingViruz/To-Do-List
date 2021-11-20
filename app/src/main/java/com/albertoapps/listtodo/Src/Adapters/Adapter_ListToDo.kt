package com.albertoapps.listtodo.Src.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.Src.Components.BottomSheetEditDescription


class Adapter_ListToDo( val context: Context,
    val ListActividades: ArrayList<String>,
    var onClickInterface: OnClickInterface,
    var onClickInterfacePosition: OnClickInterfacePosition,
    var supportFragmentManager: FragmentManager)
    : RecyclerView.Adapter<Adapter_ListToDo.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_to_do, parent, false)
        return MyViewHolder(view)
    }

    interface OnClickInterfacePosition {
        fun onClickItemAdapterGetPosition(position:Int, newDescription:String)
    }

    interface OnClickInterface {
        fun onClickItemAdapterItemList()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val infoListToDo = ListActividades[position]

        holder.cbTaskReady.text = infoListToDo

        holder.imgEdit.setOnClickListener {
            val bottomSheetFiltro = BottomSheetEditDescription(
                context,
                object : BottomSheetEditDescription.interfaceEditarDescripcion{
                    override fun resultado_texto(descripcion: String) {
                        holder.cbTaskReady.text = descripcion
                        onClickInterfacePosition.onClickItemAdapterGetPosition(position, descripcion)
                    }
                }
            )

            bottomSheetFiltro.show(supportFragmentManager, "BottomSheetEdit")
        }

        holder.imgDelete.setOnClickListener {
            ListActividades.remove(infoListToDo)
            onClickInterface.onClickItemAdapterItemList()
        }

    }

    override fun getItemCount(): Int {
        return ListActividades.size
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgEdit:ImageView = itemView.findViewById(R.id.img_edit)
        val imgDelete:ImageView = itemView.findViewById(R.id.img_delete)
        val cbTaskReady: CheckBox = itemView.findViewById(R.id.cb_task_ready)
    }

}