package com.amar.sitambol.data.model.kelurahan

import com.google.gson.annotations.SerializedName

data class DataKelurahan (
    @SerializedName("id") val id: Long?,
    @SerializedName("nama") val nama: String?
)