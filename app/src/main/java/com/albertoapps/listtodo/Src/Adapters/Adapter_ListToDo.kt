package com.albertoapps.listtodo.Src.Adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.Src.Components.BottomSheetEditDescription


class Adapter_ListToDo( val context: Context,
    val ListActividades: ArrayList<ListToDo>,
    var onClickInterface: OnClickInterface,
    var onClickInterfacePosition: OnClickInterfacePosition,
    var onClickInterfaceCheck: OnClickInterfaceCheck,
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

    interface OnClickInterfaceCheck {
        fun onClickItemAdapterCheckButton(position:Int, check:Boolean)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val infoListToDo = ListActividades[position]

        holder.cbTaskReady.text = infoListToDo.descripcion
        holder.cbTaskReady.isChecked = infoListToDo.isChecked

        holder.cbTaskReady.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                onClickInterfaceCheck.onClickItemAdapterCheckButton(position, true)
            } else {
                onClickInterfaceCheck.onClickItemAdapterCheckButton(position, false)
            }
        }
        
        holder.imgEdit.setOnClickListener {
            val bottomSheetFiltro = BottomSheetEditDescription(
                context,
                infoListToDo.descripcion,
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

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar Tarea")
            builder.setMessage(context.getString(R.string.message_delete_task))

            builder.setPositiveButton(R.string.accept) { dialog, which ->
                ListActividades.remove(infoListToDo)
                onClickInterface.onClickItemAdapterItemList()
            }

            builder.setNegativeButton(R.string.cancel) { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
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