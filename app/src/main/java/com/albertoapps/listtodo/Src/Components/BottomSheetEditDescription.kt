package com.albertoapps.listtodo.Src.Components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.databinding.BottomsheetEditDescriptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetEditDescription (context: Context, interfaz: interfaceEditarDescripcion) : BottomSheetDialogFragment() {

    private var interfazEditTxt = interfaz
    private var txt_editado = ""
    private lateinit var binding: BottomsheetEditDescriptionBinding
    var contexto = context

    interface interfaceEditarDescripcion {
        fun resultado_texto(descripcion: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetEditTheme)

    }

    override fun getTheme(): Int {
        return R.style.BottomSheetEditTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomsheetEditDescriptionBinding.inflate(inflater, container, false)

        binding.sendText.setOnClickListener {

            if (!binding.etDescription.text.toString().equals("")){
                txt_editado = binding.etDescription.text.toString()
                editDescription()
                dismiss()
            }
        }

        return binding.root
    }

    fun editDescription(){

        interfazEditTxt.resultado_texto(txt_editado)

    }

}