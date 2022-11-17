package com.amar.sitambol.data.model.gambar

import com.google.gson.annotations.SerializedName

data class DataGambar (
    @SerializedName("id") val id: Long?,
    @SerializedName("bengkel_id") val bengkel_id: Long?,
    @SerializedName("gambar") val gambar: String?
)