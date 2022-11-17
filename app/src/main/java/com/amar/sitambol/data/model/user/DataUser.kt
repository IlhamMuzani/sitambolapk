package com.amar.sitambol.data.model.user

import com.amar.sitambol.data.model.gambar.DataGambar
import com.google.gson.annotations.SerializedName

data class DataUser (
    @SerializedName("id") val id: Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("gambar") val gambar: String?
)