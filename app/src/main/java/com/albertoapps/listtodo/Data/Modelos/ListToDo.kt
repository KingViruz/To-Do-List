package com.albertoapps.listtodo.Data.Modelos

import com.google.gson.annotations.SerializedName

data class ListToDo(

    @SerializedName("fact")
    var descripcion: String,

    var isChecked: Boolean
)