package com.albertoapps.listtodo.Data.Modelos

import com.google.gson.annotations.SerializedName

data class ListToDo(

    @SerializedName("username")
    val username: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellido")
    val apellido: String,
    @SerializedName("celular")
    val celular: String,
    @SerializedName("fotoPerfil")
    val fotoPerfil: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("token_usuario")
    val tokenUsuario: String,
    @SerializedName("token_relacion")
    val tokenRelacion: String
)