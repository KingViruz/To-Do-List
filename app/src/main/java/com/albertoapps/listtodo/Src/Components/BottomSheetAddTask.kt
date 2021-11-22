package com.albertoapps.listtodo.Src.Components

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.R
import com.albertoapps.listtodo.Src.Main.ViewModelMainActivity
import com.albertoapps.listtodo.databinding.BottomsheetGeneralBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAddTask (context: Context, viewmodel: ViewModelMainActivity) : BottomSheetDialogFragment() {

    private var viewmodel = viewmodel
    private var txtNewTask = ""
    private var viewCatSentences = false
    private lateinit var binding: BottomsheetGeneralBinding
    var contexto = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetGeneralTheme)

    }

    override fun getTheme(): Int {
        return R.style.BottomSheetGeneralTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetGeneralBinding.inflate(inflater, container, false)

        binding.lyAddCatSentence.visibility = View.VISIBLE

        binding.etDescription.requestFocus()
        showKeyboard()

        binding.lyAddCatSentence.setOnClickListener {

            showOrHideViews()
            changeTitleText()
        }

        binding.sendText.setOnClickListener {

            if (viewCatSentences){
                if (validateNotEmptyText()) {
                    var times: Int = binding.etNumCatSentences.text.toString().toInt() - 1

                    for (i: Int in 0..times) {
                        viewmodel.getCatSentences(viewmodel.arrayOfTasks)
                    }

                    dismiss()
                }
            } else {
                if (validateNotEmptyText()){
                    txtNewTask = binding.etDescription.text.toString()
                    val list = ListToDo(txtNewTask, false)
                    viewmodel.arrayOfTasks.add(list)
                    viewmodel.addNewTask(viewmodel.arrayOfTasks)
                    binding.etDescription.clearFocus()
                    dismiss()
                }
            }
        }

        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        closeKeyboard()

    }

    fun validateNotEmptyText():Boolean{

        return if (viewCatSentences){
            !binding.etNumCatSentences.text.toString().equals("")
        } else {
            !binding.etDescription.text.toString().equals("")
        }

    }

    fun changeTitleText(){

        if (viewCatSentences){
            binding.txtAddCatSentences.text = getString(R.string.type_new_task)
        }
        else {
            binding.txtAddCatSentences.text = getString(R.string.add_cat_sentences_randoms)
        }

    }

    fun showOrHideViews(){

        if (!viewCatSentences){
            binding.etNumCatSentences.visibility = View.VISIBLE
            binding.etDescription.visibility = View.GONE
            binding.txtCatSentences.visibility = View.VISIBLE
            binding.etNumCatSentences.requestFocus()
            viewCatSentences = true
        } else {
            binding.etNumCatSentences.visibility = View.GONE
            binding.etDescription.visibility = View.VISIBLE
            binding.txtCatSentences.visibility = View.GONE
            binding.etDescription.requestFocus()
            viewCatSentences = false
        }

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

}