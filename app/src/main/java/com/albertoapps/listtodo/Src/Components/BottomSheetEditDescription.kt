package com.albertoapps.listtodo.Src.Components

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.databinding.BottomsheetEditDescriptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetEditDescription (context: Context, descripcion: String,interfaz: interfaceEditarDescripcion) : BottomSheetDialogFragment() {

    private var interfazEditTxt = interfaz
    private var txt_editado = ""
    private var descripcion = descripcion
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
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetEditDescriptionBinding.inflate(inflater, container, false)

        binding.etDescription.text = Editable.Factory.getInstance().newEditable(descripcion)
        binding.etDescription.requestFocus()
        showKeyboard()

        binding.sendText.setOnClickListener {

            if (!binding.etDescription.text.toString().equals("")){
                txt_editado = binding.etDescription.text.toString()
                editDescription()
                dismiss()
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        closeKeyboard()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        closeKeyboard()

    }

    fun showKeyboard() {
        val inputMethodManager: InputMethodManager =
            contexto!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun closeKeyboard() {
        val inputMethodManager: InputMethodManager =
            contexto.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun editDescription(){

        interfazEditTxt.resultado_texto(txt_editado)

    }

}